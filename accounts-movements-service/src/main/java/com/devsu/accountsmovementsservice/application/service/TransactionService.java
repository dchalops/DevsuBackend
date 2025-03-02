package com.devsu.accountsmovementsservice.application.service;

import com.devsu.accountsmovementsservice.infrastructure.exc.InsufficientFundsException;
import com.devsu.accountsmovementsservice.domain.repository.TransactionRepository;
import com.devsu.accountsmovementsservice.domain.repository.AccountRepository;
import com.devsu.accountsmovementsservice.domain.entity.Transaction;
import com.devsu.accountsmovementsservice.domain.entity.Account;
import com.devsu.accountsmovementsservice.application.dto.TransactionDTO;
import lombok.RequiredArgsConstructor;
import com.devsu.accountsmovementsservice.application.dto.AccountReportDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Transactional
    public Transaction registerTransaction(TransactionDTO transactionDTO) {
        if (transactionDTO.getAmount() == null) {
            throw new IllegalArgumentException("Transaction amount cannot be null");
        }

        Account account = accountRepository.findByAccountNumber(transactionDTO.getAccountNumber())
                .orElseThrow(() -> new EntityNotFoundException("Account not found with number: " + transactionDTO.getAccountNumber()));

        Double newBalance = account.getInitialBalance() + transactionDTO.getAmount();

        if (newBalance < 0) {
            throw new InsufficientFundsException("Insufficient funds for transaction");
        }

        account.setInitialBalance(newBalance);
        accountRepository.save(account);

        Transaction transaction = new Transaction(LocalDate.now(), transactionDTO.getTransactionType(), 
                                                  transactionDTO.getAmount(), newBalance, account);
        transactionRepository.save(transaction);

        publishTransactionEvent(transactionDTO);

        return transaction;
    }

    private void publishTransactionEvent(TransactionDTO transactionDTO) {
        TransactionDTO eventDTO = TransactionDTO.builder()
            .accountNumber(transactionDTO.getAccountNumber())
            .transactionType(transactionDTO.getTransactionType())
            .amount(transactionDTO.getAmount())
            .balance(transactionDTO.getBalance())
            .date(LocalDate.now())
            .clientId(transactionDTO.getClientId())
            .build();
    
        rabbitTemplate.convertAndSend("transaction.queue", eventDTO);
    }
    

    public List<TransactionDTO> getReports(LocalDate dateStart, LocalDate dateEnd, String clientId) {
        List<Transaction> transactions = transactionRepository
                .findByAccount_ClientIdAndDateBetween(clientId, dateStart, dateEnd);

        return transactions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private TransactionDTO convertToDTO(Transaction transaction) {
        return new TransactionDTO(
            transaction.getAccount().getAccountNumber(),
            transaction.getTransactionType(),
            transaction.getAmount(),
            transaction.getDate(),
            transaction.getAccount().getClientId().toString(),
            transaction.getBalance()
        );
    }    

    @Transactional(readOnly = true)
    public List<AccountReportDTO> getAccountReports(LocalDate dateStart, LocalDate dateEnd, String clientId) {
        UUID clientUUID;
        try {
            clientUUID = UUID.fromString(clientId);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid clientId format. It should be a valid UUID.");
        }

        List<Account> accounts = accountRepository.findByClientId(clientUUID); 

        return accounts.stream()
            .map(account -> {
                List<Transaction> transactions = transactionRepository.findByAccountAndDateBetween(account, dateStart, dateEnd);
                Double finalBalance = transactions.stream()
                    .reduce(account.getInitialBalance(), (balance, transaction) -> balance + transaction.getAmount(), Double::sum);
                return new AccountReportDTO(account, transactions, finalBalance);
            })
            .collect(Collectors.toList());
    }

}
