package com.example.hypergo.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Stock {
    @Id
    private Long code;
    
    private String name;

    @OneToMany(mappedBy = "stock")
    private Set<Price> prices = new HashSet<>();

    @ManyToMany(mappedBy = "favourites")
    private Set<User> users = new HashSet<>();

    public Stock(Long code, String name) {
        this.code = code;
        this.name = name;
    }
}
