package com.jay.personalfinancemanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jay.personalfinancemanager.dto.DashboardSummary;
import com.jay.personalfinancemanager.entity.Expense;
import com.jay.personalfinancemanager.entity.Income;
import com.jay.personalfinancemanager.entity.Saving;
import com.jay.personalfinancemanager.entity.SavingGoal;
import com.jay.personalfinancemanager.entity.User;
import com.jay.personalfinancemanager.repository.ExpenseRepository;
import com.jay.personalfinancemanager.repository.IncomeRepository;
import com.jay.personalfinancemanager.repository.SavingGoalRepository;
import com.jay.personalfinancemanager.repository.SavingRepository;
import com.jay.personalfinancemanager.repository.UserRepository;

@Service
public class DashboardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private SavingRepository savingRepository;

    @Autowired
    private SavingGoalRepository savingGoalRepository;


    public DashboardSummary getDashboardSummary(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));


        // ==============================
        // GET USER DATA
        // ==============================

        List<Income> incomes =
                incomeRepository.findByUser(user);

        List<Expense> expenses =
                expenseRepository.findByUser(user);

        List<Saving> savings =
                savingRepository.findByUser(user);

        List<SavingGoal> goals =
                savingGoalRepository.findByUser(user);


        // ==============================
        // TOTAL INCOME
        // ==============================

        double totalIncome = incomes.stream()
                .filter(income ->
                        income.getAmount() != null)
                .mapToDouble(Income::getAmount)
                .sum();


        // ==============================
        // TOTAL EXPENSE
        // ==============================

        double totalExpense = expenses.stream()
                .filter(expense ->
                        expense.getAmount() != null)
                .mapToDouble(Expense::getAmount)
                .sum();


        // ==============================
        // TOTAL SAVINGS
        // ==============================

        double totalSavings = savings.stream()
                .filter(saving ->
                        saving.getAmount() != null)
                .mapToDouble(Saving::getAmount)
                .sum();


        // ==============================
        // REMAINING BALANCE
        // ==============================

        double remainingBalance =
                totalIncome
                - totalExpense
                - totalSavings;


        // ==============================
        // FINANCIAL HEALTH SCORE
        // ==============================

        double healthScore = 0.0;

        if (totalIncome > 0) {

            double expenseRatio =
                    totalExpense / totalIncome;

            double savingRatio =
                    totalSavings / totalIncome;

            healthScore =
                    100
                    - (expenseRatio * 70)
                    + (savingRatio * 30);
        }


        // Round score

        healthScore =
                Math.round(healthScore);


        // Keep score between 0 and 100

        if (healthScore < 0) {

            healthScore = 0;
        }

        if (healthScore > 100) {

            healthScore = 100;
        }


        // ==============================
        // HEALTH STATUS
        // ==============================

        String healthStatus;


        if (healthScore >= 80) {

            healthStatus = "Excellent";

        }

        else if (healthScore >= 60) {

            healthStatus = "Good";

        }

        else if (healthScore >= 40) {

            healthStatus = "Fair";

        }

        else {

            healthStatus = "Needs Improvement";
        }


        // ==============================
        // BUDGET PERCENTAGE
        // ==============================

        double budgetPercentage = 0.0;

        if (totalIncome > 0) {

            double totalUsed =
                    totalExpense + totalSavings;

            budgetPercentage =
                    (totalUsed / totalIncome) * 100.0;
        }


        // Round budget percentage

        budgetPercentage =
                Math.round(
                        budgetPercentage * 10.0
                ) / 10.0;


        // ==============================
        // BUDGET TITLE + MESSAGE
        // ==============================

        String budgetTitle;
        String budgetMessage;


        if (totalIncome <= 0) {

            budgetTitle =
                    "Budget Information";

            budgetMessage =
                    "Add income to start tracking your budget.";
        }

        else if (budgetPercentage < 50) {

            budgetTitle =
                    "Budget Status";

            budgetMessage =
                    "Your expenses and savings are well within your income. Keep maintaining good financial habits.";
        }

        else if (budgetPercentage < 75) {

            budgetTitle =
                    "Budget Notice";

            budgetMessage =
                    "You are using a moderate portion of your income. Keep an eye on unnecessary expenses.";
        }

        else if (budgetPercentage < 90) {

            budgetTitle =
                    "Budget Notice";

            budgetMessage =
                    "You have used a large portion of your income. Try to control unnecessary expenses.";
        }

        else if (budgetPercentage <= 100) {

            budgetTitle =
                    "High Spending Alert";

            budgetMessage =
                    "You are close to using your entire income. Avoid unnecessary expenses and review your spending.";
        }

        else {

            budgetTitle =
                    "Budget Exceeded";

            budgetMessage =
                    "Your expenses and savings are higher than your available income. Your remaining balance is negative. Reduce spending and review your finances.";
        }


        // ==============================
        // GOAL PROGRESS
        // ==============================

        List<DashboardSummary.GoalProgress>
                goalProgressList = new ArrayList<>();


        for (SavingGoal goal : goals) {

            double savedAmount = savings.stream()

                    .filter(saving ->
                            saving.getGoal() != null)

                    .filter(saving ->
                            saving.getGoal()
                                    .getId()
                                    .equals(goal.getId()))

                    .filter(saving ->
                            saving.getAmount() != null)

                    .mapToDouble(Saving::getAmount)

                    .sum();


            double targetAmount =
                    goal.getTargetAmount() != null
                            ? goal.getTargetAmount()
                            : 0.0;


            double percentage = 0.0;


            if (targetAmount > 0) {

                percentage =
                        (savedAmount / targetAmount) * 100.0;
            }


            // Don't allow progress above 100%

            if (percentage > 100.0) {

                percentage = 100.0;
            }


            DashboardSummary.GoalProgress
                    goalProgress =
                    new DashboardSummary.GoalProgress(

                            goal.getId(),

                            goal.getGoalName(),

                            targetAmount,

                            savedAmount,

                            percentage
                    );


            goalProgressList.add(
                    goalProgress
            );
        }


        // ==============================
        // CREATE SUMMARY
        // ==============================

        DashboardSummary summary =
                new DashboardSummary(

                        totalIncome,

                        totalExpense,

                        totalSavings,

                        remainingBalance
                );


        // ==============================
        // ADD FINANCIAL HEALTH
        // ==============================

        summary.setHealthScore(
                healthScore
        );

        summary.setHealthStatus(
                healthStatus
        );


        // ==============================
        // ADD BUDGET DATA
        // ==============================

        summary.setBudgetPercentage(
                budgetPercentage
        );

        summary.setBudgetTitle(
                budgetTitle
        );

        summary.setBudgetMessage(
                budgetMessage
        );


        // ==============================
        // ADD GOAL PROGRESS
        // ==============================

        summary.setGoalProgress(
                goalProgressList
        );


        return summary;
    }
}