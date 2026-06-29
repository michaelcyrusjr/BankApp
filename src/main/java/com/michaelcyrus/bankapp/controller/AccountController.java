package com.michaelcyrus.bankapp.controller;

import com.michaelcyrus.bankapp.dto.CreateAccountRequest;
import com.michaelcyrus.bankapp.dto.CreateCustomerRequest;
import com.michaelcyrus.bankapp.entity.Account;
import com.michaelcyrus.bankapp.service.AccountService;
import com.michaelcyrus.bankapp.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Michael Cyrus Jr
 **/
@RestController
@RequestMapping ("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping
    public Account createAccount (@RequestBody CreateAccountRequest request) {
            return accountService.createAccount(request);
    }

}
