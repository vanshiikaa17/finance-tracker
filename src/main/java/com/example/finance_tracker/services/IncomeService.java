package com.example.finance_tracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finance_tracker.Util.ValidateUser;
import com.example.finance_tracker.models.Income;
import com.example.finance_tracker.models.User;
import com.example.finance_tracker.repositories.IncomeRepo;

@Service
public class IncomeService {
    @Autowired
    private IncomeRepo incomeRepository;
    
    @Autowired
    private ValidateUser validateUser;

    public Income addIncome(String token, Income income){
        User user=validateUser.findUser(token);
        // Link the income to the user
        income.setUser(user);
        return incomeRepository.save(income);
    }

    public List<Income> getAllIncomes(String token) {
        User user=validateUser.findUser(token);
        return incomeRepository.findByUser(user);
    }

    public List<Income> getAllIncomesBySource(String token, String source){
        User user=validateUser.findUser(token);
        return incomeRepository.findByUserAndSource(user, source);
    }
    
}
