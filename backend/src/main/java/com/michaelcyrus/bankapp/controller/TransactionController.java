package com.michaelcyrus.bankapp.controller;

import com.michaelcyrus.bankapp.dto.TransactionResponse;
import com.michaelcyrus.bankapp.service.TransactionService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<TransactionResponse> getTransactionHistory(
            Authentication authentication,
            @RequestParam(required = false) Long accountId
    ) {
        String loggedInEmail = authentication.getName();

        return transactionService.getTransactionHistory(loggedInEmail, accountId);
    }
}