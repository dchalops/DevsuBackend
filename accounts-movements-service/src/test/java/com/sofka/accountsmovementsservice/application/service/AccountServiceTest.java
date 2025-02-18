package com.sofka.accountsmovementsservice.application.service;

import com.sofka.accountsmovementsservice.domain.entity.Account;
import com.sofka.accountsmovementsservice.domain.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
        account.setAccountNumber("123456789");
        account.setAccountType("SAVINGS");
        account.setInitialBalance(5000.0);
        account.setStatus(true);
    }

    @Test
    void shouldSaveAccountSuccessfully() {
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account savedAccount = accountService.saveAccount(account);

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
