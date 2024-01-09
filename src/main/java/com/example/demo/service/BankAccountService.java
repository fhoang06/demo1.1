package com.example.demo.service;

import com.example.demo.model.BankAccount;
import com.example.demo.repository.BankAccountRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Log4j2
@Service
public class BankAccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Transactional(rollbackFor = {Exception.class} )
    @Async
    public void transferMoney(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        try{
            BankAccount fromAccount = bankAccountRepository.findById(fromAccountId)
                    .orElseThrow(() -> new RuntimeException("From account not found"));

            BankAccount toAccount = bankAccountRepository.findById(toAccountId)
                    .orElseThrow(() -> new RuntimeException("To account not found"));

            if (fromAccount.getBalance().compareTo(amount) < 0) {
                throw new RuntimeException("Not enough balance in the source account");
            }
            fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
            toAccount.setBalance(toAccount.getBalance().add(amount));

            bankAccountRepository.save(fromAccount);
            bankAccountRepository.save(toAccount);
            log.info(amount);
            log.info(fromAccount);
            log.info(toAccountId);
        }
        catch (Exception e)
        {
            log.error(amount);
            log.error(toAccountId);
            log.error(fromAccountId);
            throw new RuntimeException("Transaction failed");
        }
    }
}
