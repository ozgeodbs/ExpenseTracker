package com.company.command;

import com.company.ExpenseManager;
import com.company.ValidationUtils;

import java.util.Scanner;

public class AddCategoryCommand implements Command {
    @Override
    public void execute(Scanner scanner) {
        System.out.print("Enter Category Name: ");
        String catName = scanner.nextLine();
        if (!ValidationUtils.isValidDescription(catName)) {
            System.out.println("❌ Invalid Category Name.");
        }
        ExpenseManager.addCategoryAndGetID(catName);
    }
}
