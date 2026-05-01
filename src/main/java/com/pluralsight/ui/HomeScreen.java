package com.pluralsight.ui;
import com.pluralsight.model.Ledger;

import java.util.Scanner;


/**
 * HomeScreen.java
 *
 * INSTANCE CLASS - The top-level menu of the application.
 *
 * RESPONSIBILITIES:
 * - Display the welcome message
 * - Display the Home menu
 * - Process user choices
 * - Launch Add Deposit / Add Payment via TransactionInput
 * - Launch the Ledger screen
 * - Handle application exit
 *
 * MENU OPTIONS:
 *   D) Add Deposit - Launch deposit input
 *   P) Make Payment - Launch payment input
 *   L) Ledger - Open Ledger screen
 *   X) Exit - Quit the application
 *
 * USES (COMPOSITION):
 * - TransactionInput (for adding deposits/payments)
 * - LedgerScreen (for viewing transactions and reports)
 *
 * USED BY:
 * - AccountingLedgerApp (the main entry point)
 *
 * COMPOSITION HIERARCHY:
 *   HomeScreen
 *      ├── TransactionInput
 *      └── LedgerScreen
 *             └── ReportsScreen
 *
 * Notice: HomeScreen doesn't need to know about ReportsScreen.
 * LedgerScreen handles that internally. This is good encapsulation!
 */

public class HomeScreen {

    // ===== INSTANCE FIELDS =====
    private Scanner scanner;
    private TransactionInput transactionInput;  // COMPOSITION
    private LedgerScreen ledgerScreen;          // COMPOSITION


    // ===== CONSTRUCTOR =====
    /**
     * Creates a HomeScreen with the given Ledger and Scanner.
     *
     * COMPOSITION: Creates a TransactionInput and a LedgerScreen,
     * passing them the same Ledger and Scanner.
     *
     * This means EVERYTHING in the app uses the SAME Ledger and Scanner.
     *
     * @param ledger - The Ledger to use throughout the app
     * @param scanner - The Scanner to use for all user input
     */
    public HomeScreen(Ledger ledger, Scanner scanner) {
        this.scanner = scanner;
        // Create the dependent screens, sharing the same Ledger and Scanner
        this.transactionInput = new TransactionInput(ledger, scanner);
        this.ledgerScreen = new LedgerScreen(ledger, scanner);
    }


    // ===== PUBLIC METHOD =====

    /**
     * Shows the welcome message and starts the Home menu.
     * Loops until user selects 'X' (Exit).
     *
     * Called by: AccountingLedgerApp.main()
     */
    public void show() {
        displayWelcome();

        boolean running = true;

        while (running) {
            displayHomeMenu();
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "D":
                    transactionInput.addDeposit();
                    break;
                case "P":
                    transactionInput.addPayment();
                    break;
                case "L":
                    ledgerScreen.show();
                    break;
                case "X":
                    System.out.println("\nThank you for using MassWALLET Accounting. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.\n");
            }
        }
    }


    // ===== PRIVATE HELPER METHODS =====

    /**
     * Displays the welcome message at app startup.
     */
    private void displayWelcome() {
        System.out.println("\n================================");
        System.out.println(" MassWALLET ACCOUNTING");
        System.out.println(" Track your finances!");
        System.out.println("================================\n");
    }

    /**
     * Displays the Home menu options.
     */
    private void displayHomeMenu() {
        System.out.println("Home");
        System.out.println("------");
        System.out.println("D) Add Deposit");
        System.out.println("P) Make Payment");
        System.out.println("L) Ledger");
        System.out.println("X) Exit");
        System.out.print("\nEnter Option: ");
    }
}