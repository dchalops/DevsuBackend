package com.devsu.clientspersonsservice.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import com.devsu.clientspersonsservice.application.dto.ApiResponse;

import com.devsu.clientspersonsservice.application.service.ClientService;
import com.devsu.clientspersonsservice.domain.entity.Client;

@RestController
@RequestMapping("/v1/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ApiResponse<Client>> createClient(@RequestBody Client client) {
        try {
            Client savedClient = clientService.saveClient(client);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Client has been successfully created.", savedClient));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("An error occurred while creating the client.", HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Client>> updateClient(@PathVariable String id, @RequestBody Client client) {
        try {
            Client updatedClient = clientService.updateClient(id, client);
            return ResponseEntity.ok(ApiResponse.success("Client with ID " + id + " has been successfully updated.", updatedClient));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Client with ID " + id + " not found.", HttpStatus.NOT_FOUND, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("An error occurred while updating the client.", HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteClient(@PathVariable UUID id) {
        try {
            clientService.setIsDelete(id.toString());
            return ResponseEntity.ok(ApiResponse.success("Client with ID " + id + " has been successfully marked as deleted.", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to delete client with ID " + id, HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Iterable<Client>>> getClients() {
        try {
            Iterable<Client> clients = clientService.findAllClients();
            return ResponseEntity.ok(ApiResponse.success("Clients retrieved successfully.", clients));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("An error occurred while retrieving clients.", HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Client>> getClient(@PathVariable UUID id) {
        try {
            Client client = clientService.findClientById(id.toString());
            return ResponseEntity.ok(ApiResponse.success("Client retrieved successfully.", client));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Client with ID " + id + " not found.", HttpStatus.NOT_FOUND, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("An error occurred while retrieving the client.", HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

}
