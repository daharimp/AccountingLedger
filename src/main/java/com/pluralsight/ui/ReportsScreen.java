package com.pluralsight.ui;

import com.pluralsight.model.Ledger;
import com.pluralsight.model.Transaction;

import java.util.ArrayList;
import java.util.Scanner;

public class ReportsScreen {
    private Ledger ledger;
    private Scanner scanner;

    public ReportsScreen(Ledger ledger, Scanner scanner) {
        this.ledger = ledger;
        this.scanner = scanner;
    }
    public void show() {
        boolean inReports = true;

        while (inReports) {
            displayReportsMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showMonthToDateReport();
                    break;
                case "2":
                    showPreviousMonthReport();
                    break;
                case "3":
                    showYearToDateReport();
                    break;
                case "4":
                    showPreviousYearReport();
                    break;
                case "5":
                    searchByVendor();
                    break;
                case "0":
                    inReports = false;  // Exit loop, return to Ledger
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }
// ===== REPORTS DISPLAY METHODS =====
    /**
     * Month to Date Report:
     * Shows transactions from 1st of the current month to today
     * Calculates: Income, Expense & Net Income
     */
        /**
         * Month to Date Report
         * Shows transactions from the 1st of current month to today.
         */
        private void showMonthToDateReport() {
            ArrayList<Transaction> transactions = ledger.getMonthToDateReport();
            displayReport("MONTH TO DATE REPORT", transactions);
        }

    /**
     * Previous Month Report
     * Shows all transactions from the previous calendar month.
     */
    private void showPreviousMonthReport() {
        ArrayList<Transaction> transactions = ledger.getPreviousMonthReport();
        displayReport("PREVIOUS MONTH REPORT", transactions);
    }

    /**
     * Year to Date Report
     * Shows transactions from January 1st of current year to today.
     */
    private void showYearToDateReport() {
        ArrayList<Transaction> transactions = ledger.getYearToDateReport();
        displayReport("YEAR TO DATE REPORT", transactions);
    }

    /**
     * Previous Year Report
     * Shows all transactions from the previous calendar year.
     */
    private void showPreviousYearReport() {
        ArrayList<Transaction> transactions = ledger.getPreviousYearReport();
        displayReport("PREVIOUS YEAR REPORT", transactions);
    }

    /**
     * Search by Vendor
     * Prompts user for vendor name and displays matching transactions.
     */
    private void searchByVendor() {
        System.out.print("\nEnter vendor name to search >>> ");
        String vendorName = scanner.nextLine();

        ArrayList<Transaction> results = ledger.searchByVendor(vendorName);

        DisplayHelper.printScreenTitle("SEARCH RESULTS FOR: " + vendorName);

        if (results.isEmpty()) {
            DisplayHelper.printNoTransactionsFound("transactions");
            return;
        }

        DisplayHelper.printTransactionTable(results, false);
        System.out.println("Total: " + results.size() + " transactions\n");
    }
    private void displayReport(String title, ArrayList<Transaction> transactions ) {
        DisplayHelper.printScreenTitle(title);
        if (transactions.isEmpty()) {
            System.out.println("No transactions in this period\n");
            return;
        }
        DisplayHelper.printTransactionTable(transactions, false);

        double income = ledger.getTotalIncomeFor(transactions);
        double expenses = ledger.getTotalExpensesFor(transactions);

        DisplayHelper.printSummary(income, expenses);
    }
    private static void displayReportsMenu() {
        System.out.println("\nReports");
        System.out.println("-----------");
        System.out.println("1) Month to Date");
        System.out.println("2) Previous Month");
        System.out.println("3) Year to Date");
        System.out.println("4) Previous Year");

        System.out.println("5) Search by Vendor");
        System.out.println("0) Back");
        System.out.println("\nEnter Option: ");
    }
}
