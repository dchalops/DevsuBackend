package com.devsu.accountsmovementsservice.application.dto;

import lombok.*;
import com.devsu.accountsmovementsservice.domain.entity.Account;
import com.devsu.accountsmovementsservice.domain.entity.Transaction;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class AccountReportDTO {
    private Account account;
    private List<TransactionDTO> transactions;
    private Double finalBalance;

    public AccountReportDTO(Account account, List<Transaction> transactions, Double finalBalance) {
        this.account = account;
        this.transactions = transactions.stream().map(Transaction::convertToDTO).collect(Collectors.toList());
        this.finalBalance = finalBalance;
    }

}