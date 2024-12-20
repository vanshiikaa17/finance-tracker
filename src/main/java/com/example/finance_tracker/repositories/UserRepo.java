package com.example.finance_tracker.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.finance_tracker.models.User;



public interface UserRepo extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
    
} 
