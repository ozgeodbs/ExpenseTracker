package com.company.command;

import com.company.ExpenseManager;
import com.company.ValidationUtils;
import java.util.Scanner;

public class UpdateExpenseCommand  implements Command {

    @Override
    public void execute(Scanner scanner) {
        System.out.print("Enter Expense ID to update: ");
        String expIdInput = scanner.nextLine();
        if (!ValidationUtils.isValidPositiveInt(expIdInput)) {
            System.out.println("❌ Invalid Expense ID.");
        }
        int expID = Integer.parseInt(expIdInput);

        System.out.print("Enter New Description: ");
        String newDesc = scanner.nextLine();
        if (!ValidationUtils.isValidDescription(newDesc)) {
            System.out.println("❌ Invalid Description.");
        }

        System.out.print("Enter New Amount: ");
        String newAmountInput = scanner.nextLine();
        if (!ValidationUtils.isValidAmount(newAmountInput)) {
            System.out.println("❌ Invalid Amount.");
        }
        double newAmount = Double.parseDouble(newAmountInput);

        System.out.print("Enter New Date (YYYY-MM-DD): ");
        String newDate = scanner.nextLine();
        if (!ValidationUtils.isValidDate(newDate)) {
            System.out.println("❌ Invalid Date.");
        }

        ExpenseManager.updateExpense(expID, newDesc, newAmount, newDate);
    }
}
