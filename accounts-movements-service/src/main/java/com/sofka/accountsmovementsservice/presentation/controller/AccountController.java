package com.sofka.accountsmovementsservice.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sofka.accountsmovementsservice.application.service.AccountService;
import com.sofka.accountsmovementsservice.domain.entity.Account;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.saveAccount(account));
    }
}
