package com.pluralsight;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

}
