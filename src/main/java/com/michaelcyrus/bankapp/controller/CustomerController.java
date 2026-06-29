package com.michaelcyrus.bankapp.controller;

import com.michaelcyrus.bankapp.dto.CreateCustomerRequest;
import com.michaelcyrus.bankapp.entity.Customer;
import com.michaelcyrus.bankapp.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Michael Cyrus Jr
 **/
@RestController
@RequestMapping ("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping
    public Customer createCustomer(@RequestBody CreateCustomerRequest request) {
        return customerService.createCustomer(request);
    }

}
