package com.devsu.clientspersonsservice.application.service;

import com.devsu.clientspersonsservice.application.service.ClientService;
import com.devsu.clientspersonsservice.domain.entity.BaseEntity;
import com.devsu.clientspersonsservice.domain.entity.Client;
import com.devsu.clientspersonsservice.domain.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    private Client client;

    @BeforeEach
    void setUp() {
        UUID clientId = UUID.randomUUID();

        client = new Client();
        client.setId(clientId.toString());
        client.setPassword("password123");
        client.setStatus(true);
        client.setIsDelete(false);
    }

    @Test
    void shouldSaveClientSuccessfully() {
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client savedClient = clientService.saveClient(client);

        assertNotNull(savedClient);
        assertEquals(client.getId(), savedClient.getId());
        assertEquals(client.getName(), savedClient.getName());
    }

    @Test
    void shouldFindClientById() {
        when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));

        Client foundClient = clientService.findClientById(client.getId());

        assertNotNull(foundClient);
        assertEquals(client.getId(), foundClient.getId());
    }

    @Test
    void shouldThrowExceptionWhenClientNotFound() {
        String invalidClientId = "00000000-0000-0000-0000-000000000000";
        when(clientRepository.findById(invalidClientId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> clientService.findClientById(invalidClientId));
        
        assertEquals("Client not found", exception.getMessage());
    }

}
