package com.devsu.accountsmovementsservice.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.devsu.accountsmovementsservice.application.dto.TransactionDTO;
import com.devsu.accountsmovementsservice.application.service.TransactionService;
import com.devsu.accountsmovementsservice.domain.entity.Transaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransactionService transactionService;

    private static final PostgreSQLContainer<?> POSTGRES_CONTAINER = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("test_db")
            .withUsername("test_user")
            .withPassword("test_pass");

    @BeforeAll
    static void startContainer() {
        POSTGRES_CONTAINER.start();
    }

    @DynamicPropertySource
    static void configureProperties(org.springframework.test.context.DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRES_CONTAINER::getPassword);
    }

    @Test
    void shouldCreateTransactionSuccessfully() throws Exception {
        TransactionDTO transactionDTO = TransactionDTO.builder()
                .accountNumber("123456")
                .transactionType("DEPOSIT")
                .amount(500.0)
                .date(LocalDate.now())
                .clientId("client-id-123")
                .balance(500.0)
                .build();

        Transaction fakeTransaction = new Transaction();
        fakeTransaction.setId("fake-transaction-id");
        fakeTransaction.setTransactionType("DEPOSIT");
        fakeTransaction.setAmount(500.0);

        when(transactionService.registerTransaction(any(TransactionDTO.class)))
                .thenReturn(fakeTransaction);

        mockMvc.perform(post("/v1/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transactionDTO)))
                .andExpect(status().isCreated())
                .andDo(print());
    }
}
