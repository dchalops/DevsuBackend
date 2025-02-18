package com.sofka.accountsmovementsservice.application.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sofka.accountsmovementsservice.domain.entity.*;
import com.sofka.accountsmovementsservice.infrastructure.exc.NotFoundException;
import javax.persistence.EntityNotFoundException;
import com.sofka.accountsmovementsservice.domain.repository.AccountRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account saveAccount(Account cuenta) {
        return accountRepository.save(cuenta);
    }

    public Account getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new NotFoundException("Account not found"));
    }
}

