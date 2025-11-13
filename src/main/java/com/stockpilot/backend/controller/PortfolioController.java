package com.stockpilot.backend.controller;

import com.stockpilot.backend.dto.PortfolioDTO;
import com.stockpilot.backend.model.Portfolio;
import com.stockpilot.backend.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

            portfolioService.buyStock(email, symbol, quantity, price);

            // Return updated portfolio list
            List<Portfolio> updated = portfolioService.getUserPortfolio(email);
            List<PortfolioDTO> response = updated.stream()
                    .map(PortfolioDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(response);
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

            portfolioService.sellStock(email, symbol, quantity);

            List<Portfolio> updated = portfolioService.getUserPortfolio(email);
            List<PortfolioDTO> response = updated.stream()
                    .map(PortfolioDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<PortfolioDTO>> getPortfolio(@PathVariable String email) {
        // Mock data shortcut for your demo user
        if (email.equalsIgnoreCase("soumika@stockpilot.com")) {
            List<PortfolioDTO> mock = List.of(
                new PortfolioDTO("AAPL", "Tech", 10, 175.0, 182.0),
                new PortfolioDTO("TSLA", "Auto", 5, 720.0, 700.0),
                new PortfolioDTO("RELIANCE", "Energy", 12, 2600.0, 2720.0),
                new PortfolioDTO("INFY", "Tech", 8, 1450.0, 1400.0),
                new PortfolioDTO("TCS", "Tech", 6, 3600.0, 3740.0),
                new PortfolioDTO("HDFC", "Finance", 5, 1550.0, 1605.0),
                new PortfolioDTO("HINDUNILVR", "FMCG", 4, 2500.0, 2470.0),
                new PortfolioDTO("AMZN", "E-Commerce", 3, 3120.0, 3250.0),
                new PortfolioDTO("SUNPHARMA", "Pharma", 10, 1170.0, 1215.0),
                new PortfolioDTO("ICICIBANK", "Banking", 15, 960.0, 985.0)
            );
            return ResponseEntity.ok(mock);
        }

        // Real DB flow: map Portfolio entity -> PortfolioDTO (keeps the shape stable)
        List<Portfolio> portfolios = portfolioService.getUserPortfolio(email);
        List<PortfolioDTO> dtoList = portfolios.stream()
                .map(PortfolioDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }
}
