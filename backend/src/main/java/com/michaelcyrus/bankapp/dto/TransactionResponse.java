package com.michaelcyrus.bankapp.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponse {

    private Long transactionId;
    private Long sourceAccountId;
    private Long destinationAccountId;
    private BigDecimal amount;
    private String direction;
    private LocalDateTime createdAt;

    public TransactionResponse(
            Long transactionId,
            Long sourceAccountId,
            Long destinationAccountId,
            BigDecimal amount,
            String direction,
            LocalDateTime createdAt
    ) {
        this.transactionId = transactionId;
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.amount = amount;
        this.direction = direction;
        this.createdAt = createdAt;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public Long getSourceAccountId() {
        return sourceAccountId;
    }

    public Long getDestinationAccountId() {
        return destinationAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDirection() {
        return direction;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}