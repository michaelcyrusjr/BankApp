package com.michaelcyrus.bankapp.controller;

import com.michaelcyrus.bankapp.dto.AccountResponse;
import com.michaelcyrus.bankapp.dto.CreateAccountRequest;
import com.michaelcyrus.bankapp.entity.Account;
import com.michaelcyrus.bankapp.service.AccountService;
import com.michaelcyrus.bankapp.dto.UpdateAccountRequest;
import org.springframework.security.core.Authentication;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;

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
    public List<AccountResponse> getAllAccounts(Authentication authentication) {
        String email = authentication.getName();
        return accountService.getAccountsForCustomer(email);
    }

    @PostMapping
    public AccountResponse createAccount (@Valid @RequestBody CreateAccountRequest request) {
            return accountService.createAccount(request);
    }

    @PutMapping("/{id}")
    public AccountResponse updateAccount(
            @PathVariable Long id,
            @Valid @RequestBody UpdateAccountRequest request
    ) {
        return accountService.updateAccount(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(
            @PathVariable Long id,
            Authentication authentication
    ) {
        String email = authentication.getName();

        accountService.deleteAccount(id, email);

        return ResponseEntity.noContent().build();
    }

}
