package com.michaelcyrus.bankapp.controller;

import com.michaelcyrus.bankapp.dto.TransferRequest;
import com.michaelcyrus.bankapp.dto.TransferResponse;
import com.michaelcyrus.bankapp.entity.Transaction;
import com.michaelcyrus.bankapp.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public TransferResponse transferMoney(
            @Valid @RequestBody TransferRequest request,
            Authentication authentication
    ) {
        String loggedInEmail = authentication.getName();

        return transferService.transfer(request, loggedInEmail);
    }
}