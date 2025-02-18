package com.sofka.authservice.service;


import com.sofka.authservice.client.clientespersonasserviceClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final clientespersonasserviceClient clientespersonasserviceClient;

    public CustomUserDetailsService(clientespersonasserviceClient clientespersonasserviceClient) {
        this.clientespersonasserviceClient = clientespersonasserviceClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = clientespersonasserviceClient.getUserByUsername(username).getBody();
        assert user != null;
        return new CustomUserDetails(user);
    }
}