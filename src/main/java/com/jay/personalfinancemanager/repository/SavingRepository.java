package com.jay.personalfinancemanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jay.personalfinancemanager.entity.Saving;
import com.jay.personalfinancemanager.entity.SavingGoal;
import com.jay.personalfinancemanager.entity.User;

@Repository
public interface SavingRepository extends JpaRepository<Saving, Long> {

    List<Saving> findByUser(User user);

    List<Saving> findByGoal(SavingGoal goal);

}