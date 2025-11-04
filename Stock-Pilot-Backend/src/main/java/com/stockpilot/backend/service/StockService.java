package com.stockpilot.backend.service;

import com.stockpilot.backend.model.Stock;
import com.stockpilot.backend.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    public Stock addStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public Stock updateStockPrice(String symbol, Double newPrice) {
        Stock stock = stockRepository.findBySymbol(symbol);
        if (stock == null) throw new RuntimeException("Stock not found");
        stock.setPrice(newPrice);
        return stockRepository.save(stock);
    }
}
