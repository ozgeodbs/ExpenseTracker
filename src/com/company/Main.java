package com.company;

import com.company.command.Command;
import com.company.command.CommandFactory;

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
            System.out.println("11. Exit \n");
            System.out.print("Enter choice: ");

            String choiceInput = scanner.nextLine();
            if (!ValidationUtils.isValidPositiveInt(choiceInput)) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            int choice = Integer.parseInt(choiceInput);

            try {
                Command command = CommandFactory.command(choice, scanner);
                command.execute(scanner);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
