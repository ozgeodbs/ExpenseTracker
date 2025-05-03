package com.company;

import java.sql.*;

public class ExpenseManager {

    public static void addExpense(int departmentID, int categoryID, String description, double amount, String expenseDate) {
        String sql = "INSERT INTO Expenses (DepartmentID, CategoryID, Description, Amount, ExpenseDate) VALUES (?, ?, ?, ?, ?)";
        executeUpdate(sql, departmentID, categoryID, description, amount, expenseDate);
    }

    public static void updateExpense(int expenseID, String description, double amount, String expenseDate) {
        String sql = "UPDATE Expenses SET Description = ?, Amount = ?, ExpenseDate = ? WHERE ExpenseID = ?";
        executeUpdate(sql, description, amount, expenseDate, expenseID);
    }

    public static void deleteExpense(int expenseID) {
        String sql = "DELETE FROM Expenses WHERE ExpenseID = ?";
        executeUpdate(sql, expenseID);
    }

    private static void executeUpdate(String sql, Object... params) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setPreparedStatementParams(pstmt, params);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Operation successful!");
            } else {
                System.out.println("Operation failed.");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void setPreparedStatementParams(PreparedStatement pstmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            pstmt.setObject(i + 1, params[i]);
        }
    }

    public static void viewExpenses() {
        String sql = "SELECT * FROM Expenses";
        executeQuery(sql);
    }

    public static void viewExpensesByDepartment(int departmentID) {
        String sql = "SELECT * FROM Expenses WHERE DepartmentID = ?";
        executeQuery(sql, departmentID);
    }

    public static void viewExpensesByCategory(int categoryID) {
        String sql = "SELECT * FROM Expenses WHERE CategoryID = ?";
        executeQuery(sql, categoryID);
    }

    private static void executeQuery(String sql, Object... params) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setPreparedStatementParams(pstmt, params);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                displayExpense(rs);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void displayExpense(ResultSet rs) throws SQLException {
        System.out.println("ID: " + rs.getInt("ExpenseID") +
                ", Dept ID: " + rs.getInt("DepartmentID") +
                ", Cat ID: " + rs.getInt("CategoryID") +
                ", Desc: " + rs.getString("Description") +
                ", Amount: " + rs.getDouble("Amount") +
                ", Date: " + rs.getDate("ExpenseDate"));
    }

    public static int addDepartmentAndGetID(String departmentName) {
        String checkSql = "SELECT DepartmentID FROM Departments WHERE DepartmentName = ?";
        String insertSql = "INSERT INTO Departments (DepartmentName) VALUES (?)";
        return addEntityAndGetID(checkSql, insertSql, departmentName);
    }

    public static int addCategoryAndGetID(String categoryName) {
        String checkSql = "SELECT CategoryID FROM Categories WHERE CategoryName = ?";
        String insertSql = "INSERT INTO Categories (CategoryName) VALUES (?)";
        return addEntityAndGetID(checkSql, insertSql, categoryName);
    }

    private static int addEntityAndGetID(String checkSql, String insertSql, String name) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql);
             PreparedStatement insertStmt = conn.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            checkStmt.setString(1, name);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                System.out.println("New ID: "+ rs.getInt(1));
                return rs.getInt(1);  // Return existing ID
            }

            insertStmt.setString(1, name);
            insertStmt.executeUpdate();
            ResultSet generatedKeys = insertStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                System.out.println("New ID: "+ generatedKeys.getInt(1));
                return generatedKeys.getInt(1);  // Return new ID
            }

        } catch (SQLException e) {
            System.out.println("Error handling entity: " + e.getMessage());
        }
        return -1; // Return invalid ID if operation fails
    }

    public static void getExpenseByID(int expenseID) {
        String sql = "SELECT * FROM Expenses WHERE ExpenseID = ?";
        executeQuery(sql, expenseID);
    }

    public static void calculateTotalExpensesByCategory(int categoryID) {
        String sql = "SELECT SUM(Amount) AS Total FROM Expenses WHERE CategoryID = ?";
        executeSumQuery(sql, categoryID);
    }

    public static void calculateTotalExpensesByDepartment(int departmentID) {
        String sql = "SELECT SUM(Amount) AS Total FROM Expenses WHERE DepartmentID = ?";
        executeSumQuery(sql, departmentID);
    }

    public static void calculateTotalExpensesInRange(String startDate, String endDate) {
        String sql = "SELECT SUM(Amount) AS Total FROM Expenses WHERE ExpenseDate BETWEEN ? AND ?";
        executeSumQuery(sql, startDate, endDate);
    }

    private static void executeSumQuery(String sql, Object... params) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setPreparedStatementParams(pstmt, params);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                double total = rs.getDouble("Total");
                System.out.println("\nTotal Expenses: " + total);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void generateMonthlyReport() {
        String sql = "SELECT DATE_FORMAT(ExpenseDate, '%Y-%m') AS Month, SUM(Amount) AS Total FROM Expenses GROUP BY Month ORDER BY Month DESC";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\nMonthly Expense Report:");
            while (rs.next()) {
                System.out.println("Month: " + rs.getString("Month") + " | Total: " + rs.getDouble("Total"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
