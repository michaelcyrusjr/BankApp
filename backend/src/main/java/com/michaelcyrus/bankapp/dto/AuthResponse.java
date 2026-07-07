package com.michaelcyrus.bankapp.dto;

/**
 * @author Michael Cyrus Jr
 **/
public class AuthResponse {

    private String token;
    private CustomerResponse customer;

    public AuthResponse(String token, CustomerResponse customer) {
        this.token = token;
        this.customer = customer;
    }

    public String getToken() {
        return token;
    }

    public CustomerResponse getCustomer() {
        return customer;
    }
}
