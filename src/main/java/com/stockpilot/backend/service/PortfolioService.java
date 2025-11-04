package com.stockpilot.backend.service;

import com.stockpilot.backend.model.Portfolio;
import com.stockpilot.backend.model.Stock;
import com.stockpilot.backend.model.User;
import com.stockpilot.backend.repository.PortfolioRepository;
import com.stockpilot.backend.repository.StockRepository;
import com.stockpilot.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;
    
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private UserRepository userRepository;

    public Portfolio buyStock(String email, String symbol, int quantity, double price) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Stock stock = stockRepository.findBySymbol(symbol);
        if (stock == null) throw new RuntimeException("Stock not found");

        // check if user already owns stock
        Portfolio portfolio = portfolioRepository.findByUserAndStock(user, stock).orElse(null);

        if (portfolio == null) {
            portfolio = new Portfolio(user, stock, quantity, price);
        } else {
            // update average price and quantity
            int totalQty = portfolio.getQuantity() + quantity;
            double newAvgPrice = ((portfolio.getAvgPrice() * portfolio.getQuantity()) + (price * quantity)) / totalQty;
            portfolio.setQuantity(totalQty);
            portfolio.setAvgPrice(newAvgPrice);
        }

        transactionService.recordTransaction(user, "BUY", quantity, price, stock);

        return portfolioRepository.save(portfolio);
    }

    public Portfolio sellStock(String email, String symbol, int quantity) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Stock stock = stockRepository.findBySymbol(symbol);
        if (stock == null) throw new RuntimeException("Stock not found");

        Portfolio portfolio = portfolioRepository.findByUserAndStock(user, stock)
                .orElseThrow(() -> new RuntimeException("Stock not owned by user"));

        if (portfolio.getQuantity() < quantity) throw new RuntimeException("Not enough quantity to sell");

        portfolio.setQuantity(portfolio.getQuantity() - quantity);

        if (portfolio.getQuantity() == 0) {
            portfolioRepository.delete(portfolio);
            return null;
        }
        
        transactionService.recordTransaction(user, "SELL", quantity, stock.getPrice(), stock);

        return portfolioRepository.save(portfolio);
    }

    public List<Portfolio> getUserPortfolio(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return portfolioRepository.findByUser(user);
    }
}
