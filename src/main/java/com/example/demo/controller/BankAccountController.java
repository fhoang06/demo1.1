package com.example.demo.controller;

import com.example.demo.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

public class BankAccountController {
    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping("/transfer-money")
    public ResponseEntity<String> transferMoney(@RequestParam Long id,
                                                @RequestParam Long owner,
                                                @RequestParam BigDecimal balance) {
        try {
            bankAccountService.transferMoney(id, owner, balance);
            return ResponseEntity.ok("Money transferred successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
