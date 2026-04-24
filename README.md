# AccountingApp
A basic accounting application coded in vanilla Java for small business financial tracking.

## Project Overview
AccountingApp is a command-line accounting tool designed to help small business owners (freelancers, shops, startups) track their **income and expenses**, understand **categorized spending**, and calculate **profit/loss**. This is a learning project focused on core Java concepts applied to real-world accounting logic.

**Target User:** Small business owner wanting to track finances
**Core Goal:** Answer "How much did I make? How much did I spend (by category)? What's my profit?"

---

## Features

### MVP (Minimum Viable Product) - Phase 1
- [x] Create and manage **accounts** (Income, Expenses, Bank)
- [x] Record **transactions** (income/expense entries with date, amount, category, description)
- [x] View **transaction history** (list all transactions)
- [x] Calculate **total income** and **total expenses**
- [x] Calculate **net profit/loss** (income - expenses)
- [x] View **expenses by category** (breakdown of where money went)
- [x] **Persist data** to file (save/load between sessions)

### Phase 2 (Extended Features)
- [ ] Edit and delete transactions
- [ ] Filter transactions by date range
- [ ] Export reports (CSV, formatted text)
- [ ] Budget tracking (set spending limits per category)
- [ ] Monthly/yearly summaries

---

## Simple Accounting Concepts Explained

### Accounts
An **account** is a named bucket that holds money or tracks activity. Common types:
- **Bank Account:** Where your actual money sits
- **Income Account:** Tracks money coming in
- **Expense Account:** Tracks money going out

### Transactions
A **transaction** is a single money movement. It has:
- **Date:** When it happened
- **Amount:** How much (always positive, direction determined by type)
- **Type:** INCOME or EXPENSE
- **Category:** What it's for (e.g., "Consulting", "Office Supplies", "Utilities")
- **Description:** Details (e.g., "Project payment from Client ABC")
- **Account:** Which account it affects (usually Bank)

### Profit/Loss Calculation
```
Total Income - Total Expenses = Net Profit (or Loss if negative)
```

### Categorized Spending
Breaking down ALL expenses by category:
```
Office Supplies:      $150
Utilities:            $200
Software:             $100
Marketing:            $50
---
Total Expenses:       $500
```

---

## Data Model

### Core Data Structures

#### 1. Transaction
```
- id (unique identifier)
- date (string: YYYY-MM-DD)
- amount (double, always positive)
- type (INCOME or EXPENSE)
- category (string)
- description (string)
- account (string, which account it's tied to)
```

#### 2. Account
```
- name (string)
- type (BANK, INCOME, EXPENSE)
- balance (double)
- transactions (list of transactions)
```

#### 3. Ledger (Main App Container)
```
- accounts (list of all accounts)
- transactions (list of all transactions)
- Methods: addTransaction(), getBalance(), getTotalIncome(), getTotalExpenses(), getExpensesByCategory()
```

---

## Class Structure

### Recommended Class Organization

```
AccountingApp/
├── src/
│   ├── Transaction.java          # Represents a single transaction
│   ├── Account.java              # Represents an account
│   ├── Ledger.java               # Main business logic container
│   ├── FileManager.java          # Handles save/load to file
│   └── AccountingApp.java        # Main entry point, user interface/menu
└── data/
    └── transactions.txt          # File storage for persistence
```

### Class Breakdown

**Transaction.java**
- Fields: id, date, amount, type (INCOME/EXPENSE), category, description, account
- Methods: getters, setters, toString()

**Account.java**
- Fields: name, type, balance, list of transactions
- Methods: addTransaction(), getBalance(), toString()

**Ledger.java**
- Fields: list of accounts, list of all transactions
- Methods:
  - `addTransaction(transaction)` - Add a new transaction
  - `getTotalIncome()` - Sum all INCOME transactions
  - `getTotalExpenses()` - Sum all EXPENSE transactions
  - `getProfit()` - Income - Expenses
  - `getExpensesByCategory()` - Map category → total spent
  - `getAllTransactions()` - Return all transactions
  - `getTransactionsByType(type)` - Filter by INCOME or EXPENSE

**FileManager.java**
- Methods:
  - `saveTransactions(ledger, filename)` - Write to file
  - `loadTransactions(filename)` - Read from file and populate ledger

**AccountingApp.java (Main)**
- Manages user menu and interaction
- Methods:
  - `main(String[] args)` - Entry point
  - `displayMenu()` - Show options to user
  - `handleAddTransaction()` - Get user input, create transaction
  - `handleViewReport()` - Display financial summary
  - `handleViewByCategory()` - Display expense breakdown

---

## Implementation Roadmap

### Step 1: Core Classes (Week 1)
- [ ] Build **Transaction** class with all fields and methods
- [ ] Build **Account** class (optional for MVP, can simplify to just track transactions)
- [ ] Build **Ledger** class with basic logic
- **Learning Focus:** Classes, objects, ArrayLists, getters/setters

### Step 2: Business Logic (Week 2)
- [ ] Implement `getTotalIncome()` and `getTotalExpenses()` (loops, conditionals)
- [ ] Implement `getProfit()`
- [ ] Implement `getExpensesByCategory()` (using HashMap or nested loops)
- **Learning Focus:** Loops, conditionals, data structure manipulation

### Step 3: File Persistence (Week 3)
- [ ] Implement **FileManager** class
- [ ] Save transactions to text file (simple CSV format)
- [ ] Load transactions from file on startup
- **Learning Focus:** File I/O, String parsing, ArrayList population

### Step 4: User Interface (Week 4)
- [ ] Build menu-driven interface in **AccountingApp.main()**
- [ ] Allow user to add transactions via console input
- [ ] Display financial reports
- **Learning Focus:** Scanner, String parsing, formatted output

### Step 5: Polish & Extend (Week 5+)
- [ ] Error handling (invalid input, file not found)
- [ ] Delete/edit transaction functionality
- [ ] Date filtering
- [ ] Better reporting (sorted, formatted)

---

## File Format (Data Persistence)

Simple CSV-like format for storing transactions:

```
id|date|amount|type|category|description|account
1|2024-01-15|500.00|INCOME|Consulting|Project ABC|Bank
2|2024-01-16|150.00|EXPENSE|Office Supplies|Pens and paper|Bank
3|2024-01-17|200.00|EXPENSE|Utilities|Electric bill|Bank
```

This makes it easy to parse with `String.split("|")` and rebuild Transaction objects.

---

## Java Concepts You'll Practice

| Concept | Where You'll Use It |
|---------|-------------------|
| Classes & Objects | Transaction, Account, Ledger |
| ArrayLists | Storing multiple transactions/accounts |
| Loops (for, enhanced for) | Iterating to calculate totals, filter by category |
| Conditionals (if/else) | Checking type (INCOME/EXPENSE), filtering |
| Methods | Every calculation (getTotalIncome, getProfit, etc.) |
| HashMaps | Grouping expenses by category |
| File I/O | Reading/writing transactions to disk |
| Scanner | Getting user input from console |
| String parsing | Splitting file data, converting to numbers |

---

## Sample Output

Once built, running the app might look like:

```
=== ACCOUNTING APP ===
1. Add Transaction
2. View All Transactions
3. View Financial Summary
4. View Expenses by Category
5. Exit

Choose an option: 3

=== FINANCIAL SUMMARY ===
Total Income:     $2,500.00
Total Expenses:   $650.00
---
NET PROFIT:       $1,850.00

Choose an option: 4

=== EXPENSES BY CATEGORY ===
Office Supplies:  $150.00
Utilities:        $200.00
Marketing:        $100.00
Software:         $200.00
---
Total:            $650.00
```

---

## Getting Started

### Prerequisites
- Java 8 or higher
- Text editor or IDE (VS Code, IntelliJ, Eclipse)
- Terminal/Command line

### Compilation & Running
```bash
javac src/*.java
java -cp src AccountingApp
```

### First Transaction
When prompted:
1. Enter date (YYYY-MM-DD): 2024-01-15
2. Enter amount: 500.00
3. Enter type (INCOME/EXPENSE): INCOME
4. Enter category: Consulting
5. Enter description: Project payment

---

## Learning Tips

1. **Start small:** Build Transaction first, test it, then add to Account, then Ledger
2. **Use `System.out.println()` to debug:** Print values as you go to understand what's happening
3. **Test each method:** Before moving to the next feature, verify the current one works
4. **Don't jump to Phase 2:** Master Phase 1 first—the foundation is critical
5. **Think about edge cases:** What if someone enters negative amount? What if no transactions exist yet?

---

## Future Enhancements

- [ ] Multiple users/businesses
- [ ] Tax calculations
- [ ] Budget alerts
- [ ] Investment tracking
- [ ] Graphical UI (Swing/JavaFX)
- [ ] Database instead of file storage
- [ ] API integration with bank accounts

---

## Project Structure Notes

This is a **learning project**, not production software. As you grow:
- You might refactor to use design patterns (Factory, Observer, etc.)
- You might switch to a database (SQL)
- You might add a UI framework (JavaFX, Swing)
- You might refactor for extensibility and maintainability

The vanilla Java approach here teaches you **fundamentals first**—solid foundation before abstractions.

---

## References & Inspiration

Common small business accounting apps use similar concepts:
- Wave (free accounting)
- Freshbooks (invoicing + expenses)
- QuickBooks (comprehensive)

They all track: **transactions → categories → reports**.

---

## License
Learning project—free to use and modify.

---

**Happy coding! Focus on understanding WHY you're writing each class, not just HOW.**
