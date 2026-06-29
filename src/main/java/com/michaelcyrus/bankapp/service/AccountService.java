package com.michaelcyrus.bankapp.service;

import com.michaelcyrus.bankapp.dto.CreateAccountRequest;
import com.michaelcyrus.bankapp.entity.Account;
import com.michaelcyrus.bankapp.entity.Customer;
import com.michaelcyrus.bankapp.repository.AccountRepository;
import com.michaelcyrus.bankapp.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Michael Cyrus Jr
 **/
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account createAccount(CreateAccountRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Customer not found with id: "  + request.getCustomerId()
                        ));

        Account account = new Account(
                request.getAccountNumber(),
                request.getBalance(),
                customer
        );
        return accountRepository.save(account);
    }
}
