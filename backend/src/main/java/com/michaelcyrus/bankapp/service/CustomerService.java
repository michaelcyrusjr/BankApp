package com.michaelcyrus.bankapp.service;

import com.michaelcyrus.bankapp.dto.CreateCustomerRequest;
import com.michaelcyrus.bankapp.entity.Customer;
import com.michaelcyrus.bankapp.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Michael Cyrus Jr
 **/
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer createCustomer(CreateCustomerRequest request) {
        Customer customer = new Customer(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPasswordHash()
        );

        return customerRepository.save(customer);
    }
}
