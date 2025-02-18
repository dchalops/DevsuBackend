package com.sofka.clientspersonsservice.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.sofka.clientspersonsservice.domain.entity.Client;
import com.sofka.clientspersonsservice.domain.repository.ClientRepository;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clienteRepository;

    public Client saveClient(Client cliente) {
        return clienteRepository.save(cliente);
    }

    public Client findClientById(String clienteId) {
        return clienteRepository.findByClientId(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));
    }
}
