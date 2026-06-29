package com.michaelcyrus.bankapp.controller;

import com.michaelcyrus.bankapp.entity.Account;
import com.michaelcyrus.bankapp.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
