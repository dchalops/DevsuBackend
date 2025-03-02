package com.devsu.clientspersonsservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.devsu.clientspersonsservice.domain.entity.User;
import com.devsu.clientspersonsservice.domain.enums.Role;
import com.devsu.clientspersonsservice.domain.repository.UserRepository;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ClientesPersonasServiceApplication implements CommandLineRunner {
    private final UserRepository userRepository;

    public ClientesPersonasServiceApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ClientesPersonasServiceApplication.class, args);
    }

    @Override
    public void run(String... args) {
        final String pass = "$2a$10$BK3yUdkWkAwAmvNCF7arOe0Dp0uz.ThXrrEXhhEPisJrM/3Z4OguK";
        var admin = User.builder()
                .username("admin")
                .email("admin@gmail.com")
                .password(pass)
                .role(Role.ADMIN).build();
        if (userRepository.findByUsername("admin").isEmpty()) userRepository.save(admin);
    }
}
