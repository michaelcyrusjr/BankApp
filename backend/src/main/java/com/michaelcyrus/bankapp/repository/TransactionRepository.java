package com.michaelcyrus.bankapp.repository;

import com.michaelcyrus.bankapp.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
