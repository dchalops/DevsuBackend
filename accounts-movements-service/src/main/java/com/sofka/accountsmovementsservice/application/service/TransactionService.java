package com.sofka.accountsmovementsservice.application.service;

import com.sofka.accountsmovementsservice.infrastructure.exc.InsufficientFundsException;
import com.sofka.accountsmovementsservice.domain.repository.TransactionRepository;
import com.sofka.accountsmovementsservice.domain.repository.AccountRepository;
import com.sofka.accountsmovementsservice.domain.entity.Transaction;
import com.sofka.accountsmovementsservice.domain.entity.Account;
import com.sofka.accountsmovementsservice.application.dto.TransactionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final RabbitTemplate rabbitTemplate;

    public Transaction registerTransaction(TransactionDTO transactionDTO) {
        Account account = accountRepository.findByAccountNumber(transactionDTO.getAccountNumber())
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        Double newBalance = account.getInitialBalance() + transactionDTO.getAmount();
        if (newBalance < 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        account.setInitialBalance(newBalance);
        accountRepository.save(account);

        Transaction transaction = new Transaction(LocalDate.now(), transactionDTO.getMovementType(), 
                                                  transactionDTO.getAmount(), newBalance, account, transactionDTO.getClientId());
        transactionRepository.save(transaction);

        TransactionDTO eventDTO = new TransactionDTO(
            transactionDTO.getClientId(),
            transactionDTO.getAccountNumber(),
            transactionDTO.getMovementType(),
            transactionDTO.getAmount(),
            LocalDate.now()
        );

        rabbitTemplate.convertAndSend("transaction.queue", eventDTO);
        return transaction;
    }

    public List<TransactionDTO> getReports(LocalDate dateStart, LocalDate dateEnd, String clientId) {
        List<Transaction> transactions = transactionRepository
                .findByClientIdAndDateBetween(clientId, dateStart, dateEnd);

        return transactions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private TransactionDTO convertToDTO(Transaction transaction) {
        return new TransactionDTO(
                transaction.getClientId(),
                transaction.getAccount().getAccountNumber(),
                transaction.getMovementType(),
                transaction.getValue(),
                transaction.getDate()
        );
    }
}
