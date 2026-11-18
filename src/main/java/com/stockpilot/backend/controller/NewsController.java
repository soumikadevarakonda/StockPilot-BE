package com.stockpilot.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    @GetMapping
    public ResponseEntity<List<Map<String, String>>> getNews() {
        return ResponseEntity.ok(List.of(
                Map.of("title", "Markets open higher as technology shares gain", "category", "Stocks", "source", "StockPilot", "time", "Just now"),
                Map.of("title", "Bitcoin steadies after a volatile trading session", "category", "Crypto", "source", "StockPilot", "time", "Just now"),
                Map.of("title", "Investors watch inflation and central bank signals", "category", "Economy", "source", "StockPilot", "time", "Just now")
        ));
    }
}
