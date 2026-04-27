package com.pluralsight;

import java.io.*;
import java.lang.reflect.Array;
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

    //==== Method for getting payments ====.
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


    // ==== Report Methods ====
    // These are the methods to generate reports from the data.
    // Methods are based on time ranges (ie, "Month to Date", "Previous Month" etc)

    /**
     * Month to Date Report
     * Returns transactions from the first of the current month to the current day.
     * Used by: Reports (1) Month to Date
     */

    public ArrayList<Transaction> getMonthToDateReport() {
        ArrayList<Transaction> report = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate firstOfMonth = today.withDayOfMonth(1);

        for (Transaction tx : transactions) {
            LocalDate txDate = LocalDate.parse(tx.getDate());

            if (!txDate.isBefore(firstOfMonth) && !txDate.isAfter(today)) {
                report.add(tx);
            }
        }
        Collections.reverse(report);
        return report;
    }

    /**
     * Previous Month Report:
     * Returns all transactions from the previous calendar month.
     * Used by: Reports (2) Previous Month.
     */
    public ArrayList<Transaction> getPreviousMonthReport() {
        ArrayList<Transaction> report = new ArrayList<>();
        LocalDate today = LocalDate.now();

        // Now we go back on month:
        YearMonth previousMonth = YearMonth.now().minusMonths(1);
        LocalDate firstOfPrevMonth = previousMonth.atDay(1);
        LocalDate lastOfPrevMonth = previousMonth.atEndOfMonth();

        for (Transaction tx : transactions) {
            LocalDate txDate = LocalDate.parse(tx.getDate());

            if (!txDate.isBefore(firstOfPrevMonth) && !txDate.isAfter(lastOfPrevMonth)) {
                report.add(tx);
            }

        }
        Collections.reverse(report);
        return report;
    }
    public ArrayList<Transaction> getYearToDateReport() {
        ArrayList<Transaction> report = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate firstOfYear = LocalDate.of(today.getYear(), 1, 1);

        for (Transaction tx : transactions) {
            LocalDate txDate = LocalDate.parse(tx.getDate());

            if (!txDate.isBefore(firstOfYear) && !txDate.isAfter(today)) {
                report.add(tx);
            }
        }
        Collections.reverse(report);
        return report;
    }


    /**
     * Previous Year Report:
     * Returns transactions from the 1st of the current Year (ex: Since 2026-01-01)
     * Used by: Reports (4) Previous Year.
     */

    public ArrayList<Transaction> getPreviousYearReport() {
        ArrayList<Transaction> report = new ArrayList<>();
        int previousYear = LocalDate.now().getYear() - 1;
        LocalDate firstOfPrevYear = LocalDate.of(previousYear, 1, 1);
        LocalDate lastOfPrevYear = LocalDate.of(previousYear, 12, 31);

        for (Transaction tx : transactions) {
            LocalDate txDate = LocalDate.parse(tx.getDate());

            if (!txDate.isBefore(firstOfPrevYear) && !txDate.isAfter(lastOfPrevYear)) {
                report.add(tx);
            }
        }
        Collections.reverse(report);
        return report;
    }

    // ===== Methods for Calculation ====


    /**
     * getTotalIncome:
     * Returns total for ALL DEPOSITS
     * Used by: Reports to show total income
     */

    public double getTotalIncome() {
        double total = 0;

        for (Transaction tx : transactions) {
            if (tx.isDeposit()) {
                total += tx.getAmount(); // Make the return positive
            }
        }

        return total;
    }

    /**
     *
     * getTotalExpense:
     * Method to return ALL Payments (negative amounts shown as positive)
     * Used by Reports: To show total payments made
     */
    public double getTotalExpenses() {
        double total = 0;

        for (Transaction tx : transactions) {
            if (tx.isPayment()) {
                total += Math.abs(tx.getAmount());
            }
        }
        return total;
    }
    /**
     *
     * getNetIncome:
     * Returns Income after factoring in Total Expenses (income - expenses)
     * Used by Reports: to show NET income
     */
    public double getNetIncome() {
        return (getTotalIncome() - getTotalExpenses());
    }

    /**
     *
     * getTotalIncomeFor:
     * Returns total income for a specific list of transactions
     * Used for Reports to show filtered transactions
     */

    public double getTotalIncomeFor(ArrayList<Transaction> txList) {
        double total = 0;
        for (Transaction tx : txList) {
            if (tx.isDeposit()) {
                total += tx.getAmount();
            }
        }
        return total;
    }

    /**
    * getTotalExpensesFor:
    * Returns total expenses for a specific list of transactions
    * Useful for Reports: showing filtered transactions
    */

    public double getTotalExpensesFor(ArrayList<Transaction> txList) {
        double total = 0;
        for (Transaction tx : txList) {
            if (tx.isPayment()) {
                total += Math.abs(tx.getAmount());
            }
        }
        return total;
    }




















}