package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * TestJdbcConnection
 *
 * Test class for JDBC Connection
 *
 * Modifications:
 *
 * 	    11/24/2024 - thihaaung - created
 */

public class TestJdbcConnection {
    public static void main(String[] args) throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/mydb";
        String user = "root";
        String password = "student";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Database Connection successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

