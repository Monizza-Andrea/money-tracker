package com.example.money_tracker.repository;

import com.example.money_tracker.model.Transaction;
import com.example.money_tracker.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByDateTimeBetween(LocalDateTime start,LocalDateTime end);
    List<Transaction> findByTransactionType(TransactionType transactionType);
}
