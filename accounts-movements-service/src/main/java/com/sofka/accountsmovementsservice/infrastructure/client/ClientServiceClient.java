package com.sofka.accountsmovementsservice.infrastructure.client;

import com.sofka.accountsmovementsservice.application.dto.ClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clients-persons-service", path = "/api/clients")
public interface ClientServiceClient {
    
    @GetMapping("/{id}")
    ClientDTO getClientById(@PathVariable("id") String clientId);
}
