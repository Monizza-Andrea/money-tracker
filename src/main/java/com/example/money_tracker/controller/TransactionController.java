package com.example.money_tracker.controller;

import com.example.money_tracker.model.Transaction;
import com.example.money_tracker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
        return ResponseEntity.ok(this.transactionService.addTransaction(transaction));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTransaction(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.transactionService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transazione non trovata");
        }
    }

    @GetMapping("/total-income")
    public ResponseEntity<BigDecimal> getTotalIncome(@RequestParam(defaultValue = "2020") int year, @RequestParam(defaultValue = "6") int month) {
        try {
            return ResponseEntity.ok(transactionService.getTotalIncome(year, month));
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BigDecimal.ZERO);
        }
    }

    @GetMapping("/total-expense")
    public ResponseEntity<BigDecimal> getTotalExpense(@RequestParam int year, @RequestParam int month) {
        try {
            return ResponseEntity.ok(transactionService.getTotalExpense(year, month));
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BigDecimal.ZERO);
        }
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Invalid parameter: " + ex.getName() + ". Expected type: " + ex.getRequiredType());
    }


}
