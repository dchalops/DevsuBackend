package com.devsu.accountsmovementsservice.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsu.accountsmovementsservice.domain.entity.Account;
import com.devsu.accountsmovementsservice.domain.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findByAccount_ClientIdAndDateBetween(String clientId, LocalDate dateStart, LocalDate dateEnd);
    List<Transaction> findByAccountAndDateBetween(Account account, LocalDate startDate, LocalDate endDate);

}
