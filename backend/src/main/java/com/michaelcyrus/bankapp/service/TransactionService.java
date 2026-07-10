package com.michaelcyrus.bankapp.service;

import com.michaelcyrus.bankapp.dto.TransactionResponse;
import com.michaelcyrus.bankapp.entity.Transaction;
import com.michaelcyrus.bankapp.repository.AccountRepository;
import com.michaelcyrus.bankapp.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(
            TransactionRepository transactionRepository,
            AccountRepository accountRepository
    ) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public List<TransactionResponse> getTransactionHistory(
            String loggedInEmail,
            Long accountId
    ) {
        if (accountId != null) {
            accountRepository.findByIdAndCustomerEmail(accountId, loggedInEmail)
                    .orElseThrow(() ->
                            new IllegalArgumentException("Account not found with id: " + accountId)
                    );

            return transactionRepository
                    .findBySourceAccountIdOrDestinationAccountIdOrderByCreatedAtDesc(
                            accountId,
                            accountId
                    )
                    .stream()
                    .map(transaction -> toTransactionResponse(transaction, loggedInEmail))
                    .toList();
        }

        return transactionRepository
                .findBySourceAccountCustomerEmailOrDestinationAccountCustomerEmailOrderByCreatedAtDesc(
                        loggedInEmail,
                        loggedInEmail
                )
                .stream()
                .map(transaction -> toTransactionResponse(transaction, loggedInEmail))
                .toList();
    }

    private TransactionResponse toTransactionResponse(
            Transaction transaction,
            String loggedInEmail
    ) {
        String direction = transaction.getSourceAccount()
                .getCustomer()
                .getEmail()
                .equals(loggedInEmail)
                ? "OUTGOING"
                : "INCOMING";

        return new TransactionResponse(
                transaction.getId(),
                transaction.getSourceAccount().getId(),
                transaction.getDestinationAccount().getId(),
                transaction.getAmount(),
                direction,
                transaction.getCreatedAt()
        );
    }
}