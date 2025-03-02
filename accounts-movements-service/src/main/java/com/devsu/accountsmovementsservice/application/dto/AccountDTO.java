package com.devsu.accountsmovementsservice.application.dto;

import lombok.AllArgsConstructor;
import javax.validation.constraints.NotNull;
import lombok.Data;


@Data
@AllArgsConstructor
public class AccountDTO {
    @NotNull(message = "Account number is required")
    private String accountNumber;

    @NotNull(message = "Account type is required")
    private String accountType;

    @NotNull(message = "Initial balance is required")
    private Double initialBalance;

    private Boolean status;
}



