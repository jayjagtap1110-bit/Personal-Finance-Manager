package com.jay.personalfinancemanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jay.personalfinancemanager.entity.Income;
import com.jay.personalfinancemanager.service.IncomeService;

@RestController
@RequestMapping("/income")
@CrossOrigin
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    // Add Income for a specific user
    @PostMapping("/{userId}")
    public Income addIncome(
            @PathVariable Long userId,
            @RequestBody Income income) {

        return incomeService.saveIncome(userId, income);
    }

    // Get Income for a specific user
    @GetMapping("/user/{userId}")
    public List<Income> getIncomeByUser(
            @PathVariable Long userId) {

        return incomeService.getIncomeByUser(userId);
    }

    // Get Income By ID
    @GetMapping("/{id}")
    public Income getIncomeById(
            @PathVariable Long id) {

        return incomeService.getIncomeById(id);
    }

    // Update Income
    @PutMapping("/{id}")
    public Income updateIncome(
            @PathVariable Long id,
            @RequestBody Income income) {

        return incomeService.updateIncome(id, income);
    }

    // Delete Income
    @DeleteMapping("/{id}")
    public String deleteIncome(
            @PathVariable Long id) {

        return incomeService.deleteIncome(id);
    }
}