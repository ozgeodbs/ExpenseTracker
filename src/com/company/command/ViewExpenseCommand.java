package com.company.command;

import com.company.ExpenseManager;

import java.util.Scanner;

public class ViewExpenseCommand  implements Command {

    @Override
    public void execute(Scanner scanner) {
        ExpenseManager.viewExpenses();
    }
}