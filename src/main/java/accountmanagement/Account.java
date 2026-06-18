package accountmanagement;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


/**
 * @author Michael Cyrus Jr
 **/
public class Account {
    // Retrieved from database
    private String ownerName;
    // Input from user and then verified in database
    private int accountNumber;
    // Retrieved from database
    private double accountBalance;
    Scanner scanner = new Scanner(System.in);


    public Account(String ownerName, int accountNumber, double accountBalance) {
        this.ownerName = ownerName;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    public Account() {

    }

    // Creates a new account
    public void createAccount (Account account) {
        String ownerName;
        while (true) {
            System.out.println("Please enter your first name: ");
            //Setting the account name
            try {
                ownerName = scanner.nextLine();
                if (account.isValidName(ownerName)) {
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
            String ownerTemp;
            try {
                ownerTemp = scanner.nextLine();
                if (account.isValidName(ownerTemp)) {
                    ownerName = ownerName + " " + ownerTemp;
                    break;
                } else {
                    System.out.println("Invalid entry (No numbers or special characters). Please try again.\n");continue;
                }
            } catch (NoSuchElementException ne) {
                System.out.println("No input entered. Please try again.\n");
            }
        }

        // Setting the account owner name
        account.setOwnerName(ownerName);

        // Setting the account number
        account.setAccountNumber ();
    }


    // Ask user for account name
    public void setOwnerName (String ownerName) {
        this.ownerName = ownerName;
    }

    // Gets the account name
    public String getOwnerName () {
        return this.ownerName;
    }

    public boolean isValidName(String ownerName) {
        return ownerName.matches("[a-zA-Z]+");
    }

    // Welcomes the user
    public void welcomeUser(String ownerName) {
        System.out.println("Hello, " + ownerName+"\n");
    }

    // Prints the account name to the console
    public void printOwnerName () {
        System.out.println("\nOwner Name: " + this.ownerName);
    }

    // Gets the account Number
    public int getAccountNumber () {
        return this.accountNumber;
    }

    // Gets the account Number
    public void setAccountNumber () {
        this.accountNumber = ThreadLocalRandom.current().nextInt(10000000, 99999999);
    }

    // Print account number
    public void printAccountNumber () {
        System.out.println("Account Number: " + this.accountNumber);
    }

    // Adds to the account balance
    public double deposit(double depositAmount) {
        this.accountBalance = this.accountBalance + depositAmount;
        System.out.println("\nThank you for your " + NumberFormat.getCurrencyInstance().format(depositAmount) + " deposit!\n");
        return this.accountBalance;
    }

    // Withdraws from the account
    private double withdrawAmountTotal = 0;
    private double withdrawAmount = 0;
    public double withdraw(double amount) {
        withdrawAmount = amount;
        if (amount > this.accountBalance) {
            this.withdrawAmountTotal = this.accountBalance - amount;
            return this.withdrawAmountTotal;

        } else {
            this.accountBalance = this.accountBalance - amount;
            return this.accountBalance;
        }
    }

    public void negWithdrawMessage(double amount) {
        if (this.withdrawAmountTotal < 0) {
            System.out.print("WITHDRAW REJECTED: Your withdraw amount of "
                    +NumberFormat.getCurrencyInstance(Locale.US).format(amount)
                    +" is greater than your current balance of "
                    +NumberFormat.getCurrencyInstance(Locale.US).format(this.accountBalance)
                    +".");
        }
    }

    // Return current balance
    public double getBalance() {
        return accountBalance;
    }

    // Print current balance in US currency format
    public void printBalance() {
        System.out.println("Balance: " + NumberFormat.getCurrencyInstance(Locale.US).format(this.accountBalance));
    }

    // Return all account info
    public void printAccountInfo (){
        printOwnerName();
        printAccountNumber();
        printBalance();
        if (this.withdrawAmountTotal < 0) {
            negWithdrawMessage(this.withdrawAmount);
        }
        System.out.println("\n");
    }
}
