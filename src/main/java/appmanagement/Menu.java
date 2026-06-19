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
                case 1 -> account.createAccount(account);
                case 2 -> account.printAccountInfo();
                case 3 -> {
                    // Needs to be correctly set up
                    int amount = 0;
                    account.withdraw(amount);
                }
                case 4 -> {
                    int accountNumber = 0;
                    while (true){
                        System.out.println("Enter your account number: \n");

                        if (scanner.hasNextInt()) {
                            accountNumber = scanner.nextInt();
                            if (accountNumber > 0) {
                                break;
                            } else {
                                System.out.println("Account number must be positive.\n");
                            }
                        } else {
                            System.out.println("Input must be a positive number.\n");
                            scanner.next();
                        }
                    }
                    account = repository.getAccountInfo(accountNumber);
                    account.deposit(repository, account);
                }
                case 5 -> {
                    System.out.println("Thank you for banking with us!");
                    return;
                }
            }
        }
    }
}
