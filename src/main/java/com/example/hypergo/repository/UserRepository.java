package com.example.hypergo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hypergo.model.User;

public interface UserRepository extends JpaRepository<User,Long>{

    
} 
