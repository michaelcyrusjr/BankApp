package com.michaelcyrusjr.bankapp;

import com.michaelcyrusjr.bankapp.model.Account;
import com.michaelcyrusjr.bankapp.ui.Menu;
import com.michaelcyrusjr.bankapp.repository.AccountRepository;

import java.sql.SQLException;

/**
 * @author Michael Cyrus Jr
 **/ //TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
class Main {

    public static void main(String[] args) throws SQLException {
        AccountRepository repository = new AccountRepository();
        Account account = new Account();
        // Greet the client
        System.out.println("\nHello, Welcome to SMARTBank!\n");
        // Start the prompts
        Menu.startBankApp(account, repository);
    }























































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

