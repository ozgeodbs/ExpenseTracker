package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.*;

public class ExpenseManager {

    // 📌 Add an Expense
    public static void addExpense(int departmentID, int categoryID, String description, double amount, String expenseDate) {
        String sql = "INSERT INTO Expenses (DepartmentID, CategoryID, Description, Amount, ExpenseDate) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, departmentID);
            pstmt.setInt(2, categoryID);
            pstmt.setString(3, description);
            pstmt.setDouble(4, amount);
            pstmt.setString(5, expenseDate);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Expense added successfully!");
            } else {
                System.out.println("❌ Failed to add expense.");
            }

        } catch (SQLException e) {
            System.out.println("Error adding expense: " + e.getMessage());
        }
    }

    // 📌 Update an Expense
    public static void updateExpense(int expenseID, String description, double amount, String expenseDate) {
        String sql = "UPDATE Expenses SET Description = ?, Amount = ?, ExpenseDate = ? WHERE ExpenseID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, description);
            pstmt.setDouble(2, amount);
            pstmt.setString(3, expenseDate);
            pstmt.setInt(4, expenseID);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Expense updated successfully!");
            } else {
                System.out.println("❌ Expense not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating expense: " + e.getMessage());
        }
    }

    // 📌 Delete an Expense
    public static void deleteExpense(int expenseID) {
        String sql = "DELETE FROM Expenses WHERE ExpenseID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, expenseID);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Expense deleted successfully!");
            } else {
                System.out.println("❌ Expense not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting expense: " + e.getMessage());
        }
    }

    // 📌 View All Expenses
    public static void viewExpenses() {
        String sql = "SELECT * FROM Expenses";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("📋 Expense List:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("ExpenseID") +
                        ", Dept ID: " + rs.getInt("DepartmentID") +
                        ", Cat ID: " + rs.getInt("CategoryID") +
                        ", Desc: " + rs.getString("Description") +
                        ", Amount: " + rs.getDouble("Amount") +
                        ", Date: " + rs.getDate("ExpenseDate"));
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving expenses: " + e.getMessage());
        }
    }

    // 📌 Add Department (or return existing one)
    public static int addDepartmentAndGetID(String departmentName) {
        String checkSql = "SELECT DepartmentID FROM Departments WHERE DepartmentName = ?";
        String insertSql = "INSERT INTO Departments (DepartmentName) VALUES (?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql);
             PreparedStatement insertStmt = conn.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Check if department exists
            checkStmt.setString(1, departmentName);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("DepartmentID");  // Return existing ID
            }

            // Insert new department
            insertStmt.setString(1, departmentName);
            insertStmt.executeUpdate();
            ResultSet generatedKeys = insertStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);  // Return new ID
            }

        } catch (SQLException e) {
            System.out.println("Error handling department: " + e.getMessage());
        }
        return -1; // Return invalid ID if operation fails
    }

    // 📌 Add Category (or return existing one)
    public static int addCategoryAndGetID(String categoryName) {
        String checkSql = "SELECT CategoryID FROM Categories WHERE CategoryName = ?";
        String insertSql = "INSERT INTO Categories (CategoryName) VALUES (?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql);
             PreparedStatement insertStmt = conn.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Check if category exists
            checkStmt.setString(1, categoryName);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("CategoryID");  // Return existing ID
            }

            // Insert new category
            insertStmt.setString(1, categoryName);
            insertStmt.executeUpdate();
            ResultSet generatedKeys = insertStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);  // Return new ID
            }

        } catch (SQLException e) {
            System.out.println("Error handling category: " + e.getMessage());
        }
        return -1; // Return invalid ID if operation fails
    }

    // 📌 Get Expense by ID
    public static void getExpenseByID(int expenseID) {
        String sql = "SELECT * FROM Expenses WHERE ExpenseID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, expenseID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("📋 Expense Details:");
                System.out.println("ID: " + rs.getInt("ExpenseID"));
                System.out.println("Department ID: " + rs.getInt("DepartmentID"));
                System.out.println("Category ID: " + rs.getInt("CategoryID"));
                System.out.println("Description: " + rs.getString("Description"));
                System.out.println("Amount: " + rs.getDouble("Amount"));
                System.out.println("Date: " + rs.getDate("ExpenseDate"));
            } else {
                System.out.println("❌ No expense found with that ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving expense: " + e.getMessage());
        }
    }

    // 📌 View Expenses by Department
    public static void viewExpensesByDepartment(int departmentID) {
        String sql = "SELECT * FROM Expenses WHERE DepartmentID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, departmentID);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("\n📋 Expenses for Department ID: " + departmentID);
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("ExpenseID") +
                        ", Cat ID: " + rs.getInt("CategoryID") +
                        ", Desc: " + rs.getString("Description") +
                        ", Amount: " + rs.getDouble("Amount") +
                        ", Date: " + rs.getDate("ExpenseDate"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // 📌 View Expenses by Category
    public static void viewExpensesByCategory(int categoryID) {
        String sql = "SELECT * FROM Expenses WHERE CategoryID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, categoryID);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("\n📋 Expenses for Category ID: " + categoryID);
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("ExpenseID") +
                        ", Dept ID: " + rs.getInt("DepartmentID") +
                        ", Desc: " + rs.getString("Description") +
                        ", Amount: " + rs.getDouble("Amount") +
                        ", Date: " + rs.getDate("ExpenseDate"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void calculateTotalExpensesByCategory(int categoryID) {
        String sql = "SELECT SUM(Amount) AS Total FROM Expenses WHERE CategoryID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, categoryID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                double total = rs.getDouble("Total");
                System.out.println("\n💰 Total Expenses : " + total);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void calculateTotalExpensesByDepartment(int departmentID) {
        String sql = "SELECT SUM(Amount) AS Total FROM Expenses WHERE DepartmentID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, departmentID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                double total = rs.getDouble("Total");
                System.out.println("\n💰 Total Expenses : " + total);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // 📌 Calculate Total Expenses in a Date Range
    public static void calculateTotalExpensesInRange(String startDate, String endDate) {
        String sql = "SELECT SUM(Amount) AS Total FROM Expenses WHERE ExpenseDate BETWEEN ? AND ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, startDate);
            pstmt.setString(2, endDate);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                double total = rs.getDouble("Total");
                System.out.println("\n💰 Total Expenses from " + startDate + " to " + endDate + ": " + total);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // 📌 Generate Monthly Report
    public static void generateMonthlyReport() {
        String sql = "SELECT DATE_FORMAT(ExpenseDate, '%Y-%m') AS Month, SUM(Amount) AS Total FROM Expenses GROUP BY Month ORDER BY Month DESC";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n📆 Monthly Expense Report:");
            while (rs.next()) {
                System.out.println("Month: " + rs.getString("Month") + " | Total: " + rs.getDouble("Total"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}