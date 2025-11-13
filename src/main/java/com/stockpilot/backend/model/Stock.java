package com.stockpilot.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String symbol;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String sector;

    public Stock() {}

    public Stock(String symbol, String name, Double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.sector = assignSector(symbol);
    }

    // Helper method to assign sector automatically
    private String assignSector(String symbol) {
        if (symbol == null) return "Misc";
        switch (symbol.toUpperCase()) {
            case "AAPL":
            case "TCS":
            case "INFY":
                return "Tech";
            case "TSLA":
                return "Auto";
            case "RELIANCE":
                return "Energy";
            case "HDFC":
            case "ICICIBANK":
                return "Finance";
            case "HINDUNILVR":
                return "FMCG";
            case "SUNPHARMA":
                return "Pharma";
            case "AMZN":
                return "E-Commerce";
            default:
                return "Misc";
        }
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
        this.sector = assignSector(symbol); // Auto-update sector if symbol changes
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
