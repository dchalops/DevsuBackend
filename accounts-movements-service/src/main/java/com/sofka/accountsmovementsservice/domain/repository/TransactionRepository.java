package com.sofka.accountsmovementsservice.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sofka.accountsmovementsservice.domain.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findByClientIdAndDateBetween(String clientId, LocalDate dateStart, LocalDate dateEnd);

}
