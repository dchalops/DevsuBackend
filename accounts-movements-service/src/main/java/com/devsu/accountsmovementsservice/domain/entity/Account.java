package com.devsu.accountsmovementsservice.domain.entity;

import lombok.*;

import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
public class Account extends BaseEntity {
    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    private String accountType;
    @Column(name = "initial_balance", nullable = false)
    private Double InitialBalance;
    private Boolean status;
    private Boolean isDelete = false;

    @Column(name = "client_id", nullable = false)
    private UUID clientId;
}

