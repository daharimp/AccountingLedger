package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// ==== File I/O Methods ====
public class FileManager {
    private static final String fileName = "transactions.csv";

    /**
     * Loads all transactions from trasactions.csv file.
     * Called automatically when Ledger is created.
     * <p>
     * Example Line: 2026-04-27|04:51:25|Lunch|-78.60
     */
    public static ArrayList<Transaction> loadTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        File thisFile = new File(fileName);


        // If file doesn't exist yet (first run)
        if (!thisFile.exists()) {
            System.out.println("No existing transactions. Creating file.");
            return transactions;
        }

        try (Scanner scanner = new Scanner(thisFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }

                Transaction transaction = parseTransactionFrom(line);

                if (transaction != null) {
                    transactions.add(transaction);
                }
            }

            System.out.println("Loaded " + transactions.size() + " transactions from file.");

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }

        return transactions;
    }

    /**
     *
     * saveTransaction():
     * Saves all transactions to transactions.csv file
     * Called every time a new transcaction is added.
     * This overwrites the entire field with current transactions
     */
    public static void saveTransactions(ArrayList<Transaction> transactions) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Transaction tx : transactions) {
                // Uses the formatForCSV() method from Trancaction!
                writer.println(tx.formatforCSV());
            }
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }
    private static Transaction parseTransactionFrom(String line) {
        // Pipe character split
        try {
            String[] parts = line.split("\\|");

            if (parts.length != 5) {
                System.out.println("Warning: Invalid transaction line: " + line);
                return null;
            }

            String date = parts[0];
            String time = parts[1];
            String description = parts[2];
            String vendor = parts[3];
            double amount = Double.parseDouble(parts[4]);

            // Create and return Transaction object
            return new Transaction(date, time, description, vendor, amount);
        } catch (NumberFormatException e) {
            System.out.println("Warning: Can't parse amount in line: " + line);
            return null;
        } catch (Exception e) {
            System.out.println("Warning: error parsing line: " + line);
            return null;
        }
    }
}
