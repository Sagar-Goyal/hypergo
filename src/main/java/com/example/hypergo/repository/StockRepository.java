package com.example.hypergo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hypergo.model.Stock;

public interface StockRepository extends JpaRepository<Stock,Long>{

    
} 
