package com.jay.personalfinancemanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jay.personalfinancemanager.entity.Expense;
import com.jay.personalfinancemanager.service.ExpenseService;

@RestController
@RequestMapping("/expense")
@CrossOrigin
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // Add Expense for a specific user
    @PostMapping("/{userId}")
    public Expense addExpense(
            @PathVariable Long userId,
            @RequestBody Expense expense) {

        return expenseService.saveExpense(userId, expense);
    }

    // Get Expense for a specific user
    @GetMapping("/user/{userId}")
    public List<Expense> getExpenseByUser(
            @PathVariable Long userId) {

        return expenseService.getExpenseByUser(userId);
    }

    // Get Expense By ID
    @GetMapping("/{id}")
    public Expense getExpenseById(
            @PathVariable Long id) {

        return expenseService.getExpenseById(id);
    }

    // Update Expense
    @PutMapping("/{id}")
    public Expense updateExpense(
            @PathVariable Long id,
            @RequestBody Expense expense) {

        return expenseService.updateExpense(id, expense);
    }

    // Delete Expense
    @DeleteMapping("/{id}")
    public String deleteExpense(
            @PathVariable Long id) {

        return expenseService.deleteExpense(id);
    }
}