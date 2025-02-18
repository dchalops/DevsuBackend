package com.sofka.authservice.service;

import com.sofka.authservice.client.clientespersonasserviceClient;
import com.sofka.authservice.dto.RegisterDto;
import com.sofka.authservice.dto.TokenDto;
import com.sofka.authservice.exc.WrongCredentialsException;
import com.sofka.authservice.request.LoginRequest;
import com.sofka.authservice.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final clientespersonasserviceClient clientespersonasserviceClient;
    private final JwtService jwtService;

    public TokenDto login(LoginRequest request) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (authenticate.isAuthenticated())
            return TokenDto
                    .builder()
                    .token(jwtService.generateToken(request.getUsername()))
                    .build();
        else throw new WrongCredentialsException("Wrong credentials");
    }

    public RegisterDto register(RegisterRequest request) {
        return clientespersonasserviceClient.save(request).getBody();
    }
}
