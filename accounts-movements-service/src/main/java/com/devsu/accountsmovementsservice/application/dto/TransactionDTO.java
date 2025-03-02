package com.devsu.accountsmovementsservice.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Builder;
import java.time.LocalDate;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TransactionDTO implements Serializable {
    private String accountNumber;
    private String transactionType;
    private Double amount;
    private LocalDate date;
    private String clientId;
    private Double balance;
}

