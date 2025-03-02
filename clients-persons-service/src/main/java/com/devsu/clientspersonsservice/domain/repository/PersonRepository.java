package com.devsu.clientspersonsservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.devsu.clientspersonsservice.domain.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
}
