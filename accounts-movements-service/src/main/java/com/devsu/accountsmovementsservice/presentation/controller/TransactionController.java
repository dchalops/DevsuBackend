package com.devsu.accountsmovementsservice.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsu.accountsmovementsservice.application.dto.TransactionDTO;
import com.devsu.accountsmovementsservice.application.dto.AccountReportDTO;
import com.devsu.accountsmovementsservice.application.service.TransactionService;
import com.devsu.accountsmovementsservice.domain.entity.Transaction;
import com.devsu.accountsmovementsservice.application.dto.ApiResponse;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<ApiResponse<Transaction>> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        try {
            Transaction transaction = transactionService.registerTransaction(transactionDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Transaction registered successfully.", transaction));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error registering transaction: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    @GetMapping("/reports")
    public ResponseEntity<ApiResponse<List<AccountReportDTO>>> getAccountReports(
            @RequestParam("date_start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStart,
            @RequestParam("date_end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEnd,
            @RequestParam("client") String clientId) {
        try {
            List<AccountReportDTO> reports = transactionService.getAccountReports(dateStart, dateEnd, clientId);
            return ResponseEntity.ok(ApiResponse.success("Account reports retrieved successfully.", reports));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error retrieving account reports: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }
}


