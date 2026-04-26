package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    // Create Fields for each Transaction class
    private String date; // Format: yyyy-MM-dd
    private String time; // Format: HH:mm:ss
    private String description; // What the transaction was for
    private String vendor; // Who/what company manufactured (Amazon, Apple, HP)
    private double amount; // Positive for deposits, negative for payments

    // ===== CONSTRUCTOR =====
    // Creates a new Transaction with all required information.
    // This is called by other code (like Ledger) when creating a new transaction.


    public Transaction(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    // ===== Getter and Setter Methods =====

    // Methods used by other parts of the code!

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Helper Transaction Methods
    // Checks if transaction is a payment
    public boolean isPayment() {
        return amount < 0;
    }

    // Checks if transaction is a deposit (money coming in)

    public boolean isDeposit() {
        return amount > 0;
    }

    // ==== Display Method ====
    /**
     * Returns a formatted string of a transaction
     *
     * Useful for:
     * Displaying a single line
     * Debugging
     *
     * Logging
     * Example: "2026-04-26 | 4:20:35 | Amazon | Office Supplies | 95.60"
     */

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s | %.2f" ,
                date, time, vendor, description, amount);
    }
    // CSV format for saving to transactions.csv
    // (Description before vendor in CSV format

    public String formatforCSV() {
        return date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
    }
}
