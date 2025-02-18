package com.sofka.accountsmovementsservice.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sofka.accountsmovementsservice.application.dto.TransactionDTO;
import com.sofka.accountsmovementsservice.application.service.TransactionService;
import com.sofka.accountsmovementsservice.domain.entity.Transaction;

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
    public ResponseEntity<Transaction> movementService(@RequestBody TransactionDTO transactionDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.registerTransaction(transactionDTO));
    }

    @GetMapping("/reports")
    public ResponseEntity<List<TransactionDTO>> getReports(
            @RequestParam("date_start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStart,
            @RequestParam("date_end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEnd,
            @RequestParam("clientId") String clientId) {
        
        List<TransactionDTO> reports = transactionService.getReports(dateStart, dateEnd, clientId);
        return ResponseEntity.ok(reports);
    }
    
    
}

