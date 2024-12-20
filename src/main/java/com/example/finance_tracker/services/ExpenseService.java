package com.example.finance_tracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finance_tracker.Util.ValidateUser;
import com.example.finance_tracker.models.Expense;
import com.example.finance_tracker.models.User;
import com.example.finance_tracker.repositories.ExpenseRepo;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepo expenseRepository;
     @Autowired
    private ValidateUser validateUser;

    public Expense addExpense(String token, Expense expense) {
        User user = validateUser.findUser(token);

        expense.setUser(user);
        
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses(String token) {
        User user = validateUser.findUser(token);
        return expenseRepository.findByUser(user);
    }

    public List<Expense> getExpensesByCategory(String token, String category) {
        User user = validateUser.findUser(token);
        return expenseRepository.findByUserAndCategory(user, category);
    }
}
