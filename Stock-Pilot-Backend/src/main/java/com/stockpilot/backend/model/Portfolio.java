package com.stockpilot.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "portfolio")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double avgPrice;

    public Portfolio() {}

    public Portfolio(User user, Stock stock, Integer quantity, Double avgPrice) {
        this.user = user;
        this.stock = stock;
        this.quantity = quantity;
        this.avgPrice = avgPrice;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Stock getStock() { return stock; }

    public void setStock(Stock stock) { this.stock = stock; }

    public Integer getQuantity() { return quantity; }

    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getAvgPrice() { return avgPrice; }

    public void setAvgPrice(Double avgPrice) { this.avgPrice = avgPrice; }
}
