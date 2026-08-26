package com.jay.personalfinancemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jay.personalfinancemanager.dto.DashboardSummary;
import com.jay.personalfinancemanager.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/summary/{userId}")
    public DashboardSummary getDashboardSummary(
            @PathVariable Long userId) {

        return dashboardService.getDashboardSummary(userId);
    }
}