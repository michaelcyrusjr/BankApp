package com.michaelcyrus.bankapp.repository;

import com.michaelcyrus.bankapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Michael Cyrus Jr
 **/
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
}
