package com.devsu.accountsmovementsservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.devsu.accountsmovementsservice.domain.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findByClientId(UUID clientId);
    List<Account> findByStatus(Boolean status);
}
