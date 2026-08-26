package com.jay.personalfinancemanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jay.personalfinancemanager.entity.Saving;
import com.jay.personalfinancemanager.service.SavingService;

@RestController
@RequestMapping("/saving")
@CrossOrigin
public class SavingController {

    @Autowired
    private SavingService savingService;


    // Add Saving to Selected Goal
    @PostMapping("/{userId}/goal/{goalId}")
    public Saving addSaving(
            @PathVariable Long userId,
            @PathVariable Long goalId,
            @RequestBody Saving saving) {

        return savingService.saveSaving(
                userId,
                goalId,
                saving
        );
    }


    // Get Savings for User
    @GetMapping("/user/{userId}")
    public List<Saving> getSavingsByUser(
            @PathVariable Long userId) {

        return savingService.getSavingsByUser(userId);
    }


    // Get Saving By ID
    @GetMapping("/{id}")
    public Saving getSavingById(
            @PathVariable Long id) {

        return savingService.getSavingById(id);
    }


    // Update Saving
    @PutMapping("/{id}")
    public Saving updateSaving(
            @PathVariable Long id,
            @RequestBody Saving saving) {

        return savingService.updateSaving(
                id,
                saving
        );
    }


    // Delete Saving
    @DeleteMapping("/{id}")
    public String deleteSaving(
            @PathVariable Long id) {

        return savingService.deleteSaving(id);
    }

}