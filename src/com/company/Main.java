package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nExpense Tracking System");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Update Expense");
            System.out.println("4. Delete Expense");
            System.out.println("5. Add Department");
            System.out.println("6. Add Category");
            System.out.println("7. View Expenses by Department");
            System.out.println("8. View Expenses by Category");
            System.out.println("9. Calculate Total Expenses (Date Range)");
            System.out.println("10. Generate Monthly Expense Report");
            System.out.println("11. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Handle Department selection or creation
                    System.out.print("Enter Department ID (or type 'new' to create a department): ");
                    String deptInput = scanner.nextLine();
                    int deptID;
                    if (deptInput.equalsIgnoreCase("new")) {
                        System.out.print("Enter New Department Name: ");
                        String deptName = scanner.nextLine();
                        deptID = ExpenseManager.addDepartmentAndGetID(deptName);
                    } else {
                        deptID = Integer.parseInt(deptInput);
                    }

                    // Handle Category selection or creation
                    System.out.print("Enter Category ID (or type 'new' to create a category): ");
                    String catInput = scanner.nextLine();
                    int catID;
                    if (catInput.equalsIgnoreCase("new")) {
                        System.out.print("Enter New Category Name: ");
                        String catName = scanner.nextLine();
                        catID = ExpenseManager.addCategoryAndGetID(catName);
                    } else {
                        catID = Integer.parseInt(catInput);
                    }

                    // Collect remaining expense details
                    System.out.print("Enter Description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Enter Amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Expense Date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();

                    // Add the expense
                    ExpenseManager.addExpense(deptID, catID, desc, amount, date);
                    break;

                case 2:
                    ExpenseManager.viewExpenses();
                    break;
                case 3:
                    System.out.print("Enter Expense ID to update: ");
                    int expID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter New Description: ");
                    String newDesc = scanner.nextLine();
                    System.out.print("Enter New Amount: ");
                    double newAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter New Date (YYYY-MM-DD): ");
                    String newDate = scanner.nextLine();
                    ExpenseManager.updateExpense(expID, newDesc, newAmount, newDate);
                    break;
                case 4:
                    System.out.print("Enter Expense ID to delete: ");
                    int deleteID = scanner.nextInt();
                    ExpenseManager.deleteExpense(deleteID);
                    break;
                case 5:
                    System.out.print("Enter Department Name: ");
                    String deptName = scanner.nextLine();
                    ExpenseManager.addDepartmentAndGetID(deptName);
                    break;
                case 6:
                    System.out.print("Enter Category Name: ");
                    String catName = scanner.nextLine();
                    ExpenseManager.addCategoryAndGetID(catName);
                    break;
                case 7:
                    System.out.print("Enter Department ID: ");
                    int viewDeptID = scanner.nextInt();
                    ExpenseManager.viewExpensesByDepartment(viewDeptID);
                    break;

                case 8:
                    System.out.print("Enter Category ID: ");
                    int viewCatID = scanner.nextInt();
                    ExpenseManager.viewExpensesByCategory(viewCatID);
                    break;

                case 9:
                    System.out.print("Enter Start Date (YYYY-MM-DD): ");
                    String startDate = scanner.nextLine();
                    System.out.print("Enter End Date (YYYY-MM-DD): ");
                    String endDate = scanner.nextLine();
                    ExpenseManager.calculateTotalExpensesInRange(startDate, endDate);
                    break;

                case 10:
                    ExpenseManager.generateMonthlyReport();
                    break;

                case 11:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}