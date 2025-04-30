package com.company;

public class Expense {
    private int expenseID;
    private String description;
    private double amount;
    private String expenseDate;

    public Expense(int expenseID, String description, double amount, String expenseDate) {
        this.expenseID = expenseID;
        this.description = description;
        this.amount = amount;
        this.expenseDate = expenseDate;
    }

    public int getExpenseID() { return expenseID; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
    public String getExpenseDate() { return expenseDate; }

    @Override
    public String toString() {
        return "ExpenseID: " + expenseID + ", Description: " + description +
                ", Amount: " + amount + ", Date: " + expenseDate;
    }
}