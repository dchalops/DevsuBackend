package com.devsu.accountsmovementsservice.application.service;

import com.devsu.accountsmovementsservice.application.dto.ClientDTO;
import com.devsu.accountsmovementsservice.domain.entity.Account;
import com.devsu.accountsmovementsservice.domain.repository.AccountRepository;
import com.devsu.accountsmovementsservice.infrastructure.client.ClientServiceClient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ClientServiceClient clientServiceClient;

    @InjectMocks
    private AccountService accountService;

    private Account account;
    private String clientId;

    @BeforeEach
    void setUp() {
        clientId = "e8545826-3518-4cfe-8b40-6d6208b6931b";

        account = new Account();
        account.setAccountNumber("123456789");
        account.setAccountType("SAVINGS");
        account.setInitialBalance(5000.0);
        account.setStatus(true);
    }

    @Test
    void shouldSaveAccountSuccessfully() {
        when(clientServiceClient.getClientById(clientId))
                .thenReturn(new ClientDTO(clientId, "John Doe", "M", 37, "1718726084", "Miami", "0999112498"));

        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account savedAccount = accountService.saveAccountClient(account, clientId);

        assertNotNull(savedAccount);
        assertEquals(account.getAccountNumber(), savedAccount.getAccountNumber());
        assertEquals(account.getAccountType(), savedAccount.getAccountType());
    }

    @Test
    void shouldFindAccountByAccountNumber() {
        when(accountRepository.findByAccountNumber(account.getAccountNumber())).thenReturn(Optional.of(account));

        Account foundAccount = accountService.getAccountByNumber(account.getAccountNumber());

        assertNotNull(foundAccount);
        assertEquals(account.getAccountNumber(), foundAccount.getAccountNumber());
    }

    @Test
    void shouldThrowExceptionWhenAccountNotFound() {
        when(accountRepository.findByAccountNumber("999999")).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> accountService.getAccountByNumber("999999"));
        assertEquals("Account not found", exception.getMessage());
    }
}
