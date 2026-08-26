package com.jay.personalfinancemanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jay.personalfinancemanager.entity.SavingGoal;
import com.jay.personalfinancemanager.service.SavingGoalService;

@RestController
@RequestMapping("/saving-goal")
@CrossOrigin
public class SavingGoalController {

    @Autowired
    private SavingGoalService savingGoalService;

    // Add Goal
    @PostMapping("/{userId}")
    public SavingGoal addGoal(
            @PathVariable Long userId,
            @RequestBody SavingGoal goal) {

        return savingGoalService.saveGoal(userId, goal);
    }

    // Get Goals for User
    @GetMapping("/user/{userId}")
    public List<SavingGoal> getGoalsByUser(
            @PathVariable Long userId) {

        return savingGoalService.getGoalsByUser(userId);
    }

    // Get Goal By ID
    @GetMapping("/{id}")
    public SavingGoal getGoalById(
            @PathVariable Long id) {

        return savingGoalService.getGoalById(id);
    }

    // Update Goal
    @PutMapping("/{id}")
    public SavingGoal updateGoal(
            @PathVariable Long id,
            @RequestBody SavingGoal goal) {

        return savingGoalService.updateGoal(id, goal);
    }

    // Delete Goal
    @DeleteMapping("/{id}")
    public String deleteGoal(
            @PathVariable Long id) {

        return savingGoalService.deleteGoal(id);
    }
}