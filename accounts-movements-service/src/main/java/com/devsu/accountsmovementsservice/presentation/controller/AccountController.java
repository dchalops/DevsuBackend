package com.devsu.accountsmovementsservice.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsu.accountsmovementsservice.application.service.AccountService;
import com.devsu.accountsmovementsservice.domain.entity.Account;
import com.devsu.accountsmovementsservice.application.dto.ApiResponse;

import org.springframework.http.HttpStatus;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<ApiResponse<Account>> getAccount(@PathVariable String accountNumber) {
        try {
            Account account = accountService.getAccount(accountNumber);
            return ResponseEntity.ok(ApiResponse.success("Account retrieved successfully.", account));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Account with number " + accountNumber + " not found.", HttpStatus.NOT_FOUND, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("An error occurred while retrieving the account.", HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Account>> createAccount( 
            @RequestBody Account account, 
            @RequestParam("client_id") String clientId) {
        try {
            Account savedAccount = accountService.saveAccountClient(account, clientId);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Account created successfully.", savedAccount));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("An error occurred while creating the account.", HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    @PutMapping("/{accountNumber}")
    public ResponseEntity<ApiResponse<Account>> updateAccount(
            @RequestBody Account account, @PathVariable String accountNumber) {
        try {
            Account updatedAccount = accountService.saveAccount(account, accountNumber);
            return ResponseEntity.ok(ApiResponse.success("Account updated successfully.", updatedAccount));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Account with number " + account.getAccountNumber() + " not found.", HttpStatus.NOT_FOUND, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("An error occurred while updating the account.", HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<ApiResponse<Void>> deleteAccount(@PathVariable String accountNumber) {
        try {
            accountService.deleteAccount(accountNumber);
            return ResponseEntity.ok(ApiResponse.success("Account with number " + accountNumber + " has been successfully deleted.", null));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Account with number " + accountNumber + " not found.", HttpStatus.NOT_FOUND, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("An error occurred while deleting the account.", HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }
}
