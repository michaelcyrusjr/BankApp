package com.michaelcyrus.bankapp.service;

import com.michaelcyrus.bankapp.dto.CreateCustomerRequest;
import com.michaelcyrus.bankapp.dto.CustomerResponse;
import com.michaelcyrus.bankapp.entity.Customer;
import com.michaelcyrus.bankapp.exception.DuplicateEmailException;
import com.michaelcyrus.bankapp.repository.CustomerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Michael Cyrus Jr
 **/
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;


    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> new CustomerResponse(
                        customer.getId(),
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getEmail()
                ))
                .toList();
    }


    public CustomerResponse createCustomer(CreateCustomerRequest request) {
        String passwordHash = passwordEncoder.encode(request.getPassword());

        if (customerRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateEmailException("Email already exists");
        }

        Customer customer = new Customer(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                passwordHash
        );

        Customer savedCustomer = customerRepository.save(customer);

        return new CustomerResponse(
                savedCustomer.getId(),
                savedCustomer.getFirstName(),
                savedCustomer.getLastName(),
                savedCustomer.getEmail()
        );
    }

}
