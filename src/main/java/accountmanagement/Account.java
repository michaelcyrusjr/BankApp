package accountmanagement;

import java.text.NumberFormat;
import java.util.Locale;
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


    public Account(String accountName, int accountNumber, double accountBalance) {
        this.ownerName = accountName;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    public Account() {

    }


    // Ask user for account name
    public String setOwnerName (String ownerName) {
        this.ownerName = ownerName;
        return this.ownerName;
    }

    // Gets the account name
    public String getOwnerName (String ownerName) {
        this.ownerName = ownerName;
        return this.ownerName;
    }

    // Welcomes the user
    public void welcomeUser(String ownerName) {
        System.out.println("Hello, " + ownerName+"\n");
    }

    // Prints the account name to the console
    public void printOwnerName () {
        System.out.println("\nAccount Name: " + this.ownerName);
    }

    // Gets the account Number
    public int getAccountNumber () {
        return this.accountNumber;
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
        System.out.println(NumberFormat.getCurrencyInstance(Locale.US).format(this.accountBalance));
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
