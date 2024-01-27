package com.example.hypergo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.hypergo.model.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("Select p From Price p Where p.date = :date Order By (p.close - p.open) Desc")
    List<Price> findTopStocksByDate(@Param("date") LocalDate date, Pageable pageable);
    
    @Query("SELECT p FROM Price p WHERE p.stock.name = :name ORDER BY p.date DESC")
    List<Price> findLatestPriceByStockName(@Param("name") String name, Pageable pageable);

    @Query("SELECT p FROM Price p WHERE p.stock.code = :code ORDER BY p.date DESC")
    List<Price> findByStockCodeOrderByDateDesc(Long code);
} 
