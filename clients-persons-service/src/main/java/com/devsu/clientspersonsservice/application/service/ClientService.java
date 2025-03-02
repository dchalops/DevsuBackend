package com.devsu.clientspersonsservice.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.devsu.clientspersonsservice.domain.entity.Client;
import com.devsu.clientspersonsservice.domain.repository.ClientRepository;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public Client findClientById(String clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
    }

    public Iterable<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public void setIsDelete(String clientId) {
        Client client = clientRepository.findById(clientId)
                    .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        client.setIsDelete(true);
        clientRepository.save(client);
    }

    public Client updateClient(String clientId, Client updatedClient) {
        Client existingClient  = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        existingClient.setPassword(updatedClient.getPassword());
        existingClient.setStatus(updatedClient.getStatus());
        existingClient.setIsDelete(updatedClient.getIsDelete());

        existingClient.setName(updatedClient.getName());
        existingClient.setGender(updatedClient.getGender());
        existingClient.setAge(updatedClient.getAge());
        existingClient.setIdentification(updatedClient.getIdentification());
        existingClient.setAddress(updatedClient.getAddress());
        existingClient.setPhone(updatedClient.getPhone());
        
        clientRepository.save(existingClient);
        return existingClient;
    }
}
