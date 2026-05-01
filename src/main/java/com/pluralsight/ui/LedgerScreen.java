package com.pluralsight.ui;
import com.pluralsight.model.Transaction;
import com.pluralsight.model.Ledger;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * LedgerScreen.java
 *
 * INSTANCE CLASS - Handles the Ledger menu and transaction displays.
 *
 * RESPONSIBILITIES:
 * - Display the Ledger menu
 * - Process user choices
 * - Display all transactions
 * - Display deposits only
 * - Display payments only
 * - Launch the Reports screen
 * - Return to Home when user is done
 *
 * MENU OPTIONS:
 *   A) All - Display all transactions
 *   D) Deposits - Display only deposits
 *   P) Payments - Display only payments
 *   R) Reports - Open Reports screen
 *   H) Home - Return to home screen
 *
 * USES:
 * - Ledger (for getting transaction data)
 * - Scanner (for reading user menu choices)
 * - DisplayHelper (for printing tables and totals) - STATIC calls
 * - ReportsScreen (when user selects Reports) - INSTANCE composition
 *
 * USED BY:
 * - HomeScreen (when user selects 'L' for Ledger)
 *
 * COMPOSITION PATTERN:
 * - This class CONTAINS a ReportsScreen object
 * - When user selects 'R', we call reportsScreen.show()
 * - This is called "composition" - building larger classes from smaller ones
 */

public class LedgerScreen {

    // ===== INSTANCE FIELDS =====
    private Ledger ledger;
    private Scanner scanner;
    private ReportsScreen reportsScreen;  // COMPOSITION - we own a ReportsScreen


    // ===== CONSTRUCTOR =====
    /**
     * Creates a LedgerScreen with the given Ledger and Scanner.
     *
     * COMPOSITION: We also create a ReportsScreen here, passing it the same
     * Ledger and Scanner. This way ReportsScreen uses the SAME ledger
     * and scanner that LedgerScreen uses.
     *
     * @param ledger - The Ledger to get transaction data from
     * @param scanner - The Scanner to read user input from
     */
    public LedgerScreen(Ledger ledger, Scanner scanner) {
        this.ledger = ledger;
        this.scanner = scanner;
        // Create the ReportsScreen here, passing the same dependencies
        this.reportsScreen = new ReportsScreen(ledger, scanner);
    }


    // ===== PUBLIC METHOD =====

    /**
     * Shows the Ledger menu and handles user input.
     * Loops until user selects 'H' (Home).
     *
     * Called by: HomeScreen when user presses 'L'
     */
    public void show() {
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
                    reportsScreen.show();  // Launch Reports screen
                    break;
                case "H":
                    inLedger = false;  // Exit loop, return to Home
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }


    // ===== PRIVATE METHODS - TRANSACTION DISPLAYS =====

    /**
     * Display ALL transactions (newest first).
     */
    private void displayAllTransactions() {
        ArrayList<Transaction> transactions = ledger.getAllTransactions();

        DisplayHelper.printScreenTitle("LEDGER - ALL TRANSACTIONS");

        if (transactions.isEmpty()) {
            DisplayHelper.printNoTransactionsFound("transactions");
            return;
        }

        DisplayHelper.printTransactionTable(transactions, false);
        System.out.println("Total: " + transactions.size() + " transactions\n");
    }

    /**
     * Display ONLY DEPOSITS (positive amounts, newest first).
     */
    private void displayDepositsOnly() {
        ArrayList<Transaction> deposits = ledger.getDeposits();

        DisplayHelper.printScreenTitle("LEDGER - DEPOSITS");

        if (deposits.isEmpty()) {
            DisplayHelper.printNoTransactionsFound("deposits");
            return;
        }

        DisplayHelper.printTransactionTable(deposits, false);

        // Calculate and show total
        double total = ledger.getTotalIncomeFor(deposits);
        DisplayHelper.printTotal("Total Deposits", total);
    }

    /**
     * Display ONLY PAYMENTS (negative amounts shown as positive, newest first).
     */
    private void displayPaymentsOnly() {
        ArrayList<Transaction> payments = ledger.getPayments();

        DisplayHelper.printScreenTitle("LEDGER - PAYMENTS");

        if (payments.isEmpty()) {
            DisplayHelper.printNoTransactionsFound("payments");
            return;
        }

        // Pass 'true' to show payment amounts as POSITIVE (e.g., $89.50 instead of -$89.50)
        DisplayHelper.printTransactionTable(payments, true);

        // Calculate and show total
        double total = ledger.getTotalExpensesFor(payments);
        DisplayHelper.printTotal("Total Payments", total);
    }


    // ===== PRIVATE HELPER METHOD =====

    /**
     * Displays the Ledger menu options.
     */
    private void displayLedgerMenu() {
        System.out.println("\nLedger");
        System.out.println("-------");
        System.out.println("A) All");
        System.out.println("D) Deposits");
        System.out.println("P) Payments");
        System.out.println("R) Reports");
        System.out.println("H) Home");
        System.out.print("\nEnter Option: ");
    }
}