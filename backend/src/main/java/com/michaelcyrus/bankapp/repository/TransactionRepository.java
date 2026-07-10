package com.michaelcyrus.bankapp.repository;

import com.michaelcyrus.bankapp.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findBySourceAccountCustomerEmailOrDestinationAccountCustomerEmailOrderByCreatedAtDesc(
            String sourceEmail,
            String destinationEmail
    );

    List<Transaction> findBySourceAccountIdOrDestinationAccountIdOrderByCreatedAtDesc(
            Long sourceAccountId,
            Long destinationAccountId
    );
}
