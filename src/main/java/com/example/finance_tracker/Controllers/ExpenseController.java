package com.example.finance_tracker.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.finance_tracker.models.Expense;
import com.example.finance_tracker.services.ExpenseService;

@Controller
@RequestMapping("/api/expense")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;
  

    @PostMapping("/add-expense")
    public ResponseEntity<?> addExpense(@CookieValue(value = "auth_token") String token,@RequestBody Expense expense){
        try {           
            expenseService.addExpense(token, expense);
            return ResponseEntity.ok(Map.of("message", "Expense added successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
            
        }
    } 
    @GetMapping("/all-expenses")
    public ResponseEntity<?> getAllExpenses(@CookieValue(value = "auth_token") String token){
        try{
            List<Expense> expenses=expenseService.getAllExpenses(token);
            return ResponseEntity.ok(expenses);
        }catch(RuntimeException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", ex.getMessage()));

        }
    }
    @GetMapping("/by-category")
    public ResponseEntity<?> getAllExpensesByCategory(@CookieValue(value = "auth_token") String token, @RequestParam String category){
        try{
            List<Expense> expenses=expenseService.getExpensesByCategory(token, category);
            if(expenses.size()==0){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "No expenses found for category: " + category));
            }
            return ResponseEntity.ok(expenses);
        }catch(RuntimeException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", ex.getMessage()));

        }
    }
}
