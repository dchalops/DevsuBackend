package com.sofka.clientspersonsservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sofka.clientspersonsservice.domain.entity.User;
import com.sofka.clientspersonsservice.domain.enums.Active;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    List<User> findAllByActive(Active active);

}
