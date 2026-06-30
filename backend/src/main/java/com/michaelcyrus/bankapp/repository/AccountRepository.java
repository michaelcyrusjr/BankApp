package com.michaelcyrus.bankapp.repository;

import com.michaelcyrus.bankapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Michael Cyrus Jr
 **/
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
}
