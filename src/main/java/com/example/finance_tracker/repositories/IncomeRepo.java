package com.example.finance_tracker.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.finance_tracker.models.Income;
import com.example.finance_tracker.models.User;



public interface IncomeRepo extends JpaRepository<Income, Long>{
   
    List<Income> findByUserAndSource(User user, String source);
    List<Income> findByUser(User user);
} 
