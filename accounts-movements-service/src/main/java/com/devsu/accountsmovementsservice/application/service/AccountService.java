package com.devsu.accountsmovementsservice.application.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.devsu.accountsmovementsservice.infrastructure.client.ClientServiceClient;
import com.devsu.accountsmovementsservice.application.dto.ClientDTO;
import com.devsu.accountsmovementsservice.domain.entity.*;
import com.devsu.accountsmovementsservice.infrastructure.exc.NotFoundException;
import javax.persistence.EntityNotFoundException;
import com.devsu.accountsmovementsservice.domain.repository.AccountRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final ClientServiceClient clientFeignClient;

    private final AccountRepository accountRepository;

    public Account saveAccountClient(Account account, String clientId) {
        ClientDTO clientDTO = clientFeignClient.getClientById(clientId);

        if (clientDTO == null) {
            throw new EntityNotFoundException("Client with ID " + clientId + " not found.");
        }

        account.setClientId(UUID.fromString(clientId)); 

        return accountRepository.save(account);
    }

    public Account saveAccount(Account account, String clientId) {
        ClientDTO clientDTO = clientFeignClient.getClientById(clientId);

        if (clientDTO == null) {
            throw new EntityNotFoundException("Client with ID " + clientId + " not found.");
        }
        Account cuenta = accountRepository.findByAccountNumber(account.getAccountNumber())
                .orElseThrow(() -> new EntityNotFoundException("Account not found with number: " + account.getAccountNumber()));
        
        cuenta.setAccountNumber(account.getAccountNumber());
        cuenta.setInitialBalance(account.getInitialBalance());
        cuenta.setStatus(account.getStatus());

        //account.setClientId(UUID.fromString(clientId));
        return accountRepository.save(cuenta);
    }

    public Account getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new NotFoundException("Account not found"));
    }

    public Account getAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with number: " + accountNumber));
    }

    public void deleteAccount(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with number: " + accountNumber));
        
        account.setIsDelete(true);
        accountRepository.save(account);
    }
}

