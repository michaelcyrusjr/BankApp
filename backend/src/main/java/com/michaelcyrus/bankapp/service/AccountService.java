package com.michaelcyrus.bankapp.service;

import com.michaelcyrus.bankapp.dto.AccountResponse;
import com.michaelcyrus.bankapp.dto.CreateAccountRequest;
import com.michaelcyrus.bankapp.dto.CustomerResponse;
import com.michaelcyrus.bankapp.entity.Account;
import com.michaelcyrus.bankapp.entity.Customer;
import com.michaelcyrus.bankapp.repository.AccountRepository;
import com.michaelcyrus.bankapp.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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

    public List<AccountResponse> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(account -> new AccountResponse(
                        account.getId(),
                        account.getAccountNumber(),
                        account.getBalance(),
                        new CustomerResponse(
                                account.getCustomer().getId(),
                                account.getCustomer().getFirstName(),
                                account.getCustomer().getLastName(),
                                account.getCustomer().getEmail()
                        )
                ))
                .toList();
    }

    public AccountResponse createAccount(CreateAccountRequest request) {
        String accountNumber = generateUniqueAccountNumber();

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Customer not found with id: "  + request.getCustomerId()
                        ));

        Account account = new Account(
                accountNumber,
                request.getBalance(),
                customer
        );

        Account savedAccount = accountRepository.save(account);

        return new AccountResponse(
                savedAccount.getId(),
                savedAccount.getAccountNumber(),
                savedAccount.getBalance(),
                new CustomerResponse(
                        customer.getId(),
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getEmail()
                )
        );
    }

    private String generateUniqueAccountNumber() {
        String accountNumber;

        do {
            long number = ThreadLocalRandom.current().nextLong(1000000000L, 9999999999L);
            accountNumber = String.valueOf(number);
        } while (accountRepository.findByAccountNumber(accountNumber).isPresent());

        return accountNumber;
    }
}
