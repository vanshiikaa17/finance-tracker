package com.example.finance_tracker.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.finance_tracker.models.Income;
import com.example.finance_tracker.services.IncomeService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/income")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;
   
    @PostMapping("/add-income")
    public ResponseEntity<?> addIncome(@CookieValue(value="auth_token") String token, @RequestBody Income income) {
        try {
            incomeService.addIncome(token, income);
            return ResponseEntity.ok(Map.of("message", "Income added successfully"));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", ex.getMessage()));
        }
    }
    @GetMapping("/my-incomes")
    public ResponseEntity<?> getIncomes(@CookieValue(value="auth_token") String token) {

        try{
            List<Income> incomes=incomeService.getAllIncomes(token);
            return ResponseEntity.ok(incomes);
        }catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", ex.getMessage()));
        }
    }
    @GetMapping("/source")
    public ResponseEntity<?> getIncomesBySource(@CookieValue(value="auth_token") String token, @RequestParam String source) {

        try{
            List<Income> incomes=incomeService.getAllIncomesBySource(token, source);
            if(incomes.size()==0){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "No expenses found for source: " + source));
            }
            return ResponseEntity.ok(incomes);
        }catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", ex.getMessage()));
        }
    }
    
}
