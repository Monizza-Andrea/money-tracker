package com.example.money_tracker.service;

import com.example.money_tracker.model.Transaction;
import com.example.money_tracker.model.TransactionType;
import com.example.money_tracker.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public List<Transaction> getTransactionByMonth(int year, int month) {
        LocalDateTime start = LocalDateTime.of(year, month,1,0,0);
        LocalDateTime end = start.plusMonths(1);
        return transactionRepository.findByDateTimeBetween(start, end);
    }

    public BigDecimal getTotalExpense(int year, int month) {
        List<Transaction> transactions = getTransactionsByMonth(year, month);
        return transactions.stream()
                .filter(t -> t.getTransactionType() == TransactionType.EXPENSE)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public BigDecimal getTotalIncome(int year, int month) {
        List<Transaction> transactions = getTransactionsByMonth(year, month);
        return transactions.stream()
                .filter(t -> t.getTransactionType() == TransactionType.INCOME)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
