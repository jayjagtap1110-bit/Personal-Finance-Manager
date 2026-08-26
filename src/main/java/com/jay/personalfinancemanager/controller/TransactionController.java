package com.jay.personalfinancemanager.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jay.personalfinancemanager.entity.Expense;
import com.jay.personalfinancemanager.entity.Income;
import com.jay.personalfinancemanager.entity.Saving;
import com.jay.personalfinancemanager.entity.User;
import com.jay.personalfinancemanager.repository.ExpenseRepository;
import com.jay.personalfinancemanager.repository.IncomeRepository;
import com.jay.personalfinancemanager.repository.SavingRepository;
import com.jay.personalfinancemanager.repository.UserRepository;

@RestController
@RequestMapping("/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private SavingRepository savingRepository;


    @GetMapping("/{userId}")
    public List<TransactionResponse> getTransactions(
            @PathVariable Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));


        List<TransactionResponse> transactions =
                new ArrayList<>();


        // ==============================
        // INCOME
        // ==============================

        List<Income> incomes =
                incomeRepository.findByUser(user);

        for (Income income : incomes) {

            transactions.add(
                    new TransactionResponse(
                            income.getId(),
                            "Income",
                            income.getAmount(),
                            "Income"
                    )
            );
        }


        // ==============================
        // EXPENSE
        // ==============================

        List<Expense> expenses =
                expenseRepository.findByUser(user);

        for (Expense expense : expenses) {

            transactions.add(
                    new TransactionResponse(
                            expense.getId(),
                            "Expense",
                            expense.getAmount(),
                            "Expense"
                    )
            );
        }


        // ==============================
        // SAVING
        // ==============================

        List<Saving> savings =
                savingRepository.findByUser(user);

        for (Saving saving : savings) {

            transactions.add(
                    new TransactionResponse(
                            saving.getId(),
                            "Saving",
                            saving.getAmount(),
                            "Saving"
                    )
            );
        }


        return transactions;
    }


    // ==========================================
    // TRANSACTION RESPONSE DTO
    // ==========================================

    public static class TransactionResponse {

        private Long id;

        private String type;

        private Double amount;

        private String description;


        public TransactionResponse() {
        }


        public TransactionResponse(
                Long id,
                String type,
                Double amount,
                String description) {

            this.id = id;
            this.type = type;
            this.amount = amount;
            this.description = description;
        }


        public Long getId() {
            return id;
        }


        public void setId(Long id) {
            this.id = id;
        }


        public String getType() {
            return type;
        }


        public void setType(String type) {
            this.type = type;
        }


        public Double getAmount() {
            return amount;
        }


        public void setAmount(Double amount) {
            this.amount = amount;
        }


        public String getDescription() {
            return description;
        }


        public void setDescription(
                String description) {

            this.description = description;
        }
    }
}