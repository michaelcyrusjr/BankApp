import accountmanagement.Account;
import database.AccountRepository;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Michael Cyrus Jr
 **/ //TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
class BankApp {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        AccountRepository repository = new AccountRepository();
        Account account = new Account();
        String accountOwner;
        int accountNumber;
        double accountBalance;

        // Greet the client
        System.out.println("\nHello, Welcome to SMARTBank!\n");


        while (true) {
            System.out.println("Please enter your name: ");
            //Setting the account name
            try {
                accountOwner = scanner.nextLine();
                if (account.isValidName(accountOwner)) {
                    break;
                } else {
                    System.out.println("Invalid entry (No numbers or special characters). Please try again.\n");continue;
                }
            } catch (NoSuchElementException ne) {
                System.out.println("No input entered. Please try again.\n");
            }
        }

            // Setting the account owner name
            account.setOwnerName(accountOwner);

            // Setting the account number
            account.setAccountNumber ();
            accountNumber = account.getAccountNumber();

            // Setting the account balance
            accountBalance = 0.0;


        repository.createAccount(account);
















































//        while (true) {
//            while (true){
//                System.out.println("Enter your account number: \n");
//
//                if (scanner.hasNextInt()) {
//                    accountNumber = scanner.nextInt();
//                    if (accountNumber > 0) {
//                        break;
//                    } else {
//                        System.out.println("Account number must be positive.\n");
//                    }
//                } else {
//                    System.out.println("Input must be a positive number.\n");
//                    scanner.next();
//                }
//            }
//
//            account = repository.getAccountInfo(accountNumber);
//
//            if (account != null) {
//                break;
//            } else {
//                System.out.println("Account not found.\n");
//            }
//        }
//
//        account.printAccountInfo();
    }

}