package com.pluralsight.ui;

import com.pluralsight.model.Ledger;
import com.pluralsight.model.Transaction;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Scanner;

public class TransactionInput {
    private Ledger ledger;
    private Scanner scanner;

    public TransactionInput(Ledger ledger, Scanner scanner) {
        this.ledger = ledger;
        this.scanner = scanner;
    }

    public void addDeposit() {
        System.out.println("\n===== ADD DEPOSIT =====");

        try {
            String date = dateInput("deposit");
            String time = timeInput("deposit");
            String description = descriptionInput();
            String vendor = vendorInput();
            double amount = amountInput("deposit");

            // Make sure the amount shows as POSITIVE ONLY with deposits
            amount = Math.abs(amount);

            Transaction deposit = new Transaction(date, time, description, vendor, amount);

            // Add to ledger and save to file
            ledger.addTransaction(deposit);

            System.out.println("Deposit successfully added!\n");
        } catch (NumberFormatException e) {
            System.out.println("Error: Amount must be a number. Returning home\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + " Returning home.\n");
        }
    }
    public void addPayment() {
        System.out.println("\n===== ADD PAYMENT =====");

        try {
            String date = dateInput("payment");
            String time = timeInput("payment");
            String description = descriptionInput();
            String vendor = vendorInput();
            double amount = amountInput("payment");

            // Make sure the amount shows as POSITIVE ONLY with deposits
            amount = Math.abs(amount) * -1;

            Transaction payment = new Transaction(date, time, description, vendor, amount);

            // Add to ledger and save to file
            ledger.addTransaction(payment);

            System.out.println("Payment successfully added!\n");

        } catch (NumberFormatException e) {
            System.out.println("Error: Amount must be a number. Returning home\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + " Returning home.\n");
        }
    }

    //===== PRIVATE CLASS METHODS =====
    // Only used inside the TransactionInput class

    /**
     * Prompts user input for date and validates format
     */
    private String dateInput(String transactionType) {
        System.out.println("Enter the " + transactionType + " date (yyyy-MM-dd) >>>> ");
        String date = scanner.nextLine();
        validateDate(date);
        return date;
    }

    /**
     * Prompts user for time input and validates the format
     * Used by: addDeposit() and addPayment()
     */
    private String timeInput(String transactionType) {
        System.out.println("Enter the " + transactionType + " time (HH:mm:ss) >>>> ");
        String time = scanner.nextLine();
        validateTime(time);
        return time;
    }

    /**
     * Prompts user for description.
     */
    private String descriptionInput() {
        System.out.println("Enter the description >>>> ");
        return scanner.nextLine();
    }

    /**
     * Prompts user for name of the vendor
     */
    private String vendorInput() {
        System.out.println("Enter the vendor name >>>>");
        return scanner.nextLine();
    }

    /**
     * Prompts user for amount
     * Input handling for invalid number (NumberFormatException)
     */
    private double amountInput(String transactionType) {
        System.out.println("Enter the " + transactionType + " amount >>>> ");
        return Double.parseDouble(scanner.nextLine());
    }

    // ===== TIME & DATE VALIDATORS =====
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
