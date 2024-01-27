package com.example.hypergo.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(
        name = "user_favourites",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "stock_code")
    )
    private Set<Stock> favourites = new HashSet<>();

    public User(String name) {
        this.name = name;
    }

    public void addFavourite(Stock stock) {
        favourites.add(stock);
        stock.getUsers().add(this);
    }

    public void removeFavourite(Stock stock) {
        favourites.remove(stock);
        stock.getUsers().remove(this);
    }
}
