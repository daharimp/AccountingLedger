package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Ledger.java
 *
 * The ledger is the "container" that controls all transactions.
 * - Stores transactions in an ArrayList
 * - Loads transactions from transactions.csv file
 * - Saves transactions.csv file
 * - Provides methods to filter and calculate on transactions.
 *
 *  This is how Transaction objects get used by the rest of the app.
 */

public class Ledger {
    // ArrayList that hold transactions in memory.
    private ArrayList<Transaction> transactions;


    private static final String fileName = "transaction.csv";

    // Constructor creates a new Ledger and loads all transactions on file

    public Ledger() {
        transactions = new ArrayList<>();
    }
    // === Methods ===

    // Adds a new Transaction to the ledger AND saves to file
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
    // Returns ALL transactions starting from most recent transaction
    // Ledger display (A) All option
    public ArrayList<Transaction> getAllTransactions() {
        // Sort newest first (reverse order)
        ArrayList<Transaction> sorted = new ArrayList<>(transactions);
        Collections.reverse(sorted);
        return sorted;
    }

    // Method to return deposits ONLY (amount > 0).
    // Ledger display (D) Deposits option
    public ArrayList<Transaction> getDeposits() {
        ArrayList<Transaction> deposits = new ArrayList<>();

        // Loop through ALL transactions
        for (Transaction tx : transactions) {
            if (tx.isDeposit()) {
                deposits.add(tx);
            }
        }
        Collections.reverse(deposits);
        return deposits;
    }

    // Method for getting payments.
    /**
     *
     * Method that Returns only PAYMENT transactions.
     * Ledger display: (P) Payments option
     *
     */

    public ArrayList<Transaction> getPayments() {
        ArrayList<Transaction> payments = new ArrayList<>();

        // Loop through all transactions
        for (Transaction tx : transactions) {
            if (tx.isPayment()) {
                payments.add(tx);
            }
        }

        // Sort newest first
        Collections.reverse(payments);
        return payments;
    }
    /**
     *Returns all transactions that match a vendor's name
     * Ledger display: (S) Search by vendor
     */

    public ArrayList<Transaction> searchByVendor(String vendorName) {
        ArrayList<Transaction> results = new ArrayList<>();

        // Loop through transactions again:
        for (Transaction tx : transactions){
            // Check if vendor returns the search term (not case sensitive).
            if (tx.getVendor().toLowerCase().contains(vendorName.toLowerCase())){
                results.add(tx);
            }
        }
        Collections.reverse(results);
        return results;
    }




    private void loadTransactions() {
        File file = new File(fileName);

        // If file does not exist, run program first

        if (!file.exists()) {
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }
                // Split by pipe character
                String[] parts = line.split("\\|");

                // Make sure we all 5 parts of Transaction are shown
                if (parts.length  == 5) {
                    String date = parts[0];
                    String time = parts[1];
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);

                    // Create a Transaction object and add to Array List

                    Transaction tx = new Transaction(date, time, description, vendor, amount);

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
    }
}
