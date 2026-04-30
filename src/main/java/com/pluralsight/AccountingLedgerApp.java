package com.pluralsight;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

/**
 * MAIN APPLICATION CLASS:
 *
 * 1. Creates a Ledger (which loads transactions from file)
 * 2. Display menus to the user
 * 3. Gets user input
 * 4. Calls appropriate Ledger methods
 * 5. Displays results
 * 6. Keeps running until Exit
 *
 */

public class AccountingLedgerApp {

    // Creates ledger used for the entire Main application
    private static Ledger ledger;

    // Scanner for all user input
    private static Scanner scanner;

    public static void main(String[] args) {
        ledger = new Ledger(); // Initialize the ledger (automatically loads transactions from file)
        scanner = new Scanner(System.in); // Start scanner for user input

        // Welcome message display
        displayWelcome();

        // Home Screen (Main Menu)
        homeScreen();

        // Scanner needs to be closed when done
        scanner.close();
    }

    /**
     * Home Screen: Shows main menu options
     * This method loops until the user chooses to exit
     * Options: Add Deposit, Make Payment, Ledger, Exit
     */
    private static void homeScreen() {
        boolean running = true;

        while (running) {
            displayHomeMenu();
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    addPayment();
                    break;
                case "L":
                     ledgerScreen();
                    break;
                case "X":
                    System.out.println("\nThank you for using MassWALLET Accounting. Goodbye");
                default:
                    System.out.println("Invalid option. Please try again.\n");
                }
        }
    }
    //===== Ledger Screen =====
    /**
     * Options:
     * A) All Transactions
     * D) Deposits
     * P) Payments
     * R) Reports
     * H) Home
     *
     */
    private static void ledgerScreen() {
        boolean inLedger = true;

        while (inLedger) {
            displayLedgerMenu();
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "A":
                    displayAllTransactions();
                    break;
                case "D":
                    displayDepositsOnly();
                    break;
                case "P":
                    displayPaymentsOnly();
                    break;
                case "R":

                    break;
                case "H":
                    inLedger = false; // Returns to home
                    break;
                default:
                    System.out.println("Invalid choice. Please try again\n");
            }
        }
    }
    private static void reportScreen() {
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
                   System.out.println("Search by Vendor screen");
                   break;
               case "0":
                   inReports = false;
                   break;
               default:
                   System.out.println("Invalid choice. Try Again.\n");
           }
       }
    }

    //===== LEDGER DISPLAY METHODS =====

    /**
     * Display all transactions (sorted by most recent):
     * 1. ledger.getAllTransactions returns an ArrayList of transactions
     * 2. Loops through each transaction
     * 3. Prints in a formatted table
     * 4. Shows total amount of transactions
     */
    private static void displayAllTransactions() {
        ArrayList<Transaction> transactions = ledger.getAllTransactions();

        System.out.println("\n===== Ledger - All Transactions =====");

        if (transactions.isEmpty()) {
            System.out.println("No transactions found\n");
            return;
        }

        // Header w/ format
        System.out.printf("%-12s | %-10s | %-20s | %-15s | %10s", // Header format with space alignment
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("-----------------------------------------------------");

        // Transaction loop
        for (Transaction tx : transactions) {
            System.out.printf("%-12s | %-10s | %-20s | %-15s | $%10.2f",
                    tx.getDate(),
                    tx.getTime(),
                    tx.getDescription(),
                    tx.getVendor(),
                    tx.getAmount());
        }

        System.out.println("-----------------------------------------------------");
        System.out.println("Total: " + transactions.size() + " transaction\n");
    }

    /**
     * Method to display ONLY DEPOSITS (positive amounts)
     */
    private static void displayDepositsOnly() {
        ArrayList<Transaction> deposits = ledger.getDeposits();

        System.out.println("\n===== LEDGER - DEPOSITS =====");

        if (deposits.isEmpty()) {
            System.out.println("No deposits found\n");
            return;
        }
        System.out.printf("%-12s | %-10s | %-20s | %-15s | %10s",
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("-----------------------------------------------------");

        // Transaction loop
        double total = 0;
        for (Transaction tx : deposits) {
            System.out.printf("%-12s | %-10s | %-20s | %-15s | $%10.2f",
                    tx.getDate(),
                    tx.getTime(),
                    tx.getDescription(),
                    tx.getVendor(),
                    tx.getAmount());
            total += tx.getAmount();
        }

        System.out.println("-----------------------------------------------------");
        System.out.printf("Total Deposits: $%.2f\n", total);
    }

    /**
     * Display ONLY PAYMENTS (negative amounts)
     */
    private static void displayPaymentsOnly() {
        ArrayList<Transaction> payments = ledger.getPayments();

        System.out.println("\n===== LEDGER - PAYMENTS =====");

        if (payments.isEmpty()) {
            System.out.println("No payments found\n");
            return;
        }
        System.out.printf("%-12s | %-10s | %-20s | %-15s | %10s",
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("-----------------------------------------------------");

        // Transaction loop
        double total = 0;
        for (Transaction tx : payments) {
            double txDisplayAmount = Math.abs(tx.getAmount());
            System.out.printf("%-12s | %-10s | %-20s | %-15s | $%10.2f",
                    tx.getDate(),
                    tx.getTime(),
                    tx.getDescription(),
                    tx.getVendor(),
                    txDisplayAmount);
            total += txDisplayAmount;
        }

        System.out.println("-----------------------------------------------------");
        System.out.printf("Total Payments: $%.2f\n", total);
    }

    // ===== REPORTS SCREEN =====
    /**
     * Reports Options:
     * 1) Month to Date
     * 2) Previous Month
     * 3) Year to Date
     * 4) Previous Year
     * 5) Search by vendor
     * 0) Back
     */
    private static void reportsScreen() {
        boolean inReports = true;
    }

    // ===== REPORTS DISPLAY METHODS =====
    /**
     * Month to Date Report:
     * Shows transactions from 1st of the current month to today
     * Calculates: Income, Expense & Net Income
     */
    private static void showMonthToDateReport() {
        ArrayList<Transaction> transactions = ledger.getMonthToDateReport();
        double income = ledger.getTotalIncomeFor(transactions);
        double expenses = ledger.getTotalExpensesFor(transactions);
        double net = income - expenses;

        displayFinancialReport("MONTH TO DATE REPORT:", transactions, income, expenses, net);
    }

    /**
     * Previous Month Report:
     * Shows all transaction from the prior month
     */
    private static void showPreviousMonthReport() {
        ArrayList<Transaction> transactions = ledger.getPreviousMonthReport();
        double income = ledger.getTotalIncomeFor(transactions);
        double expenses = ledger.getTotalExpensesFor(transactions);
        double net = income - expenses;

        displayFinancialReport("PREVIOUS MONTH REPORT:", transactions, income, expenses, net);
    }

    /**
     * Year to Date Report:
     * Shows transactions from Jan 1 of the current year to today
     */
    private static void showYearToDateReport() {
        ArrayList<Transaction> transactions = ledger.getYearToDateReport();
        double income = ledger.getTotalIncomeFor(transactions);
        double expenses = ledger.getTotalExpensesFor(transactions);
        double net = income - expenses;

        displayFinancialReport("YEAR TO DATE REPORT:", transactions, income, expenses, net);
    }

    /**
     * Previous Year Report
     * Shows transactions for the entire previous year
     */
    private static void showPreviousYearReport() {
        ArrayList<Transaction> transactions = ledger.getPreviousYearReport();
        double income = ledger.getTotalIncomeFor(transactions);
        double expenses = ledger.getTotalExpensesFor(transactions);
        double net = income - expenses;

        displayFinancialReport("PREVIOUS YEAR REPORT:", transactions, income, expenses, net);
    }


    // ====== DISPLAY FINANCIAL REPORT HELPER =====


    private static void displayFinancialReport(String reportTitle, ArrayList<Transaction> transactions, double income,
                                               double expenses, double net) {
        System.out.println("\n===== " + reportTitle + " =====");

        if (transactions.isEmpty()) {
            System.out.println("No transactions in this period.\n");
            return;
        }
        System.out.printf("%-12s | %-10s | %-20s | %-15s | %10s",
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("-----------------------------------------------------");

        for (Transaction tx : transactions) {
            System.out.printf("%-12s | %-10s | %-20s | %-15s | $%10.2f",
                    tx.getDate(),
                    tx.getTime(),
                    tx.getDescription(),
                    tx.getVendor(),
                    tx.getAmount());
        }
        // Print The Summary
        System.out.println("-----------------------------------------------------");
        System.out.printf("Total Income: $%5.2f", income);
        System.out.printf("Total Expenses: $%5.2f", expenses);
        System.out.println("----------");
        System.out.printf("Net Income: $%5.2f\n", net);
    }

    // ===== ADD DEPOSIT =====
    /**
    // Asks for date, time, description, vendor, amount.
    * Creates Transaction object and adds to Ledger
    * Returns Home
    */
    private static void addDeposit() {
        System.out.println("\n===== ADD DEPOSIT =====");

        try {
            // Get Date:
            System.out.println("Enter the deposit date (yyyy-MM-dd) >>>> ");
            String date = scanner.nextLine();
            validateDate(date);

            // Get Time:
            System.out.println("Enter the deposit time (HH:mm:ss) >>>> ");
            String time = scanner.nextLine();
            validateTime(time);

            // Get Description:
            System.out.println("Enter the deposit description >>>> ");
            String description = scanner.nextLine();

            // Get Vendor:
            System.out.println("Enter the vendor name >>>> ");
            String vendor = scanner.nextLine();

            // Get amount:
            System.out.println("Enter the deposit amount >>>> ");
            double amount = Double.parseDouble(scanner.nextLine());

            // Amount is always positive for a deposit
            amount = Math.abs(amount);

            Transaction deposit = new Transaction(date, time, description, vendor, amount);

            // Add to the Ledger (also saves to file)
            ledger.addTransaction(deposit);

            System.out.println("Deposit successfully added!\n");

        } catch (NumberFormatException e) {
            System.out.println("Error: Amount must be a number. Returning to home.\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + " Returning to home.\n");
        }
    }

    // ===== ADD PAYMENT =====
    /** Asks for date, time, description, vendor, amount.
    * Creates Transaction object and adds to Ledger
    * Same method creation as deposit but amount is stored as NEGATIVE
    */
    private static void addPayment() {
        System.out.println("\n===== ADD PAYMENT =====");

        try {
            // Get Date:
            System.out.println("Enter the payment date (yyyy-MM-dd) >>>> ");
            String date = scanner.nextLine();
            validateDate(date);

            // Get Time:
            System.out.println("Enter the payment time (HH:mm:ss) >>>> ");
            String time = scanner.nextLine();
            validateTime(time);

            // Get Description:
            System.out.println("Enter the payment description >>>> ");
            String description = scanner.nextLine();

            // Get Vendor:
            System.out.println("Enter the vendor name >>>> ");
            String vendor = scanner.nextLine();

            // Get amount:
            System.out.println("Enter the payment amount >>>> ");
            double amount = Double.parseDouble(scanner.nextLine());

            // Amount is always NEGATIVE for a payment
            amount = amount * -1;

            Transaction payment = new Transaction(date, time, description, vendor, amount);

            // Add to the Ledger (also saves to file)
            ledger.addTransaction(payment);

            System.out.println("Payment successfully added!\n");

        } catch (NumberFormatException e) {
            System.out.println("Error: Amount must be a number. Returning to home.\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + " Returning to home.\n");
        }
    }

    // ===== MENU DISPLAY METHODS =====

    private static void displayWelcome() {
    System.out.println("\n================================");
        System.out.println(" MassWALLET ACCOUNTING");
        System.out.println(" Track your finances!");
        System.out.println("================================\n");
    }
    private static void displayHomeMenu() {
        System.out.println("Home");
        System.out.println("------");
        System.out.println("D) Add Deposit");
        System.out.println("P) Make Payment");
        System.out.println("L) Ledger");
        System.out.println("X) Exit");
        System.out.println("\nEnter Option: ");
    }

    private static void displayLedgerMenu() {
        System.out.println("\nLedger");
        System.out.println("-------");
        System.out.println("A) All");
        System.out.println("D) Deposits");
        System.out.println("P) Payments");
        System.out.println("R) Reports");
        System.out.println("H) Home");
        System.out.println("\nEnter Option: ");
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

    //===== INPUT VAlIDATION METHODS =====
    // These 2 methods validate: Date & Time
    // Throws IllegalArgumentException if invalid

    // Validates that date is in format: yyyy-MM-dd
    private static void validateDate(String date) {
        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("Date must be in format yyyy-MM-dd");
        }
        try {
            LocalDate.parse(date);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date: " + date);
        }
    }

    private static void validateTime(String time) {
        if (!time.matches("\\d{2}:\\d{2}:\\d{2}")) {
            throw new IllegalArgumentException("Time must be in format HH:mm:ss");
        }
    }
}
