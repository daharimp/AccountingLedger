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

    // Creating Ledger used for the entire Main application
    private static Ledger ledger;

    // Create ONE Scanner for ALL user input
    private static Scanner scanner;

    public static void main(String[] args) {
        ledger = new Ledger(); // Initialize the ledger (automatically loads transactions from file)
        scanner = new Scanner(System.in); // Start scanner for user input

        // Display welcome message
        displayWelcome();

    }
    // Home Screen (Main Menu)

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
                     // Create ledgerScreen method
                    break;
                case "X":
                    System.out.println("\nThank you for using MassWALLET Accounting. Goodbye");
                default:
                    System.out.println("Invalid option. Please try again.\n");
                }
        }
    }
    // ===== ADD DEPOSIT =====
    // Asks for date, time, description, vendor, amount.
    // Creates Transaction object and adds to Ledger
    // Returns Home

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
    // Asks for date, time, description, vendor, amount.
    // Creates Transaction object and adds to Ledger
    // Same method creation as deposit but amount is stored as NEGATIVE

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
        System.out.println("\nEnter Option:");
    }

    private static void displayLedgerMenu() {
        System.out.println("\nLedger");
        System.out.println("-------");
        System.out.println("A) All");
        System.out.println("D) Deposits");
        System.out.println("P) Payments");
        System.out.println("R) Reports");
        System.out.println("H) Home");
        System.out.println("\nEnter Option:");
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
