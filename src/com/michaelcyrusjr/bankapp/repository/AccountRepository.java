package com.michaelcyrusjr.bankapp.repository;

import com.michaelcyrusjr.bankapp.model.Account;

import java.sql.*;

/**
 * @author Michael Cyrus Jr
 **/
public class AccountRepository {
    private final String url = "jdbc:oracle:thin:@//localhost:1521/FREEPDB1";
    private final String user = "BANKAPP";
    private final String password = "BANKAPP";
    private final Connection conn = DriverManager.getConnection(url, user, password);

    public AccountRepository() throws SQLException {
    }

    public void saveNewAccount(Account account) throws SQLException {
        int accountNumber = account.getAccountNumber();
        String ownerFirstName = account.getOwnerFirstName();
        String ownerLastName = account.getOwnerLastName();

        try (
                PreparedStatement pstmt = conn.prepareStatement(
                        """
                        INSERT INTO ACCOUNTS (
                                ACCOUNT_NUMBER,
                                FIRST_NAME,
                                LAST_NAME
                        )
                        VALUES (
                                ?,
                                ?,
                                ?
                        );
                        """
                );
        ) {
            pstmt.setInt(1, accountNumber);
            pstmt.setString(2, ownerFirstName);
            pstmt.setString(3, ownerLastName);
            try {
                pstmt.executeUpdate();
                System.out.println("Account created successfully ;)");
            } catch (SQLException e) {
                System.out.println("Unable to create account :(");
            }
        }
    }

    public Account getAccountInfo(int accountNumber) throws SQLException {
        try (
                PreparedStatement pstmt = conn.prepareStatement(
                """
                SELECT 
                    A.ACCOUNT_ID,
                    C.CUSTOMER_ID,
                    C.FULL_NAME,
                    C.FIRST_NAME,
                    C.LAST_NAME,
                    C.EMAIL_ADDRESS,
                    A.ACCOUNT_NUMBER,
                    A.BALANCE
                FROM CUSTOMERS C
                INNER JOIN ACCOUNTS A
                ON C.CUSTOMER_ID = A.CUSTOMER_ID
                WHERE C.EMAIL_ADDRESS = ?
                """);
        ) {
            pstmt.setInt(1, accountNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Need to create the constructor
                    return new Account(
                            rs.getInt("A.ACCOUNT_ID"),
                            rs.getInt("C.CUSTOMER_ID"),
                            rs.getString("C.FULL_NAME"),
                            rs.getString("C.FIRST_NAME"),
                            rs.getString("C.LAST_NAME"),
                            rs.getString("C.EMAIL_ADDRESS"),
                            rs.getInt("A.ACCOUNT_NUMBER"),
                            rs.getDouble("A.BALANCE")
                    );
                }
            }
        }
        return null;
    }

    public boolean verifyAccountExist(int accountNumber) throws SQLException {
        try (
                PreparedStatement pstmt = conn.prepareStatement(
                """
                SELECT *
                FROM ACCOUNTS
                WHERE ACCOUNT_NUMBER = ?
                """
                )
        ) {
            pstmt.setInt(1, accountNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void printAllAccountDataById(int id) throws SQLException {
        try (
                PreparedStatement pstmt = conn.prepareStatement(
                """
                SELECT *
                FROM ACCOUNTS
                WHERE ACCOUNT_ID = ?
                """
                )
        ) {
            pstmt.setInt(1, id);
            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println(rs.getString("OWNER_NAME"));
                }
            }
        }
    }

    public String getOwnerNameById(int id) throws SQLException {
        try (
                PreparedStatement pstmt = conn.prepareStatement(
                """
                
                SELECT OWNER_NAME 
                FROM ACCOUNTS 
                WHERE ACCOUNT_ID = ?
                
                """
                );
        ) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()) {
                    return rs.getString("OWNER_NAME");
                }
            }
        }
        return null;
    }

    public double getRepoBalance (Account account) throws SQLException {
        Double balance = 0.0;
        int accountId = account.getAccountId();
        try(
                PreparedStatement pstmt = conn.prepareStatement("""
                SELECT BALANCE
                FROM ACCOUNTS
                WHERE ACCOUNT_ID = ?
                """
                );
        ) {
            pstmt.setInt(1, accountId);
            try(ResultSet rs = pstmt.executeQuery()) {
                balance = rs.getDouble("BALANCE");
            }
        }
        return balance;
    }

    public void updateRepoBalance(double amount, Account account) throws SQLException {
        int accountId = account.getAccountId();
        try (
                PreparedStatement pstmt = conn.prepareStatement("""
                UPDATE ACCOUNTS
                SET BALANCE = ?
                WHERE ACCOUNT_ID = ?
                """
                );
        ) {
            pstmt.setDouble(1, amount);
            pstmt.setInt(2, accountId);
            try {
                pstmt.executeUpdate();
                System.out.println("\nAccount update successful :)\n");
            } catch (SQLException e) {
                System.out.println("\nAccount update unsuccessful :(\n");
                throw new RuntimeException(e);
            }
        }
    }

}
