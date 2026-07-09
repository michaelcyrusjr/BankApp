package com.michaelcyrus.bankapp.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransferResponse {

    private Long transactionId;
    private Long sourceAccountId;
    private Long destinationAccountId;
    private BigDecimal amount;
    private BigDecimal sourceBalanceAfterTransfer;
    private BigDecimal destinationBalanceAfterTransfer;
    private LocalDateTime createdAt;
    private String message;

    public TransferResponse(
            Long transactionId,
            Long sourceAccountId,
            Long destinationAccountId,
            BigDecimal amount,
            BigDecimal sourceBalanceAfterTransfer,
            BigDecimal destinationBalanceAfterTransfer,
            LocalDateTime createdAt,
            String message
    ) {
        this.transactionId = transactionId;
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.amount = amount;
        this.sourceBalanceAfterTransfer = sourceBalanceAfterTransfer;
        this.destinationBalanceAfterTransfer = destinationBalanceAfterTransfer;
        this.createdAt = createdAt;
        this.message = message;
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

    public BigDecimal getSourceBalanceAfterTransfer() {
        return sourceBalanceAfterTransfer;
    }

    public BigDecimal getDestinationBalanceAfterTransfer() {
        return destinationBalanceAfterTransfer;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getMessage() {
        return message;
    }
}