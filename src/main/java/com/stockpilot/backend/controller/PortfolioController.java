package com.stockpilot.backend.controller;

import com.stockpilot.backend.model.Portfolio;
import com.stockpilot.backend.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/portfolio")
@CrossOrigin(origins = "*")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @PostMapping("/buy")
    public ResponseEntity<?> buyStock(@RequestBody Map<String, Object> payload) {
        try {
            String email = (String) payload.get("email");
            String symbol = (String) payload.get("symbol");
            int quantity = (int) payload.get("quantity");
            double price = ((Number) payload.get("price")).doubleValue();

            Portfolio portfolio = portfolioService.buyStock(email, symbol, quantity, price);
            return ResponseEntity.ok(portfolio);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/sell")
    public ResponseEntity<?> sellStock(@RequestBody Map<String, Object> payload) {
        try {
            String email = (String) payload.get("email");
            String symbol = (String) payload.get("symbol");
            int quantity = (int) payload.get("quantity");

            Portfolio portfolio = portfolioService.sellStock(email, symbol, quantity);
            return ResponseEntity.ok(portfolio != null ? portfolio : "All shares sold, removed from portfolio");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<Portfolio>> getPortfolio(@PathVariable String email) {
        return ResponseEntity.ok(portfolioService.getUserPortfolio(email));
    }
}
