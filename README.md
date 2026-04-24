# Accounting Ledger Application

A command-line Java application for tracking financial transactions. Record deposits and payments, view transaction history, and generate financial reports. All data persists to a CSV file between sessions.

---

## Project Overview

The Accounting Ledger Application is a CLI-based financial tracking tool designed for personal or small business use. Users can record deposits (money in) and payments (money out), view their transaction history with filtering options, and run pre-defined or custom financial reports.

This project applies core Java fundamentals including file I/O, data structures, user interaction patterns, and business logic to solve a real-world accounting problem.

---

## Features

### Core Application Features
- ✅ **Add Deposits** - Record money coming in with date, time, description, vendor, and amount
- ✅ **Make Payments** - Record money going out with same transaction details
- ✅ **View Ledger** - Display transactions with multiple filtering options
- ✅ **Generate Reports** - Pre-defined financial reports and vendor search
- ✅ **Persistent Storage** - All transactions automatically saved to `transactions.csv`

### Application Screens

#### Home Screen
The main menu provides access to all features:
```
Home
-------
D) Add Deposit
P) Make Payment  
L) Ledger
X) Exit
```

#### Ledger Screen
View and filter transactions (newest first):
```
Ledger
-------
A) All - Display all transactions
D) Deposits - Show only deposits (positive amounts)
P) Payments - Show only payments (negative amounts)
R) Reports - Access the reports screen
H) Home - Return to home screen
```

#### Reports Screen
Pre-defined and custom financial reports:
```
Reports
-------
1) Month to Date - Transactions from 1st of current month to today
2) Previous Month - All transactions from previous calendar month
3) Year to Date - Transactions from January 1st to today
4) Previous Year - All transactions from previous calendar year
5) Search by Vendor - Filter transactions by vendor name
0) Back - Return to ledger screen
```

---

## Data Format

Transactions are stored in a `transactions.csv` file with pipe-delimited format:

```
date|time|description|vendor|amount
2024-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50
2024-04-15|11:15:00|Invoice 1001 paid|Joe|1500.00
2024-04-16|14:30:00|Client consultation|Tech Corp|500.00
```

**Field Descriptions:**
- **date** - Transaction date in YYYY-MM-DD format
- **time** - Transaction time in HH:MM:SS format (24-hour)
- **description** - What the transaction was for
- **vendor** - Who the transaction was with (company/person name)
- **amount** - Dollar amount (positive for deposits, negative for payments)

---

## Project Structure

```
accounting-ledger/
├── src/
│   ├── AccountingLedgerApp.java    # Main entry point and menu logic
│   ├── Transaction.java             # Transaction data model/object
│   ├── Ledger.java                  # Business logic and file operations
│   └── (Optional) FileManager.java  # File I/O helper class
├── transactions.csv                 # Data file (auto-created)
├── README.md                        # This file
├── .gitignore                       # Git ignore file
└── (Optional) src/AccountingLedgerApp.class  # Compiled files
```

---

## Getting Started

### Prerequisites
- Java 8 or higher installed and in your PATH
- Text editor or IDE (VS Code, IntelliJ IDEA, Eclipse, NetBeans)
- Terminal or command prompt access
- Git (optional, but recommended for version control)

### Installation & Setup

**Step 1: Clone or download the repository**
```bash
git clone https://github.com/yourusername/accounting-ledger.git
cd accounting-ledger
```

**Step 2: Compile the Java files**
```bash
javac src/*.java
```

This creates `.class` files in the `src/` directory.

**Step 3: Run the application**
```bash
java -cp src AccountingLedgerApp
```

**Step 4: Use the application**
Follow the on-screen prompts to add deposits, view transactions, and generate reports.

### First Run
When you run the app for the first time, it will create a `transactions.csv` file in the working directory. The app will be empty until you add your first transaction.

---

## Usage Guide

### Adding a Deposit

```
Home
-------
D) Add Deposit
P) Make Payment
L) Ledger
X) Exit

Please enter the desired option >>> D

Please enter the deposit date (yyyy-MM-dd) >>> 2024-04-15
Please enter the deposit time (HH:mm:ss) >>> 10:13:25
Please enter the deposit description >>> Freelance project completed
Please enter the vendor (company/person) >>> Client ABC Inc
Please enter the deposit amount >>> 1500.00

Deposit added successfully! Returned to Home.
```

### Making a Payment

```
Please enter the desired option >>> P

Please enter the payment date (yyyy-MM-dd) >>> 2024-04-15
Please enter the payment time (HH:mm:ss) >>> 14:30:00
Please enter the payment description >>> Office supplies order
Please enter the vendor (company/person) >>> Amazon
Please enter the payment amount >>> 89.50

Payment added successfully! Returned to Home.
```

### Viewing the Ledger

```
Please enter the desired option >>> L

Ledger
-------
A) All
D) Deposits
P) Payments
R) Reports
H) Home

Please enter the desired option >>> A

Ledger - All Transactions
========================================
Date       | Time     | Vendor    | Description              | Amount
-----------|----------|-----------|--------------------------|----------
2024-04-15 | 14:30:00 | Amazon    | Office supplies order    | -89.50
2024-04-15 | 10:13:25 | Client ABC| Freelance project        | 1500.00
========================================
Total: 2 transactions
```

### Generating Reports

```
Please enter the desired option >>> R

Reports
-------
1) Month to Date
2) Previous Month
3) Year to Date
4) Previous Year
5) Search by Vendor
0) Back

Please enter the desired option >>> 1

========== Month to Date Report ==========
Total Income: $1,500.00
Total Expenses: $89.50
---
Net Income: $1,410.50
=========================================
```

### Searching by Vendor

```
Please enter the desired option >>> 5

Enter vendor name to search >>> Amazon

Ledger - Amazon Transactions
========================================
Date       | Time     | Description              | Amount
-----------|----------|--------------------------|----------
2024-04-15 | 14:30:00 | Office supplies order    | -89.50
========================================
Total: 1 transaction
```

---

## Implementation Details

### Transaction Class
Represents a single transaction with:
- `date` (String) - YYYY-MM-DD format
- `time` (String) - HH:MM:SS format
- `description` (String) - What the transaction was for
- `vendor` (String) - Who the transaction was with
- `amount` (double) - Dollar amount

**Key Methods:**
- `getters/setters` - Access transaction properties
- `toString()` - Format transaction for display
- `isDeposit()` - Check if amount is positive
- `isPayment()` - Check if amount is negative

### Ledger Class
Core business logic that manages all transactions:

**Key Methods:**
- `addTransaction(transaction)` - Add new transaction and save to file
- `getAllTransactions()` - Retrieve all transactions
- `getDeposits()` - Return only deposit transactions
- `getPayments()` - Return only payment transactions
- `getTotalIncome()` - Sum all deposits
- `getTotalExpenses()` - Sum all payments
- `getMonthToDateReport()` - Filter current month's transactions
- `getPreviousMonthReport()` - Filter previous month's transactions
- `getYearToDateReport()` - Filter current year's transactions
- `getPreviousYearReport()` - Filter previous year's transactions
- `searchByVendor(vendorName)` - Find transactions by vendor
- `loadTransactions()` - Read from CSV file
- `saveTransactions()` - Write to CSV file

### Main Application Class (AccountingLedgerApp)
Manages user interface and menu flow:

**Responsibilities:**
- Display menus and get user input
- Call appropriate Ledger methods
- Format and display results
- Handle invalid input gracefully
- Keep application running until user exits

---

## Code Examples

### Loading and Saving Transactions

```java
// Reading from CSV
Scanner scanner = new Scanner(new File("transactions.csv"));
while (scanner.hasNextLine()) {
    String line = scanner.nextLine();
    String[] parts = line.split("\\|");
    
    Transaction t = new Transaction(
        parts[0],  // date
        parts[1],  // time
        parts[2],  // description
        parts[3],  // vendor
        Double.parseDouble(parts[4])  // amount
    );
    transactions.add(t);
}

// Writing to CSV
PrintWriter writer = new PrintWriter(new FileWriter("transactions.csv"));
for (Transaction t : transactions) {
    writer.println(t.getDate() + "|" + t.getTime() + "|" + 
                   t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
}
writer.close();
```

### Filtering by Date Range

```java
public List<Transaction> getMonthToDateReport() {
    LocalDate today = LocalDate.now();
    LocalDate firstOfMonth = today.withDayOfMonth(1);
    
    return transactions.stream()
        .filter(t -> {
            LocalDate txDate = LocalDate.parse(t.getDate());
            return txDate.isAfter(firstOfMonth.minusDays(1)) && 
                   txDate.isBefore(today.plusDays(1));
        })
        .collect(Collectors.toList());
}
```

### Calculating Totals

```java
public double getTotalIncome() {
    double total = 0;
    for (Transaction t : transactions) {
        if (t.getAmount() > 0) {
            total += t.getAmount();
        }
    }
    return total;
}

public double getTotalExpenses() {
    double total = 0;
    for (Transaction t : transactions) {
        if (t.getAmount() < 0) {
            total += t.getAmount();
        }
    }
    return total;
}
```

---

## Testing Checklist

Verify each feature works correctly:

- [ ] **Deposits**
  - [ ] Add a deposit transaction
  - [ ] Verify it appears in "All" view
  - [ ] Verify it appears in "Deposits" view
  - [ ] Verify it's included in income total

- [ ] **Payments**
  - [ ] Add a payment transaction
  - [ ] Verify it appears in "All" view
  - [ ] Verify it appears in "Payments" view
  - [ ] Verify it's included in expenses total

- [ ] **Ledger Views**
  - [ ] All transactions display newest first
  - [ ] Deposits shows only positive amounts
  - [ ] Payments shows only negative amounts

- [ ] **Reports**
  - [ ] Month to Date shows correct date range
  - [ ] Previous Month shows correct month
  - [ ] Year to Date shows current year
  - [ ] Previous Year shows prior year
  - [ ] Search by Vendor finds matching transactions

- [ ] **Data Persistence**
  - [ ] Add transaction, exit app, restart—data is still there
  - [ ] CSV file is properly formatted
  - [ ] Amounts and descriptions preserve correctly

- [ ] **Input Validation**
  - [ ] Invalid date format is rejected
  - [ ] Non-numeric amounts are rejected
  - [ ] Empty inputs are handled gracefully

- [ ] **Edge Cases**
  - [ ] First run with no transactions
  - [ ] Empty ledger shows appropriate message
  - [ ] Very large amounts display correctly
  - [ ] Special characters in descriptions work

---

## Key Java Concepts Demonstrated

| Concept | Implementation Example |
|---------|----------------------|
| **Classes & Objects** | Transaction, Ledger classes represent real-world entities |
| **ArrayLists** | Store and manage dynamic list of transactions |
| **File I/O** | Read/write transactions.csv for data persistence |
| **String Manipulation** | Parse CSV with `split("\|")`, format output |
| **Date Handling** | Compare dates for report filtering |
| **Loops & Iteration** | Process all transactions for calculations/filtering |
| **Conditionals** | Filter by amount (deposit vs payment), date ranges |
| **Methods** | Organize logic into focused, reusable functions |
| **Scanner** | Get user input from console |
| **Exception Handling** | Handle file not found, invalid input, parse errors |

---

## Development Approach

### Recommended Implementation Order

**Phase 1: Core Data Model (Days 1-2)**
- [ ] Create Transaction class with all fields
- [ ] Create Ledger class with ArrayList for transactions
- [ ] Test creating/accessing transactions in memory

**Phase 2: File I/O (Days 3-4)**
- [ ] Implement CSV file reading
- [ ] Implement CSV file writing
- [ ] Test load and save functionality
- [ ] Verify data persists between runs

**Phase 3: Business Logic (Days 5-6)**
- [ ] Implement filtering methods (deposits, payments)
- [ ] Implement date-range filtering
- [ ] Implement vendor search
- [ ] Implement report calculations

**Phase 4: User Interface (Days 7-8)**
- [ ] Build main menu loop
- [ ] Implement add deposit input/validation
- [ ] Implement add payment input/validation
- [ ] Connect all screens together

**Phase 5: Testing & Polish (Days 9-10)**
- [ ] Comprehensive testing
- [ ] Input validation and error handling
- [ ] Output formatting
- [ ] Code cleanup and comments

---

## Bonus Features

### Custom Search (Advanced)
Implement a search with multiple filter criteria:

```
Custom Search
-------
Start Date (yyyy-MM-dd) [Enter to skip] >>> 2024-01-01
End Date (yyyy-MM-dd) [Enter to skip] >>> 2024-03-31
Description keyword [Enter to skip] >>> supplies
Vendor [Enter to skip] >>> 
Minimum amount [Enter to skip] >>> 
Maximum amount [Enter to skip] >>> 

Results:
Date       | Time     | Vendor       | Description              | Amount
-----------|----------|--------------|--------------------------|----------
2024-03-15 | 09:30:00 | Office Depot | Office supplies purchase | -120.00
-----------|----------|--------------|--------------------------|----------
Total: 1 transaction found
```

**Implementation Tips:**
- Only apply filters user provides (skip empty inputs)
- Support partial vendor/description matching
- Handle amount filters for transactions in range

---

## Best Practices

1. **Validation** - Always validate user input (date format, numeric values, empty strings)
2. **Error Messages** - Provide clear, helpful error messages when something goes wrong
3. **Data Integrity** - Always save to file immediately after adding transactions
4. **User Experience** - Return to Home after each action for consistency
5. **Code Organization** - Separate concerns (data model, business logic, UI)
6. **Comments** - Comment complex logic, especially filtering and calculations
7. **Testing** - Test edge cases (first run, empty ledger, large numbers)

---

## Troubleshooting

**Q: "File not found" error**  
A: Ensure you're running the app from the directory containing `transactions.csv`, or the code creates it automatically on first run.

**Q: Transactions not appearing after restart**  
A: Check that `saveTransactions()` is being called after each new transaction. Verify the file path is correct.

**Q: Date filters not working correctly**  
A: Ensure dates in CSV are in YYYY-MM-DD format. Use `LocalDate.parse()` for reliable date comparison.

**Q: Decimal precision issues with amounts**  
A: Use `DecimalFormat` for display, or `BigDecimal` for accurate financial calculations.

**Q: Menu not returning to correct screen**  
A: Verify each menu option has a return path. Track which screen user is on and display appropriate menu.

---

## GitHub Repository

This project should be in a public GitHub repository:

**Repository Structure:**
```
GitHub.com/yourusername/accounting-ledger/
├── README.md (this file)
├── .gitignore
├── src/
│   ├── AccountingLedgerApp.java
│   ├── Transaction.java
│   └── Ledger.java
└── transactions.csv (optional, can be added to .gitignore)
```

**Suggested .gitignore:**
```
# Compiled Java files
*.class

# IDE files
.idea/
.vscode/
*.iml

# OS files
.DS_Store
Thumbs.db

# Optional: exclude data file
# transactions.csv
```

**Commit History Example:**
```
1. Initial project setup - Create repository structure
2. Add Transaction class - Implement data model
3. Add Ledger class - Implement file I/O
4. Implement filtering - Add deposit/payment/vendor filters
5. Add reports functionality - Implement date-based reports
6. Build user interface - Main menu and screens
7. Add input validation - Verify user input
8. Final testing and documentation
```

---

## Presentation Guide (10 minutes)

### Structure:
1. **App Demo (5 minutes)**
   - Start at home screen
   - Add a deposit
   - Add a payment
   - Show all transactions (sorted newest first)
   - Filter deposits
   - Run month-to-date report
   - Search by vendor
   - Exit application

2. **Code Walkthrough (3 minutes)**
   - Show one interesting piece of code you're proud of
   - Examples: date filtering, CSV parsing, report calculation, input validation
   - Explain the logic briefly

3. **Q&A (2 minutes)**
   - Be ready to answer questions about design decisions
   - Discuss any challenges you faced

---

## Learning Resources

- **Java File I/O:** https://docs.oracle.com/javase/tutorial/i18n/resbundle/index.html
- **LocalDate:** https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html
- **ArrayList:** https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
- **Scanner:** https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html
- **Pluralsight Java Course:** Your learning materials

---

## Project Statistics

- **Estimated Time:** 10-15 hours
- **Number of Classes:** 3-4 (Transaction, Ledger, FileManager, AccountingLedgerApp)
- **Lines of Code:** 400-600 (depending on implementation)
- **Key Features:** 5 major features + bonus custom search

---

## Notes

- This is a **learning project** using vanilla Java with no external libraries
- Focus is on understanding core Java concepts applied to a real problem
- CSV file format is simple and human-readable (easy to debug and understand)
- As you advance, consider refactoring to use better patterns, database, or GUI

---

## License

Educational project - Pluralsight Capstone 1 Assignment

---

**Good luck with your capstone project! Build it step-by-step, test thoroughly, and you'll have a solid portfolio piece!** 🚀

