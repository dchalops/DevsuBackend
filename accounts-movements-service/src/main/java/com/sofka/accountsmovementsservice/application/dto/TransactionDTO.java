package com.sofka.accountsmovementsservice.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionDTO implements Serializable {
    private String clientId;
    private String accountNumber;
    private String movementType;
    private Double amount;
    private LocalDate date;
}
