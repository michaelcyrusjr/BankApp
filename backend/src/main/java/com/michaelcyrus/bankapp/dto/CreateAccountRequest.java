package com.michaelcyrus.bankapp.dto;

import java.math.BigDecimal;

/**
 * @author Michael Cyrus Jr
 **/
public class CreateAccountRequest {

    private String accountNumber;
    private BigDecimal balance;
    private Long customerId;

    public CreateAccountRequest() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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
