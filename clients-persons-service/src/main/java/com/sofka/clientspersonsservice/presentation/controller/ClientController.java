package com.sofka.clientspersonsservice.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import com.sofka.clientspersonsservice.application.service.ClientService;
import com.sofka.clientspersonsservice.domain.entity.Client;

@RestController
@RequestMapping("/v1/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clienteService;

    @PostMapping
    public ResponseEntity<Client> createCliente(@RequestBody Client cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.saveClient(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getCliente(@PathVariable String id) {
        return ResponseEntity.ok(clienteService.findClientById(id));
    }
}
