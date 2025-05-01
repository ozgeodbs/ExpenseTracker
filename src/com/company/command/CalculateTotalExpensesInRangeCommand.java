package com.company.command;

import com.company.ExpenseManager;
import com.company.ValidationUtils;

import java.util.Scanner;

public class CalculateTotalExpensesInRangeCommand implements Command {
    @Override
    public void execute(Scanner scanner) {
        System.out.print("Enter Start Date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        if (!ValidationUtils.isValidDate(startDate)) {
            System.out.println("❌ Invalid Start Date.");
        }

        System.out.print("Enter End Date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();
        if (!ValidationUtils.isValidDate(endDate)) {
            System.out.println("❌ Invalid End Date.");
        }

        ExpenseManager.calculateTotalExpensesInRange(startDate, endDate);
    }
}
