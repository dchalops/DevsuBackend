package com.sofka.clientspersonsservice.application.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionDTO implements Serializable { 
    private String clientId;
    private String accountNumber;
    private String transactionType;
    private Double amount;
    private LocalDate date;
}