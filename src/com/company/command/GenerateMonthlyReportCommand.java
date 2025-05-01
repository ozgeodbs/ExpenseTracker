package com.company.command;

import com.company.ExpenseManager;

import java.util.Scanner;

public class GenerateMonthlyReportCommand implements Command {
    @Override
    public void execute(Scanner scanner) {
        ExpenseManager.generateMonthlyReport();
    }
}
