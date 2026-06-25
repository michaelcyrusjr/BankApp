package com.michaelcyrusjr.bankapp.model;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Michael Cyrus Jr
 **/
public class Account {
    private int accountId = 0;
    private int accountNumber = 0;
    private double accountBalance = 0.0;


    //Constructors
    public Account() {
    }
    public Account(int accountId, int accountNumber, double accountBalance) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    // Getters
    public int getAccountId() {
        return this.accountId;
    }
    public int getAccountNumber () {
        return this.accountNumber;
    }
    public double getAccountBalance() {
        return accountBalance;
    }

    // Setters
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    public void setAccountNumber (int accountNumber) {
        this.accountNumber = accountNumber;
    }
    public void setAccountBalance (Double accountBalance) {
        this.accountBalance = accountBalance;
    }



}
