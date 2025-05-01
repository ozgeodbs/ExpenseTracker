package com.company.command;

import com.company.ExpenseManager;
import java.util.Scanner;

public class AddExpenseCommand implements Command {

    @Override
    public void execute(Scanner scanner) {
        System.out.print("Enter Department ID: ");
        int departmentID = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Category ID: ");
        int categoryID = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        ExpenseManager.addExpense(departmentID, categoryID, description, amount, date);
    }
}
