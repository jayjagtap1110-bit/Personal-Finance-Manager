package com.jay.personalfinancemanager.dto;

import java.util.List;

public class DashboardSummary {

    private Double totalIncome;
    private Double totalExpense;
    private Double totalSavings;
    private Double remainingBalance;

    private Double healthScore;
    private String healthStatus;

    private Double budgetPercentage;
    private String budgetTitle;
    private String budgetMessage;

    private List<GoalProgress> goalProgress;


    public DashboardSummary() {
    }


    public DashboardSummary(
            Double totalIncome,
            Double totalExpense,
            Double totalSavings,
            Double remainingBalance) {

        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.totalSavings = totalSavings;
        this.remainingBalance = remainingBalance;
    }


    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }


    public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }


    public Double getTotalSavings() {
        return totalSavings;
    }

    public void setTotalSavings(Double totalSavings) {
        this.totalSavings = totalSavings;
    }


    public Double getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(Double remainingBalance) {
        this.remainingBalance = remainingBalance;
    }


    public Double getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(Double healthScore) {
        this.healthScore = healthScore;
    }


    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }


    public Double getBudgetPercentage() {
        return budgetPercentage;
    }

    public void setBudgetPercentage(Double budgetPercentage) {
        this.budgetPercentage = budgetPercentage;
    }


    public String getBudgetTitle() {
        return budgetTitle;
    }

    public void setBudgetTitle(String budgetTitle) {
        this.budgetTitle = budgetTitle;
    }


    public String getBudgetMessage() {
        return budgetMessage;
    }

    public void setBudgetMessage(String budgetMessage) {
        this.budgetMessage = budgetMessage;
    }


    public List<GoalProgress> getGoalProgress() {
        return goalProgress;
    }

    public void setGoalProgress(
            List<GoalProgress> goalProgress) {

        this.goalProgress = goalProgress;
    }


    // ==========================================
    // GOAL PROGRESS DTO
    // ==========================================

    public static class GoalProgress {

        private Long goalId;
        private String goalName;
        private Double targetAmount;
        private Double savedAmount;
        private Double percentage;


        public GoalProgress() {
        }


        public GoalProgress(
                Long goalId,
                String goalName,
                Double targetAmount,
                Double savedAmount,
                Double percentage) {

            this.goalId = goalId;
            this.goalName = goalName;
            this.targetAmount = targetAmount;
            this.savedAmount = savedAmount;
            this.percentage = percentage;
        }


        public Long getGoalId() {
            return goalId;
        }

        public void setGoalId(Long goalId) {
            this.goalId = goalId;
        }


        public String getGoalName() {
            return goalName;
        }

        public void setGoalName(String goalName) {
            this.goalName = goalName;
        }


        public Double getTargetAmount() {
            return targetAmount;
        }

        public void setTargetAmount(
                Double targetAmount) {

            this.targetAmount = targetAmount;
        }


        public Double getSavedAmount() {
            return savedAmount;
        }

        public void setSavedAmount(
                Double savedAmount) {

            this.savedAmount = savedAmount;
        }


        public Double getPercentage() {
            return percentage;
        }

        public void setPercentage(
                Double percentage) {

            this.percentage = percentage;
        }
    }
}