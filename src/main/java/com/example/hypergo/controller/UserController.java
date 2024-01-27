package com.example.hypergo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.hypergo.model.Stock;
import com.example.hypergo.service.UserService;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/{userId}/favourites/{stockCode}")
    public ResponseEntity<?> addFavourite(@PathVariable Long userId, @PathVariable Long stockCode) {
        try {
            userService.addFavourite(userId, stockCode);
            return ResponseEntity.ok("Stock added successfully!");
        } catch (Exception e) {
            if (e instanceof ResponseStatusException) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } else {
                return ResponseEntity.internalServerError().build();
            }
        }
    }
    
    @GetMapping("/{userId}/favourites")
    public ResponseEntity<?> getFavourites(@PathVariable Long userId) {
        try {
            Set<Stock> favStocks = userService.getFavourites(userId);
            return ResponseEntity.ok(favStocks);
        } catch (Exception e) {
            if (e instanceof ResponseStatusException) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } else {
                return ResponseEntity.internalServerError().body(e.getMessage());
            }
        }
    }

    @DeleteMapping("/{userId}/favourites/{stockCode}")
    public ResponseEntity<?> removeFavourite(@PathVariable Long userId,@PathVariable Long stockCode) {
        try{
            userService.removeFavourite(userId, stockCode);
            return ResponseEntity.ok("stock removed successfully!");
        } catch(Exception e) {
            if(e instanceof ResponseStatusException) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } else {
                return ResponseEntity.internalServerError().body(e.getMessage());
            }
        }
    }
    
}
