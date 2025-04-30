package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/ExpenseTrackingDB";
    private static final String USER = "root";  // Change to your MySQL username
    private static final String PASSWORD = "xowbeb-hiMfi3-vamrex";  // Change to your MySQL password

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL Driver
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
