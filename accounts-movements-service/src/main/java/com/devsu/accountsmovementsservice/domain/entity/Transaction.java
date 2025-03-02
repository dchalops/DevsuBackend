package com.devsu.accountsmovementsservice.domain.entity;

import lombok.*;

import java.time.LocalDate;
import javax.persistence.*;

import com.devsu.accountsmovementsservice.application.dto.TransactionDTO;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
public class Transaction extends BaseEntity {
    
    private LocalDate date;
    private String transactionType;
    private Double amount;
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    
    public Transaction(LocalDate date, String transactionType, Double amount, Double balance, Account account) {
        this.date = date; 
        this.transactionType = transactionType; 
        this.amount = amount; 
        this.balance = balance; 
        this.account = account;
    }

    public TransactionDTO convertToDTO() {
        return TransactionDTO.builder()
                .accountNumber(account.getAccountNumber())
                .transactionType(transactionType)
                .amount(amount)
                .date(date)
                .clientId(account.getClientId().toString())
                .balance(balance)
                .build();
    }
}
