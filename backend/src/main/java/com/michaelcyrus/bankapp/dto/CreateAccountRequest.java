package com.michaelcyrus.bankapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

/**
 * @author Michael Cyrus Jr
 **/
public class CreateAccountRequest {

    @NotNull
    @PositiveOrZero
    private BigDecimal balance;

    @NotNull
    private Long customerId;

    public CreateAccountRequest() {
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
