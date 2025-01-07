package com.example.money_tracker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Data
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String note;
    private BigDecimal amount;
    private LocalDateTime dateTime;
    private TransactionType transactionType;
    private String category;
    private String paymentMethod;


}
