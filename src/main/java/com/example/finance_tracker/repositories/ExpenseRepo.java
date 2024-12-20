package com.example.finance_tracker.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.finance_tracker.models.Expense;
import com.example.finance_tracker.models.User;



public interface ExpenseRepo extends JpaRepository<Expense, Long>{
   List<Expense> findByUser(User user);
   List<Expense> findByUserAndCategory(User user, String category);
    
} 
