package database;

import accountmanagement.Account;

import javax.xml.transform.Result;
import java.sql.*;

/**
 * @author Michael Cyrus Jr
 **/
public class AccountRepository {
    private final String url = "jdbc:oracle:thin:@//192.168.1.69:1521/FREEPDB1";
    private final String user = "BANKAPP";
    private final String password = "BANKAPP";
    private final Connection conn = DriverManager.getConnection(url, user, password);

    public AccountRepository() throws SQLException {
    }

    public void createAccount(Account account) throws SQLException {
        int accountNumber = account.getAccountNumber();
        String accountOwner = account.getOwnerName();

        try (
                PreparedStatement pstmt = conn.prepareStatement(
                        """
                        INSERT INTO ACCOUNTS (
                                ACCOUNT_NUMBER,
                                OWNER_NAME
                        )
                        VALUES (
                                ?,
                                ?
                        );
                        """
                );
        ) {
            pstmt.setInt(1, account.getAccountNumber());
            pstmt.setString(2, account.getOwnerName());
            try {
                ResultSet rs = pstmt.executeQuery();
                System.out.println("Account was successfully saved");
            } catch (SQLException e) {
                System.out.println("Account was not successfully saved.");
            }
        }
    }

    public Account getAccountInfo(int accountNumber) throws SQLException {
        try (
                PreparedStatement pstmt = conn.prepareStatement(
                """
                SELECT *
                FROM ACCOUNTS
                WHERE ACCOUNT_NUMBER = ?
                """);
        ) {
            pstmt.setInt(1, accountNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Account(
                            rs.getString("OWNER_NAME"),
                            rs.getInt("ACCOUNT_NUMBER"),
                            rs.getDouble("BALANCE")
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
}
