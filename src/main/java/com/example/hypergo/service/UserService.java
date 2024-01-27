package com.example.hypergo.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.hypergo.model.Stock;
import com.example.hypergo.model.User;
import com.example.hypergo.repository.StockRepository;
import com.example.hypergo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StockRepository stockRepository;

    @Transactional
    public void addFavourite(Long userId, Long stockCode) {
        try {
            User user = userRepository.findById(userId).orElseThrow(
                    () -> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "User not found!"));
            Stock stock = stockRepository.findById(stockCode).orElseThrow(
                    () -> new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Stock not found!"));
            user.addFavourite(stock);
            userRepository.save(user);
        } catch (Exception e) {
            throw e;
        }
    }

    public Set<Stock> getFavourites(Long userID) {
        try {
            User user = userRepository.findById(userID).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"user not found!"));
            return user.getFavourites();
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public void removeFavourite(Long userId, Long stockCode) {
        try {
            User user = userRepository.findById(userId).orElseThrow(
                    () -> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "User not found!"));
            Stock stock = stockRepository.findById(stockCode).orElseThrow(
                    () -> new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Stock not found!"));
            user.removeFavourite(stock);
            userRepository.save(user);
        } catch (Exception e) {
            throw e;
        }
    }
}
