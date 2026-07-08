package com.michaelcyrus.bankapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class UpdateAccountRequest {

    @NotNull
    @PositiveOrZero
    private BigDecimal balance;

    public UpdateAccountRequest() {
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
