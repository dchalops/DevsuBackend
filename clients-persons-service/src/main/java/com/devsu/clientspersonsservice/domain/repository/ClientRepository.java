package com.devsu.clientspersonsservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsu.clientspersonsservice.domain.entity.Client;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findById(String clientId);
}