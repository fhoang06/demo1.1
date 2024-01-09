package com.example.demo.repository;

import com.example.demo.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    @Query(value = "SELECT *  FROM testdb.bank a WHERE a.accountNumber = :accountNumber", nativeQuery = true)
    BankAccount findByAccountNumberUsingQuery(@Param("accountNumber") String accountNumber);

    BankAccount findByAccountNumber(String accountNumber);
}