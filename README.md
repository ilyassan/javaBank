# YouBank - Bank Account Management System

## Project Description

YouBank is a console-based bank account management system developed in Java 8. The application automates the management of bank accounts through an intuitive menu-driven interface, allowing users to efficiently handle banking operations such as deposits, withdrawals, transfers, and account creation.

The system implements a layered architecture with separate presentation, business logic, and utility layers, providing a robust foundation for banking operations management.

## Technologies Used

- **Java 8**: Core programming language
- **Collections Framework**: ArrayList and HashMap for data storage and fast retrieval
- **IntelliJ IDEA**: Development environment

## Project Structure

```
YouBank/
├── src/
│   ├── Main.java                    # Application entry point and main menu
│   ├── Controllers/
│   │   └── MainController.java      # Presentation layer - handles user interactions
│   └── Models/
│       ├── Account.java             # Abstract base class for all accounts
│       ├── CurrentAccount.java      # Current account implementation with overdraft
│       ├── SavingsAccount.java      # Savings account implementation with interest
│       ├── Operation.java           # Abstract base class for banking operations
│       ├── Deposit.java             # Deposit operation implementation
│       └── Withdrawal.java          # Withdrawal operation implementation
├── out/                             # Compiled classes
├── uml/                             # UML diagrams
├── .gitignore                       # Git ignore file
├── YouBank.iml                      # IntelliJ project file
└── README.md                        # Project documentation
```

### Architecture Layers

1. **Presentation Layer (UI/Menu)**:
   - `Main.java`: Console interface and main menu
   - `MainController.java`: User interaction handlers

2. **Business Logic Layer**:
   - `Account.java`: Core account management logic
   - `CurrentAccount.java` & `SavingsAccount.java`: Account type implementations
   - `Operation.java`, `Deposit.java`, `Withdrawal.java`: Transaction management

3. **Utility Layer**:
   - Built-in Java utilities for data validation and formatting

## Core Features

### Account Management
- **Account Creation**: Support for Current and Savings accounts
- **Account Code Generation**: Automatic generation following "CPT-XXXXX" pattern
- **Account Search**: Find accounts by code with validation

### Banking Operations
- **Deposits**: Add money to accounts with source tracking
- **Withdrawals**: Remove money with destination tracking and balance validation
- **Transfers**: Move money between accounts with transaction safety
- **Transaction History**: View complete operation history for any account

### Account Types

#### Current Account (`CurrentAccount`)
- **Overdraft Support**: Default overdraft limit of 500 DH
- **Withdrawal Rules**: Can withdraw up to balance + overdraft limit
- **Interest**: No interest calculation (returns 0%)
- **Code Format**: CPT-XXXXX (e.g., CPT-00001)

#### Savings Account (`SavingsAccount`)
- **Interest Calculation**: Default interest rate of 2.5%
- **Withdrawal Rules**: Can only withdraw up to available balance
- **Interest Earning**: Calculates interest based on current balance
- **Code Format**: CPT-XXXXX (e.g., CPT-00002)

### Operation Types

#### Deposit Operations
- **Attributes**: Amount, source description, unique ID, timestamp
- **Sources**: "Cash deposit", "External transfer", "Salary", etc.
- **Validation**: Positive amounts only

#### Withdrawal Operations
- **Attributes**: Amount, destination description, unique ID, timestamp
- **Destinations**: "ATM withdrawal", "Check", "External transfer", etc.
- **Validation**: Sufficient funds check based on account type

## Prerequisites

### System Requirements
- **Java Development Kit (JDK) 8 or higher**
- **Operating System**: Windows, macOS, or Linux
- **IDE**: IntelliJ IDEA (recommended) or any Java-compatible IDE
- **Memory**: Minimum 512 MB RAM
- **Storage**: 10 MB free disk space

### Development Setup
1. Ensure JDK 8+ is installed and configured
2. Clone or download the project
3. Open the project in your preferred IDE
4. Compile and run `Main.java`

## Installation & Usage

### Running the Application

1. **Compile the project**:
   ```bash
   javac -d out src/**/*.java
   ```

2. **Run the application**:
   ```bash
   java -cp out Main
   ```

3. **Use the interactive menu**:
   - Follow the on-screen prompts
   - Enter numbers to select menu options
   - Input account codes in format: CPT-XXXXX

### Sample Usage Flow

1. **Create Account**: Choose account type (Current/Savings)
2. **Make Deposit**: Select account and enter amount
3. **Check Balance**: View account details and current balance
4. **Make Withdrawal**: Select account and enter withdrawal amount
5. **Transfer Money**: Select source and destination accounts
6. **View History**: See all transactions for an account

## Key Implementation Details

### Data Persistence
- **In-Memory Storage**: Data persists during application runtime
- **HashMap Storage**: Fast account lookups by account code
- **ArrayList Operations**: Maintains chronological transaction history

### Validation & Error Handling
- **Amount Validation**: Ensures positive values for all transactions
- **Account Code Validation**: Follows CPT-XXXXX pattern
- **Exception Handling**: Try-catch blocks for robust error management
- **Input Validation**: Scanner with InputMismatchException handling

### Security Features
- **Unique Transaction IDs**: UUID-based operation tracking
- **Balance Protection**: Overdraft limits and insufficient funds prevention
- **Data Integrity**: Validation at multiple layers

## Technical Specifications

### Code Standards
- **Java 8 Features**: Lambda expressions and enhanced date/time API
- **Object-Oriented Design**: Inheritance, polymorphism, and encapsulation
- **Design Patterns**: Template method pattern in abstract classes
- **Exception Handling**: Comprehensive try-catch error management

### Performance Features
- **HashMap Lookup**: O(1) average time complexity for account searches
- **ArrayList Operations**: Efficient transaction history management
- **Memory Management**: Optimized object creation and garbage collection