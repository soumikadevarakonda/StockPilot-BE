package com.stockpilot.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AiController {
    @PostMapping("/portfolio-advice")
    public ResponseEntity<String> getPortfolioAdvice(@RequestBody Map<String, Object> payload) {
        Object holdingsValue = payload.get("holdings");
        if (!(holdingsValue instanceof List<?> holdings) || holdings.isEmpty()) {
            return ResponseEntity.badRequest().body("No holdings were provided.");
        }

        return ResponseEntity.ok(
                "Diversification overview:\n" +
                "- Spread risk across several sectors rather than concentrating in one theme.\n" +
                "- Review positions with very large weights and rebalance gradually.\n" +
                "- Compare each holding's expected return with its volatility before trading.\n" +
                "- This is educational guidance, not financial advice."
        );
    }
}
