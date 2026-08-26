package com.jay.personalfinancemanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jay.personalfinancemanager.entity.Income;
import com.jay.personalfinancemanager.entity.User;
import com.jay.personalfinancemanager.repository.IncomeRepository;
import com.jay.personalfinancemanager.repository.UserRepository;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private UserRepository userRepository;

    // Add Income for a specific user
    public Income saveIncome(Long userId, Income income) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        income.setUser(user);

        return incomeRepository.save(income);
    }

    // Get Income for a specific user
    public List<Income> getIncomeByUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        return incomeRepository.findByUser(user);
    }

    // Get Income By ID
    public Income getIncomeById(Long id) {

        return incomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Income Not Found"));
    }

    // Update Income
    public Income updateIncome(Long id, Income updatedIncome) {

        Income existingIncome = incomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Income Not Found"));

        existingIncome.setIncomeFrom(updatedIncome.getIncomeFrom());
        existingIncome.setAmount(updatedIncome.getAmount());
        existingIncome.setCurrency(updatedIncome.getCurrency());
        existingIncome.setDate(updatedIncome.getDate());
        existingIncome.setDescription(updatedIncome.getDescription());

        return incomeRepository.save(existingIncome);
    }

    // Delete Income
    public String deleteIncome(Long id) {

        if (incomeRepository.existsById(id)) {

            incomeRepository.deleteById(id);

            return "Income Deleted Successfully";
        }

        return "Income Not Found";
    }
}