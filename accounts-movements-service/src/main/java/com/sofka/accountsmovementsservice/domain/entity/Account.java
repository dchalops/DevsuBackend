package com.sofka.accountsmovementsservice.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
public class Account extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String accountNumber;

    private String accountType;
    private Double InitialBalance;
    private Boolean status;


}

