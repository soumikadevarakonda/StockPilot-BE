package com.stockpilot.backend.controller;

import com.stockpilot.backend.model.Transaction;
import com.stockpilot.backend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{email}")
    public ResponseEntity<List<Transaction>> getUserTransactions(@PathVariable String email) {
        return ResponseEntity.ok(transactionService.getUserTransactions(email));
    }
}
