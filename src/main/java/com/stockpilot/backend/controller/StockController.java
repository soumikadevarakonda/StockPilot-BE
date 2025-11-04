package com.stockpilot.backend.controller;

import com.stockpilot.backend.model.Stock;
import com.stockpilot.backend.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@CrossOrigin(origins = "*")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        return ResponseEntity.ok(stockService.getAllStocks());
    }

    @PostMapping
    public ResponseEntity<Stock> addStock(@RequestBody Stock stock) {
        return ResponseEntity.ok(stockService.addStock(stock));
    }

    @PutMapping("/{symbol}")
    public ResponseEntity<Stock> updateStockPrice(
            @PathVariable String symbol,
            @RequestParam Double price) {
        return ResponseEntity.ok(stockService.updateStockPrice(symbol, price));
    }
}
