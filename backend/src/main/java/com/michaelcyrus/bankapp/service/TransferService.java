package com.michaelcyrus.bankapp.service;

import com.michaelcyrus.bankapp.dto.TransferRequest;
import com.michaelcyrus.bankapp.dto.TransferResponse;
import com.michaelcyrus.bankapp.entity.Account;
import com.michaelcyrus.bankapp.entity.Transaction;
import com.michaelcyrus.bankapp.repository.AccountRepository;
import com.michaelcyrus.bankapp.repository.TransactionRepository;
import com.michaelcyrus.bankapp.exception.InsufficientFundsException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransferService(
            AccountRepository accountRepository,
            TransactionRepository transactionRepository
    ) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public TransferResponse transfer(TransferRequest request, String loggedInEmail) {
        Account sourceAccount = accountRepository
                .findByIdAndCustomerEmail(request.getSourceAccountId(), loggedInEmail)
                .orElseThrow(() ->
                        new IllegalArgumentException("Source account not found")
                );

        Account destinationAccount = accountRepository
                .findById(request.getDestinationAccountId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Destination account not found")
                );

        BigDecimal amount = request.getAmount();

        if (sourceAccount.getId().equals(destinationAccount.getId())) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        if (sourceAccount.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds for this transfer");
        }

        sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
        destinationAccount.setBalance(destinationAccount.getBalance().add(amount));

        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);

        Transaction transaction = new Transaction(
                sourceAccount,
                destinationAccount,
                amount
        );

        Transaction savedTransaction = transactionRepository.save(transaction);

        return new TransferResponse(
                savedTransaction.getId(),
                sourceAccount.getId(),
                destinationAccount.getId(),
                amount,
                sourceAccount.getBalance(),
                destinationAccount.getBalance(),
                savedTransaction.getCreatedAt(),
                "Transfer completed successfully"
        );
    }
}