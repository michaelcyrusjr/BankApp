package accountmanagement;

import database.AccountRepository;

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
    // Retrieved from database
    private int accountId = 0;
    // Retrieved from database
    private String ownerName;
    // Input from user and then verified in database
    private int accountNumber;
    // Retrieved from database
    private double accountBalance;
    Scanner scanner = new Scanner(System.in);
    AccountRepository repository = new AccountRepository();

    public Account(String ownerName, int accountNumber, double accountBalance) throws SQLException {
        this.ownerName = ownerName;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    public Account(int accountId, String ownerName, int accountNumber, double accountBalance) throws SQLException {
        this.accountId = accountId;
        this.ownerName = ownerName;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    public Account() throws SQLException {

    }

    // Get the account ID
    public int getAccountId() {
        return this.accountId;
    }

    // Ask user for account name
    public void setOwnerName (String ownerName) {
        this.ownerName = ownerName;
    }

    // Gets the account name
    public String getOwnerName () {
        return this.ownerName;
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
        System.out.println("\n");
    }

    // Creates a new account
    public void createAccount (Account account, AccountRepository repository) throws SQLException {
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
        repository.saveNewAccount(account);
    }

    // Adds to the account repo balance
    public void deposit(AccountRepository repository, Account account) throws SQLException {
        System.out.println("Please enter a deposit amount: ");
        // User enters deposit amount
        double depositAmount = scanner.nextDouble();
        // Get the current balance
        double currentBalance = account.getBalance();
        // Add deposit to current balance
        currentBalance = currentBalance + depositAmount;
        // Attempt to save new balance to repo
        repository.updateRepoBalance(currentBalance, account);
        // Assign the account object balance to the new balance
        this.accountBalance = currentBalance;
        System.out.println("\nThank you for your " + NumberFormat.getCurrencyInstance().format(depositAmount) + " deposit!\n");
        System.out.println("\nYou're current balance is now: " + NumberFormat.getCurrencyInstance().format(this.accountBalance) + "\n");
    }

    // Subtracts from the account repo balance
    public void withdraw(AccountRepository repository, Account account) throws SQLException {
        System.out.println("Please enter a withdraw amount: ");
        // User enters withdraw amount
        double withdrawAmount = scanner.nextDouble();
        // Get the current balance
        double currentBalance = account.getBalance();
        // Subtract withdraw amount to current balance
        currentBalance = currentBalance - withdrawAmount;
        // Print message if withdraw is more than avaialve balance
        if (currentBalance < 0) {
            System.out.print("WITHDRAW REJECTED: Your withdraw amount of "
                    +NumberFormat.getCurrencyInstance(Locale.US).format(withdrawAmount)
                    +" is greater than your current balance of "
                    +NumberFormat.getCurrencyInstance(Locale.US).format(account.getBalance())
                    +".\n");
        } else {
            // Update repo and thank client if withdraw is successful
            repository.updateRepoBalance(currentBalance, account);
            this.accountBalance = currentBalance;
            System.out.println("\nThank you for your " + NumberFormat.getCurrencyInstance().format(withdrawAmount) + " withdraw!\n");
            System.out.println("\nYou're current balance is now: " + NumberFormat.getCurrencyInstance().format(this.accountBalance) + "\n");
        }
    }

    // Checks if String is a valid name
    public boolean isValidName(String ownerName) {
        return ownerName.matches("[a-zA-Z]+");
    }




}
