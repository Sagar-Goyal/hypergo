package com.example.hypergo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.hypergo.model.Price;
import com.example.hypergo.repository.PriceRepository;

@Service
public class StockService {

    @Autowired
    private PriceRepository priceRepository;

    public List<Price> getTopTenStocks() {
        LocalDate date = LocalDate.now();
        Pageable topTen = PageRequest.of(0, 10);
        try {
            return priceRepository.findTopStocksByDate(date, topTen);
        } catch (Exception e) {
            throw e;
        }
    }

    public Price getStockByName(String name) {
        Pageable topOne = PageRequest.of(0, 1);
        try {
            List<Price> prices = priceRepository.findLatestPriceByStockName(name, topOne);
            return prices.isEmpty() ? null : prices.get(0);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Price> getStockHistory(Long code) {
        try {
            List<Price> prices = priceRepository.findByStockCodeOrderByDateDesc(code);
            return prices;
        } catch (Exception e) {
            throw e;
        }
    }
}
