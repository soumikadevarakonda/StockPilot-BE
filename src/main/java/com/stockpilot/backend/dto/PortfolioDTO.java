package com.stockpilot.backend.dto;

import com.stockpilot.backend.model.Portfolio;
import com.stockpilot.backend.model.Stock;

public class PortfolioDTO {
    private String symbol;
    private String sector;
    private int quantity;
    private double avgPrice;
    private double currentPrice;

    // ✅ Constructor for real database Portfolio entity
    public PortfolioDTO(Portfolio p) {
        if (p == null) return;

        this.quantity = p.getQuantity();
        this.avgPrice = p.getAvgPrice() != null ? p.getAvgPrice() : 0.0;

        Stock s = p.getStock();
        if (s != null) {
            this.symbol = s.getSymbol();
            this.sector = s.getSector() != null ? s.getSector() : "Misc";
            this.currentPrice = s.getPrice() != null ? s.getPrice() : 0.0;
        } else {
            this.symbol = "Unknown";
            this.sector = "Misc";
            this.currentPrice = 0.0;
        }
    }

    // ✅ Convenience constructor for mock data
    public PortfolioDTO(String symbol, String sector, int quantity, double avgPrice, double currentPrice) {
        this.symbol = symbol;
        this.sector = sector;
        this.quantity = quantity;
        this.avgPrice = avgPrice;
        this.currentPrice = currentPrice;
    }

    // ✅ Getters (needed for JSON serialization)
    public String getSymbol() { return symbol; }
    public String getSector() { return sector; }
    public int getQuantity() { return quantity; }
    public double getAvgPrice() { return avgPrice; }
    public double getCurrentPrice() { return currentPrice; }
}
