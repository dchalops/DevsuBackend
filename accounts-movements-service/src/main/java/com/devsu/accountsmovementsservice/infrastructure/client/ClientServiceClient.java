package com.devsu.accountsmovementsservice.infrastructure.client;

import com.devsu.accountsmovementsservice.application.dto.ClientDTO;
import com.devsu.accountsmovementsservice.domain.entity.Client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clients-persons-service", path = "/v1/clients")
public interface ClientServiceClient {
    
    @GetMapping("/{id}")
    ClientDTO getClient(@PathVariable String id);

    @GetMapping("/{id}")
    ClientDTO getClientById(@PathVariable("id") String clientId);
}
