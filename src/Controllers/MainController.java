package controllers;

import models.Account;
import models.CurrentAccount;
import models.Operation;
import models.SavingsAccount;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainController {
    private static final Scanner scanner = new Scanner(System.in);

    public static void createAccount() {
        System.out.println("\n=== CREATE ACCOUNT ===");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");
        System.out.println("3. Back to Main Menu");
        System.out.print("Choose account type: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                SavingsAccount savingsAccount = SavingsAccount.create();
                System.out.printf("Savings account created successfully!\nCode: %s\nInitial balance: %.2f DH\n",
                        savingsAccount.getCode(), savingsAccount.getBalance());
                pauseBeforeMenu();
                break;
            case 2:
                CurrentAccount currentAccount = CurrentAccount.create();
                System.out.printf("Current account created successfully!\nCode: %s\nInitial balance: %.2f DH\nOverdraft limit: %.2f DH\n",
                        currentAccount.getCode(), currentAccount.getBalance(), currentAccount.getOverdraft());
                pauseBeforeMenu();
                break;
            case 3: return;
            default: System.out.println("Invalid option.");
        }
    }

    public static void checkBalance() {
        System.out.println("\n=== CHECK BALANCE ===");
        System.out.println("1. Search by Account Code");
        System.out.println("2. List All Accounts to Select");
        System.out.println("3. Back to Main Menu");
        System.out.print("Choose an option: ");

        int choice = getIntInput();
        Account account = null;

        switch (choice) {
            case 1:
                System.out.print("Enter account code (CPT-XXXXX): ");
                String accountCode = scanner.nextLine();
                account = Account.searchAccount(accountCode);
                break;
            case 2:
                account = selectAccount();
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid option.");
                pauseBeforeMenu();
                return;
        }

        if (account != null) {
            account.displayDetails();
        }
        pauseBeforeMenu();
    }

    public static void makeDeposit() {
        Account account = selectAccount();
        if (account != null) {
            System.out.print("Enter deposit amount: ");
            double amount = getDoubleInput();

            if (amount > 0) {
                System.out.print("Source of deposit (optional): ");
                String source = scanner.nextLine();
                if (source.trim().isEmpty()) {
                    source = "Cash deposit";
                }

                if (Account.processDeposit(account.getCode(), amount, source)) {
                    System.out.printf("Deposit successful! New balance: %.2f DH\n", account.getBalance());
                }
            } else {
                System.out.println("Invalid amount. Please enter a positive number.");
            }
        }
        pauseBeforeMenu();
    }

    public static void makeWithdrawal() {
        Account account = selectAccount();
        if (account != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = getDoubleInput();

            if (amount > 0) {
                System.out.print("Destination of withdrawal (optional): ");
                String destination = scanner.nextLine();
                if (destination.trim().isEmpty()) {
                    destination = "ATM withdrawal";
                }

                if (Account.processWithdrawal(account.getCode(), amount, destination)) {
                    System.out.printf("Withdrawal successful! New balance: %.2f DH\n", account.getBalance());
                }
            } else {
                System.out.println("Invalid amount. Please enter a positive number.");
            }
        }
        pauseBeforeMenu();
    }

    public static void transferMoney() {
        System.out.println("\n=== SOURCE ACCOUNT ===");
        Account sourceAccount = selectAccount();
        if (sourceAccount == null) return;

        System.out.println("\n=== DESTINATION ACCOUNT ===");
        Account destinationAccount = selectAccount();
        if (destinationAccount == null) return;

        System.out.print("Enter transfer amount: ");
        double amount = getDoubleInput();

        if (amount > 0) {
            if (Account.processTransfer(sourceAccount.getCode(), destinationAccount.getCode(), amount)) {
                System.out.println("Transfer successful!");
                System.out.printf("Source account (%s): %.2f DH\n", sourceAccount.getCode(), sourceAccount.getBalance());
                System.out.printf("Destination account (%s): %.2f DH\n", destinationAccount.getCode(), destinationAccount.getBalance());
            }
        } else {
            System.out.println("Invalid amount. Please enter a positive number.");
        }
        pauseBeforeMenu();
    }

    public static void viewTransactionHistory() {
        Account account = selectAccount();
        if (account != null) {
            List<Operation> operations = account.getOperationsList();

            if (operations.isEmpty()) {
                System.out.println("No transactions found for this account.");
                return;
            }

            System.out.println("\n=== TRANSACTION HISTORY ===");
            System.out.printf("Account: %s - Current balance: %.2f DH\n", account.getCode(), account.getBalance());
            System.out.println(String.join("", Collections.nCopies(60, "-")));

            for (Operation operation : operations) {
                System.out.println(operation.getDetails());
            }
        };
        pauseBeforeMenu();
    }

    public static Account selectAccount() {
        List<Account> allAccounts = Account.getAllAccounts();

        if (allAccounts.isEmpty()) {
            System.out.println("No accounts found. Please create an account first.");
            return null;
        }

        System.out.println("\n=== SELECT ACCOUNT ===");
        for (int i = 0; i < allAccounts.size(); i++) {
            Account account = allAccounts.get(i);
            String type = (account instanceof SavingsAccount) ? "Savings" : "Current";
            System.out.printf("%d. %s (%s) - Balance: %.2f DH\n",
                    i + 1, account.getCode(), type, account.getBalance());
        }

        System.out.print("Select an account: ");
        int choice = getIntInput();

        if (choice >= 1 && choice <= allAccounts.size()) {
            return allAccounts.get(choice - 1);
        } else {
            System.out.println("Invalid selection.");
            return null;
        }
    }

    private static int getIntInput() {
        try {
            int input = scanner.nextInt();
            scanner.nextLine();
            return input;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid input. Please enter a whole number.");
            return -1;
        }
    }

    private static double getDoubleInput() {
        try {
            double input = scanner.nextDouble();
            scanner.nextLine();
            return input;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid input. Please enter a number.");
            return -1;
        }
    }

    private static void pauseBeforeMenu() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}
