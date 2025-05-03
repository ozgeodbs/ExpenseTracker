package com.company.command;

import com.company.ExpenseManager;
import com.company.ValidationUtils;

import java.util.Scanner;

public class ViewExpensesByCategoryCommand implements Command {
    @Override
    public void execute(Scanner scanner) {
        System.out.print("Enter Category ID: ");
        String viewCatIdInput = scanner.nextLine();
        if (!ValidationUtils.isValidPositiveInt(viewCatIdInput)) {
            System.out.println("Invalid Category ID.");
        }
        int viewCatID = Integer.parseInt(viewCatIdInput);
        ExpenseManager.calculateTotalExpensesByCategory(viewCatID);
        ExpenseManager.viewExpensesByCategory(viewCatID);
    }
}
