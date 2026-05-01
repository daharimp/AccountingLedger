package com.pluralsight.ui;
import com.pluralsight.model.Ledger;

import java.util.Scanner;

/**
 * AccountingLedgerApp.java
 *
 * MAIN ENTRY POINT - The simplest class in the entire app.
 *
 * RESPONSIBILITIES:
 * 1. Create the shared Ledger (loads transactions from file)
 * 2. Create the shared Scanner (for user input)
 * 3. Create the HomeScreen (which owns all other screens)
 * 4. Launch the app via homeScreen.show()
 * 5. Close the Scanner when done
 *
 * THAT'S IT! All complexity is delegated to other classes.
 *
 * ARCHITECTURE:
 *   AccountingLedgerApp (main)
 *      └── HomeScreen
 *           ├── TransactionInput
 *           └── LedgerScreen
 *                └── ReportsScreen
 *
 * DESIGN PRINCIPLES DEMONSTRATED:
 * - Single Responsibility: Main only handles startup/shutdown
 * - Dependency Injection: Pass Ledger and Scanner down through constructors
 * - Composition: Each class owns the smaller classes it needs
 * - Encapsulation: Each screen handles its own logic internally
 *
 * BEFORE REFACTORING: This file was ~400 lines doing everything.
 * AFTER REFACTORING: This file is ~30 lines, doing only what main() should do.
 */

public class AccountingLedgerApp {

    public static void main(String[] args) {
        // Create the shared Ledger (automatically loads transactions from CSV)
        Ledger ledger = new Ledger();

        // Create the shared Scanner for all user input
        Scanner scanner = new Scanner(System.in);

        // Create the HomeScreen - it will create everything else internally
        HomeScreen homeScreen = new HomeScreen(ledger, scanner);

        // Launch the app - this runs until user exits
        homeScreen.show();

        // Clean up the Scanner when done
        scanner.close();
    }
}