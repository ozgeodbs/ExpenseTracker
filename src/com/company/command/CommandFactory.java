package com.company.command;

import java.util.Scanner;

public class CommandFactory {

    public static Command command(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                return new AddExpenseCommand();
            case 2:
                return new ViewExpenseCommand();
            case 3:
                return new UpdateExpenseCommand();
            case 4:
                return new DeleteExpenseCommand();
            case 5:
                return new AddDepartmentCommand();
            case 6:
                return new AddCategoryCommand();
            case 7:
                return new ViewExpensesByDepartmentCommand();
            case 8:
                return new ViewExpensesByCategoryCommand();
            case 9:
                return new CalculateTotalExpensesInRangeCommand();
            case 10:
                return new GenerateMonthlyReportCommand();
            case 11:
                System.out.println("Exiting...");
                scanner.close();
            default:
                throw new IllegalArgumentException("Invalid choice!");
        }
    }
}
