package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Michael Cyrus Jr
 **/
public class DatabaseTest {
    public static void main (String[] args) throws SQLException {
        String url = "jdbc:oracle:thin:@//192.168.1.69:1521/FREEPDB1";
        String user = "BANKAPP";
        String password = "BANKAPP";

        try(Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected");
        }
    }
}
