package com.company.command;

import com.company.ExpenseManager;
import com.company.ValidationUtils;

import java.util.Scanner;

public class ViewExpensesByDepartmentCommand implements Command {
    @Override
    public void execute(Scanner scanner) {
        System.out.print("Enter Department ID: ");
        String viewDeptIdInput = scanner.nextLine();
        if (!ValidationUtils.isValidPositiveInt(viewDeptIdInput)) {
            System.out.println("Invalid Department ID.");
        }
        int viewDeptID = Integer.parseInt(viewDeptIdInput);
        ExpenseManager.calculateTotalExpensesByDepartment(viewDeptID);
        ExpenseManager.viewExpensesByDepartment(viewDeptID);
    }
}
