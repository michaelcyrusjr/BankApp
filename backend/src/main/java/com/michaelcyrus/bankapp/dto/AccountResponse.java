package com.michaelcyrus.bankapp.dto;

import java.math.BigDecimal;

/**
 * @author Michael Cyrus Jr
 **/
public class AccountResponse {
    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private CustomerResponse customer;

    public AccountResponse(Long id, String accountNumber, BigDecimal balance, CustomerResponse customer) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CustomerResponse getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerResponse customer) {
        this.customer = customer;
    }
}
