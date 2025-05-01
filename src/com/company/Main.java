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

            String choiceInput = scanner.nextLine();
            if (!ValidationUtils.isValidPositiveInt(choiceInput)) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            int choice = Integer.parseInt(choiceInput);

            switch (choice) {
                case 1:
                    // Handle Department
                    System.out.print("Enter Department ID (or type 'new' to create one): ");
                    String deptInput = scanner.nextLine();
                    int deptID;
                    if (deptInput.equalsIgnoreCase("new")) {
                        System.out.print("Enter New Department Name: ");
                        String deptName = scanner.nextLine();
                        deptID = ExpenseManager.addDepartmentAndGetID(deptName);
                    } else if (ValidationUtils.isValidPositiveInt(deptInput)) {
                        deptID = Integer.parseInt(deptInput);
                    } else {
                        System.out.println("❌ Invalid Department ID.");
                        break;
                    }

                    // Handle Category
                    System.out.print("Enter Category ID (or type 'new' to create one): ");
                    String catInput = scanner.nextLine();
                    int catID;
                    if (catInput.equalsIgnoreCase("new")) {
                        System.out.print("Enter New Category Name: ");
                        String catName = scanner.nextLine();
                        catID = ExpenseManager.addCategoryAndGetID(catName);
                    } else if (ValidationUtils.isValidPositiveInt(catInput)) {
                        catID = Integer.parseInt(catInput);
                    } else {
                        System.out.println("❌ Invalid Category ID.");
                        break;
                    }

                    // Description
                    System.out.print("Enter Description: ");
                    String desc = scanner.nextLine();
                    if (!ValidationUtils.isValidDescription(desc)) {
                        System.out.println("❌ Invalid Description.");
                        break;
                    }

                    // Amount
                    System.out.print("Enter Amount: ");
                    String amountInput = scanner.nextLine();
                    if (!ValidationUtils.isValidAmount(amountInput)) {
                        System.out.println("❌ Invalid Amount.");
                        break;
                    }
                    double amount = Double.parseDouble(amountInput);

                    // Date
                    System.out.print("Enter Expense Date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    if (!ValidationUtils.isValidDate(date)) {
                        System.out.println("❌ Invalid Date.");
                        break;
                    }

                    ExpenseManager.addExpense(deptID, catID, desc, amount, date);
                    break;

                case 2:
                    ExpenseManager.viewExpenses();
                    break;

                case 3:
                    System.out.print("Enter Expense ID to update: ");
                    String expIdInput = scanner.nextLine();
                    if (!ValidationUtils.isValidPositiveInt(expIdInput)) {
                        System.out.println("❌ Invalid Expense ID.");
                        break;
                    }
                    int expID = Integer.parseInt(expIdInput);

                    System.out.print("Enter New Description: ");
                    String newDesc = scanner.nextLine();
                    if (!ValidationUtils.isValidDescription(newDesc)) {
                        System.out.println("❌ Invalid Description.");
                        break;
                    }

                    System.out.print("Enter New Amount: ");
                    String newAmountInput = scanner.nextLine();
                    if (!ValidationUtils.isValidAmount(newAmountInput)) {
                        System.out.println("❌ Invalid Amount.");
                        break;
                    }
                    double newAmount = Double.parseDouble(newAmountInput);

                    System.out.print("Enter New Date (YYYY-MM-DD): ");
                    String newDate = scanner.nextLine();
                    if (!ValidationUtils.isValidDate(newDate)) {
                        System.out.println("❌ Invalid Date.");
                        break;
                    }

                    ExpenseManager.updateExpense(expID, newDesc, newAmount, newDate);
                    break;

                case 4:
                    System.out.print("Enter Expense ID to delete: ");
                    String delIdInput = scanner.nextLine();
                    if (!ValidationUtils.isValidPositiveInt(delIdInput)) {
                        System.out.println("❌ Invalid Expense ID.");
                        break;
                    }
                    int deleteID = Integer.parseInt(delIdInput);
                    ExpenseManager.deleteExpense(deleteID);
                    break;

                case 5:
                    System.out.print("Enter Department Name: ");
                    String deptName = scanner.nextLine();
                    if (!ValidationUtils.isValidDescription(deptName)) {
                        System.out.println("❌ Invalid Department Name.");
                        break;
                    }
                    ExpenseManager.addDepartmentAndGetID(deptName);
                    break;

                case 6:
                    System.out.print("Enter Category Name: ");
                    String catName = scanner.nextLine();
                    if (!ValidationUtils.isValidDescription(catName)) {
                        System.out.println("❌ Invalid Category Name.");
                        break;
                    }
                    ExpenseManager.addCategoryAndGetID(catName);
                    break;

                case 7:
                    System.out.print("Enter Department ID: ");
                    String viewDeptIdInput = scanner.nextLine();
                    if (!ValidationUtils.isValidPositiveInt(viewDeptIdInput)) {
                        System.out.println("❌ Invalid Department ID.");
                        break;
                    }
                    int viewDeptID = Integer.parseInt(viewDeptIdInput);
                    ExpenseManager.viewExpensesByDepartment(viewDeptID);
                    break;

                case 8:
                    System.out.print("Enter Category ID: ");
                    String viewCatIdInput = scanner.nextLine();
                    if (!ValidationUtils.isValidPositiveInt(viewCatIdInput)) {
                        System.out.println("❌ Invalid Category ID.");
                        break;
                    }
                    int viewCatID = Integer.parseInt(viewCatIdInput);
                    ExpenseManager.viewExpensesByCategory(viewCatID);
                    break;

                case 9:
                    System.out.print("Enter Start Date (YYYY-MM-DD): ");
                    String startDate = scanner.nextLine();
                    if (!ValidationUtils.isValidDate(startDate)) {
                        System.out.println("❌ Invalid Start Date.");
                        break;
                    }

                    System.out.print("Enter End Date (YYYY-MM-DD): ");
                    String endDate = scanner.nextLine();
                    if (!ValidationUtils.isValidDate(endDate)) {
                        System.out.println("❌ Invalid End Date.");
                        break;
                    }

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
