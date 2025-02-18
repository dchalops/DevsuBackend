package com.sofka.authservice.client;

import com.sofka.authservice.dto.RegisterDto;
import com.sofka.authservice.dto.UserDto;
import com.sofka.authservice.request.RegisterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "clients-persons-service", path = "/v1/user")
public interface clientespersonasserviceClient {
    @PostMapping("/save")
    ResponseEntity<RegisterDto> save(@RequestBody RegisterRequest request);

    @GetMapping("/getUserByUsername/{username}")
    ResponseEntity<UserDto> getUserByUsername(@PathVariable String username);
}
