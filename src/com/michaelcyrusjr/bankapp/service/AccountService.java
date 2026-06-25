package com.michaelcyrusjr.bankapp.service;

import com.michaelcyrusjr.bankapp.model.Account;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Michael Cyrus Jr
 **/
public class AccountService {
    // Business
    public void createAccount (Account account) throws SQLException {











        String ownerFirstName;
        String ownerLastName;
        String ownerFullName;
        while (true) {
            System.out.println("Please enter your first name: ");
            //Setting the account name
            try {
                ownerFirstName = scanner.nextLine();
                if (account.isValidName(ownerFirstName)) {
                    break;
                } else {
                    System.out.println("Invalid entry (No numbers or special characters). Please try again.\n");continue;
                }
            } catch (NoSuchElementException ne) {
                System.out.println("No input entered. Please try again.\n");
            }
        }
        // Getting last name
        while (true) {
            System.out.println("Please enter your last name: ");
            //Setting the account name
            try {
                ownerLastName = scanner.nextLine();
                if (account.isValidName(ownerLastName)) {
                    break;
                } else {
                    System.out.println("Invalid entry (No numbers or special characters). Please try again.\n");continue;
                }
            } catch (NoSuchElementException ne) {
                System.out.println("No input entered. Please try again.\n");
            }
        }
        // Setting the account owner name
        account.setOwnerFirstName(ownerFirstName);
        account.setOwnerLastName(ownerLastName);
        account.setOwnerFullName();
        // Setting the account number
        account.setAccountNumber ();
        repository.saveNewAccount(account);
    }
    public void deposit(AccountRepository repository, Account account) throws SQLException {
        System.out.println("Please enter a deposit amount: ");
        // User enters deposit amount
        double depositAmount = scanner.nextDouble();
        // Get the current balance
        double currentBalance = account.getAccountBalance();
        // Add deposit to current balance
        currentBalance = currentBalance + depositAmount;
        // Attempt to save new balance to repo
        repository.updateRepoBalance(currentBalance, account);
        // Assign the account object balance to the new balance
        this.accountBalance = currentBalance;
        System.out.println("\nThank you for your " + NumberFormat.getCurrencyInstance().format(depositAmount) + " deposit!\n");
        System.out.println("\nYou're current balance is now: " + NumberFormat.getCurrencyInstance().format(this.accountBalance) + "\n");
    }
    public void withdraw(AccountRepository repository, Account account) throws SQLException {
        System.out.println("Please enter a withdrawal amount: ");
        // User enters withdraw amount
        double withdrawAmount = scanner.nextDouble();
        // Get the current balance
        double currentBalance = account.getAccountBalance();
        // Subtract withdraw amount to current balance
        currentBalance = currentBalance - withdrawAmount;
        // Print message if withdraw is more than avaialve balance
        if (currentBalance < 0) {
            System.out.print("WITHDRAW REJECTED: Your withdraw amount of "
                    +NumberFormat.getCurrencyInstance(Locale.US).format(withdrawAmount)
                    +" is greater than your current balance of "
                    +NumberFormat.getCurrencyInstance(Locale.US).format(account.getAccountBalance())
                    +".\n");
        } else {
            // Update repo and thank client if withdraw is successful
            int accountNumber = account.getAccountNumber();
            repository.updateRepoBalance(currentBalance, account);
            this.accountBalance = currentBalance;
            System.out.println("\nThank you for your " + NumberFormat.getCurrencyInstance().format(withdrawAmount) + " withdraw!\n");
            System.out.println("\nYou're current balance is now: " + NumberFormat.getCurrencyInstance().format(this.accountBalance) + "\n");
        }
    }
    public void newAccountNumber () {
        this.accountNumber = ThreadLocalRandom.current().nextInt(10000000, 99999999);
    }
}
