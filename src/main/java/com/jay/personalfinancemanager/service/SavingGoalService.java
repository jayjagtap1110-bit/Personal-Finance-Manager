package com.jay.personalfinancemanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jay.personalfinancemanager.entity.SavingGoal;
import com.jay.personalfinancemanager.entity.User;
import com.jay.personalfinancemanager.repository.SavingGoalRepository;
import com.jay.personalfinancemanager.repository.UserRepository;

@Service
public class SavingGoalService {

    @Autowired
    private SavingGoalRepository savingGoalRepository;

    @Autowired
    private UserRepository userRepository;

    // Add Goal
    public SavingGoal saveGoal(Long userId, SavingGoal goal) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        goal.setUser(user);

        return savingGoalRepository.save(goal);
    }

    // Get Goals for User
    public List<SavingGoal> getGoalsByUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        return savingGoalRepository.findByUser(user);
    }

    // Get Goal By ID
    public SavingGoal getGoalById(Long id) {

        return savingGoalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Goal Not Found"));
    }

    // Update Goal
    public SavingGoal updateGoal(Long id, SavingGoal updatedGoal) {

        SavingGoal existingGoal = savingGoalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Goal Not Found"));

        existingGoal.setGoalName(updatedGoal.getGoalName());
        existingGoal.setTargetAmount(updatedGoal.getTargetAmount());
        existingGoal.setTargetDate(updatedGoal.getTargetDate());
        existingGoal.setDescription(updatedGoal.getDescription());

        return savingGoalRepository.save(existingGoal);
    }

    // Delete Goal
    public String deleteGoal(Long id) {

        if (savingGoalRepository.existsById(id)) {

            savingGoalRepository.deleteById(id);

            return "Goal Deleted Successfully";
        }

        return "Goal Not Found";
    }
}