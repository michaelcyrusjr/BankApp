package com.michaelcyrus.bankapp.service;

import com.michaelcyrus.bankapp.entity.Account;
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

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
