package com.sofka.accountsmovementsservice.domain.entity;

import lombok.*;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
public class Transaction extends BaseEntity {
    
    private LocalDate date;
    private String movementType;
    private Double value;
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "client_id", nullable = false)
    private String clientId;

    public Transaction(LocalDate date, String movementType, Double value, Double balance, Account account, String clientId) {
        this.date = date; 
        this.movementType = movementType; 
        this.value = value; 
        this.balance = balance; 
        this.account = account;
        this.clientId = clientId;
    }
}
