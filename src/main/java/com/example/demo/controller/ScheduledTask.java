package com.example.demo.controller;

import com.example.demo.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ScheduledTask {

    @Autowired
    private BankAccountService bankAccountService;

    private final Object lock = new Object();

    @Scheduled(cron = "*/10 * * * * *")
    public void automaticTransfer() {
        synchronized (lock) {
            try {
                bankAccountService.transferMoney(1L, 2L, new BigDecimal("10"));
                System.out.println("Automatic transfer 1 completed successfully.");

                bankAccountService.transferMoney(1L, 3L, new BigDecimal("5"));
                System.out.println("Automatic transfer 2 completed successfully.");

                bankAccountService.transferMoney(1L, 4L, new BigDecimal("1"));
                System.out.println("Automatic transfer 3 completed successfully.");
            } catch (Exception e) {
                System.out.println("Error during automatic transfer: " + e.getMessage());
            }
        }
    }
}

