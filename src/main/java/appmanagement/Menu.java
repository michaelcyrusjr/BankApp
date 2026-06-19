package appmanagement;

import accountmanagement.Account;
import database.AccountRepository;

import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * @author Michael Cyrus Jr
 **/
public class Menu {
    static Scanner scanner = new Scanner(System.in);

    public static void startBankApp (Account account, AccountRepository repository) throws SQLException {
        while (true) {
            System.out.println("\nPLEASE SELECT A MENU OPTION:\n");
            System.out.println("""
                Menu
                1. Create an account
                2. View your account
                3. Withdraw from your account
                4. Deposit into your account
                5. Exit
                """);

            int choice = scanner.nextInt();
            switch (choice) {
                // Create a new account
                case 1 -> account.createAccount(account, repository);
                // View account info
                case 2 -> {
                    // Use the account number to assign the current account info to the account object
                    account = repository.getAccountInfo(getAccountNumber());
                    // Print all account info
                    account.printAccountInfo();
                }
                // Withdraw from account
                case 3 -> {
                    // Use the account number to assign the current account info to the account object
                    account = repository.getAccountInfo(getAccountNumber());
                    // Attempt to withdraw money from the account
                    account.withdraw(repository, account);
                }
                // Deposit into account
                case 4 -> {
                    // Use the account number to assign the current account info to the account object
                    account = repository.getAccountInfo(getAccountNumber());
                    // Attempt to deposit money into the account
                    account.deposit(repository, account);
                }
                // Exit app
                case 5 -> {
                    System.out.println("Thank you for banking with us!");
                    return;
                }
            }
        }
    }

    public static int getAccountNumber() {
        int accountNumber = 0;
        while (true) {
            System.out.println("Enter your account number: \n");
            // verify the user enters a valid account number
            if (scanner.hasNextInt()) {
                // If this is a valid int assign to the account number variable
                accountNumber = scanner.nextInt();
                // If the account number is greater than 0 break the loop
                if (accountNumber > 0) {
                    break;
                    // If the number is less than 0 continue the loop
                } else {
                    System.out.println("Account number must be positive.\n");
                }
            } else {
                // If the input is not a valid positive int
                System.out.println("Input must be a positive number.\n");
                scanner.next();
            }
        }
        return accountNumber;
    }
}
