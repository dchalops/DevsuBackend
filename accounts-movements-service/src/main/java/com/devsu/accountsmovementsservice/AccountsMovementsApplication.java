package com.devsu.accountsmovementsservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class AccountsMovementsApplication implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        // Implementation here
    }
    public static void main(String[] args) {
        SpringApplication.run(AccountsMovementsApplication.class, args);
    }

}
