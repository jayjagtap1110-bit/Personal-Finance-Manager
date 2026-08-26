package com.jay.personalfinancemanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jay.personalfinancemanager.entity.Saving;
import com.jay.personalfinancemanager.entity.SavingGoal;
import com.jay.personalfinancemanager.entity.User;
import com.jay.personalfinancemanager.repository.SavingRepository;
import com.jay.personalfinancemanager.repository.SavingGoalRepository;
import com.jay.personalfinancemanager.repository.UserRepository;

@Service
public class SavingService {

    @Autowired
    private SavingRepository savingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SavingGoalRepository savingGoalRepository;


    // Add Saving to Goal
    public Saving saveSaving(
            Long userId,
            Long goalId,
            Saving saving) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                    new RuntimeException("User Not Found"));

        SavingGoal goal = savingGoalRepository.findById(goalId)
                .orElseThrow(() ->
                    new RuntimeException("Saving Goal Not Found"));

        // Make sure goal belongs to logged-in user
        if (goal.getUser() == null ||
            !goal.getUser().getId().equals(userId)) {

            throw new RuntimeException(
                "This Saving Goal does not belong to this User");
        }

        saving.setUser(user);

        saving.setGoal(goal);

        return savingRepository.save(saving);
    }


    // Get Savings for User
    public List<Saving> getSavingsByUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                    new RuntimeException("User Not Found"));

        return savingRepository.findByUser(user);
    }


    // Get Saving By ID
    public Saving getSavingById(Long id) {

        return savingRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Saving Not Found"));
    }


    // Update Saving
    public Saving updateSaving(
            Long id,
            Saving updatedSaving) {

        Saving existingSaving =
                savingRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Saving Not Found"));

        existingSaving.setAmount(
                updatedSaving.getAmount());

        existingSaving.setCurrency(
                updatedSaving.getCurrency());

        existingSaving.setDate(
                updatedSaving.getDate());

        existingSaving.setDescription(
                updatedSaving.getDescription());

        return savingRepository.save(existingSaving);
    }


    // Delete Saving
    public String deleteSaving(Long id) {

        if (savingRepository.existsById(id)) {

            savingRepository.deleteById(id);

            return "Saving Deleted Successfully";
        }

        return "Saving Not Found";
    }
}