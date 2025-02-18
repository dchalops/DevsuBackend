package com.sofka.clientspersonsservice.application.service;

import com.sofka.clientspersonsservice.domain.entity.Client;
import com.sofka.clientspersonsservice.domain.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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
        client = new Client("123456", "John Doe", true);
    }

    @Test
    void shouldSaveClientSuccessfully() {
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client savedClient = clientService.saveClient(client);

        assertNotNull(savedClient);
        assertEquals(client.getClientId(), savedClient.getClientId());
        assertEquals(client.getName(), savedClient.getName());
    }

    @Test
    void shouldFindClientById() {
        when(clientRepository.findByClientId(client.getClientId())).thenReturn(Optional.of(client));

        Client foundClient = clientService.findClientById(client.getClientId());

        assertNotNull(foundClient);
        assertEquals(client.getClientId(), foundClient.getClientId());
    }

    @Test
    void shouldThrowExceptionWhenClientNotFound() {
        when(clientRepository.findByClientId("999999")).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> clientService.findClientById("999999"));
        assertEquals("Cliente no encontrado", exception.getMessage());
    }
}
