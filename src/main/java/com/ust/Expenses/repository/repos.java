package com.ust.Expenses.repository;

import com.ust.Expenses.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface repos extends JpaRepository<Expense, UUID> {
}
