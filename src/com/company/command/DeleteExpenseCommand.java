package com.company.command;

import com.company.ExpenseManager;
import com.company.ValidationUtils;

import java.util.Scanner;

public class DeleteExpenseCommand implements Command {
    @Override
    public void execute(Scanner scanner) {
        System.out.print("Enter Expense ID to delete: ");
        String delIdInput = scanner.nextLine();
        if (!ValidationUtils.isValidPositiveInt(delIdInput)) {
            System.out.println("❌ Invalid Expense ID.");
        }
        int deleteID = Integer.parseInt(delIdInput);
        ExpenseManager.deleteExpense(deleteID);
    }
}
