package com.jay.personalfinancemanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jay.personalfinancemanager.entity.Expense;
import com.jay.personalfinancemanager.entity.User;
import com.jay.personalfinancemanager.repository.ExpenseRepository;
import com.jay.personalfinancemanager.repository.UserRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    // Add Expense
    public Expense saveExpense(Long userId, Expense expense) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        expense.setUser(user);

        return expenseRepository.save(expense);
    }

    // Get Expense for User
    public List<Expense> getExpenseByUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        return expenseRepository.findByUser(user);
    }

    // Get Expense By ID
    public Expense getExpenseById(Long id) {

        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense Not Found"));
    }

    // Update Expense
    public Expense updateExpense(Long id, Expense updatedExpense) {

        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense Not Found"));

        existingExpense.setExpenseFrom(updatedExpense.getExpenseFrom());
        existingExpense.setCategory(updatedExpense.getCategory());
        existingExpense.setAmount(updatedExpense.getAmount());
        existingExpense.setCurrency(updatedExpense.getCurrency());
        existingExpense.setDate(updatedExpense.getDate());
        existingExpense.setDescription(updatedExpense.getDescription());

        return expenseRepository.save(existingExpense);
    }

    // Delete Expense
    public String deleteExpense(Long id) {

        if (expenseRepository.existsById(id)) {

            expenseRepository.deleteById(id);

            return "Expense Deleted Successfully";
        }

        return "Expense Not Found";
    }
}