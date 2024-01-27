package com.example.hypergo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.hypergo.model.Price;
import com.example.hypergo.service.StockService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/getTopTenStocks")
    public ResponseEntity<Object> getTopTenStocks() {
        try {
            List<Price> topTenList = stockService.getTopTenStocks();
            return ResponseEntity.ok(topTenList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    
    @GetMapping("/getStock/{name}")
    public ResponseEntity<Object> getStockByName(@RequestParam String name) {
        try {
            Price stockPrice = stockService.getStockByName(name);
            return ResponseEntity.ok(stockPrice);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    
    @GetMapping("/getStockHistory/{code}")
    public ResponseEntity<Object> getStockHistory(@RequestParam("code") String code) {
        try {
            Long stockCode = Long.getLong(code);
            List<Price> stockPriceHistory = stockService.getStockHistory(stockCode);
            return ResponseEntity.ok(stockPriceHistory);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
