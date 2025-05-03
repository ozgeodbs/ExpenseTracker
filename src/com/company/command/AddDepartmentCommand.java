package com.company.command;

import com.company.ExpenseManager;
import com.company.ValidationUtils;

import java.util.Scanner;

public class AddDepartmentCommand implements Command {
    @Override
    public void execute(Scanner scanner) {
        System.out.print("Enter Department Name: ");
        String deptName = scanner.nextLine();
        if (!ValidationUtils.isValidDescription(deptName)) {
            System.out.println("Invalid Department Name.");
        }
        ExpenseManager.addDepartmentAndGetID(deptName);
    }
}
