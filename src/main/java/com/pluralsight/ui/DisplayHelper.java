package com.pluralsight.ui;

import com.pluralsight.model.Transaction;

import java.util.ArrayList;

public class DisplayHelper {
    /**
     * Prints a formatted table of transactions
     * Used by: Ledger Screen and Reports Screen
     *
     */
    public static void printTransactionTable(ArrayList<Transaction> transactions, boolean showAsAbolute) {
        // Print table header
        printTableHeader();

        // Loop through each transaction and print
        for (Transaction tx : transactions) {
            double amountToShow;
            if (showAsAbolute) {
                amountToShow = Math.abs(tx.getAmount());
            } else {
                amountToShow = tx.getAmount();
            }
            System.out.printf("%-12s | %-10s | %-20s | %-15s | $%10.2f\n",
                    tx.getDate(),
                    tx.getTime(),
                    tx.getDescription(),
                    tx.getVendor(),
                    amountToShow);
        }
        printDivider();
    }
    public static void  printScreenTitle(String title) {
        System.out.println("\n===== " + title + " =====");
    }
    public static void printTableHeader() {
        System.out.printf("%-12s | %-10s | %-20s | %-15s | %10s\n",
                "Date", "Time", "Description", "Vendor", "Amount");
        printDivider();
    }
    /**
     * Prints a divider line. Seperates sections in the table
     */
    public static void printDivider() {
        System.out.println("-----------------------------------------------------");
    }
    /**
     * Method to print the screen title
     * Example: "====== LEDGER - DEPOSITS"
     */
    public static void printNoTransactionsFound(String type) {
        System.out.println("No " + type + "found.\n");
    }

    /**
     * Method to print financial summary at the end of report
     * Shows: Total Income, Total Expenses, Net Income
     */
    public static void printSummary(double income, double expenses) {
        double net = income - expenses;

        System.out.printf("Total Income: $%10.2f\n", income);
        System.out.printf("Total Expenses: $%10.2f\n", expenses);
        System.out.printf("----------");
        System.out.printf("Net Income: $%10.2f\n", net);
    }
    /**
     * Method to print a single total line.
     * Example: "Total Deposits: $2,584.56"
     */
    public static void printTotal(String label, double amount) {
        System.out.printf("%s: $%.2f\n\n", label, amount);
    }
}
