package com.stockpilot.backend.service;

import com.stockpilot.backend.model.Transaction;
import com.stockpilot.backend.model.User;
import com.stockpilot.backend.repository.TransactionRepository;
import com.stockpilot.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    public Transaction recordTransaction(User user, String type, int quantity, double price, com.stockpilot.backend.model.Stock stock) {
        Transaction transaction = new Transaction(user, stock, type, quantity, price, LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getUserTransactions(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return transactionRepository.findByUserOrderByTimestampDesc(user);
    }
}
