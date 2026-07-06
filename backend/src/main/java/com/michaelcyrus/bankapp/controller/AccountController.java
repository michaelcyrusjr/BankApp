package com.michaelcyrus.bankapp.controller;

import com.michaelcyrus.bankapp.dto.AccountResponse;
import com.michaelcyrus.bankapp.dto.CreateAccountRequest;
import com.michaelcyrus.bankapp.entity.Account;
import com.michaelcyrus.bankapp.service.AccountService;

import jakarta.validation.Valid;
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
    public List<AccountResponse> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping
    public AccountResponse createAccount (@Valid @RequestBody CreateAccountRequest request) {
            return accountService.createAccount(request);
    }

}
