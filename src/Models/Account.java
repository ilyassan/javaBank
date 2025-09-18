package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public abstract class Account {
    protected String code;
    protected double balance;
    protected List<Operation> operationsList;

    private static final HashMap<String, Account> accounts = new HashMap<>();
    private static int counter = 1;

    public Account(String code) {
        this.code = code;
        this.balance = 0.0;
        this.operationsList = new ArrayList<>();
        accounts.put(code, this);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public String getCode() {
        return code;
    }

    public double getBalance() {
        return balance;
    }

    public List<Operation> getOperationsList() {
        return new ArrayList<>(operationsList);
    }

    public void addOperation(Operation operation) {
        operationsList.add(operation);
    }

    public static String generateAccountCode() {
        return String.format("CPT-%05d", counter++);
    }

    public static Account findAccountByCode(String code) {
        return accounts.get(code);
    }

    public static Account searchAccount(String code) {
        if (code == null || code.trim().isEmpty()) {
            System.out.println("Account code cannot be empty.");
            return null;
        }

        String searchCode = code.trim().toUpperCase();
        Account account = findAccountByCode(searchCode);

        if (account == null) {
            System.out.println("Account with code '" + searchCode + "' not found.");
        }

        return account;
    }

    public static List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

    public static boolean validateAmount(double amount) {
        return amount > 0;
    }

    public static boolean processDeposit(String accountCode, double amount, String source) {
        try {
            if (!validateAmount(amount)) {
                throw new IllegalArgumentException("Amount must be positive");
            }

            Account account = findAccountByCode(accountCode);
            if (account == null) {
                throw new IllegalArgumentException("Account not found");
            }

            Deposit deposit = new Deposit(amount, source);
            account.deposit(amount);
            account.addOperation(deposit);
            return true;

        } catch (Exception e) {
            System.err.println("Error during deposit: " + e.getMessage());
            return false;
        }
    }

    public static boolean processWithdrawal(String accountCode, double amount, String destination) {
        try {
            if (!validateAmount(amount)) {
                throw new IllegalArgumentException("Amount must be positive");
            }

            Account account = findAccountByCode(accountCode);
            if (account == null) {
                throw new IllegalArgumentException("Account not found");
            }

            if (account.withdraw(amount)) {
                Withdrawal withdrawal = new Withdrawal(amount, destination);
                account.addOperation(withdrawal);
                return true;
            } else {
                throw new IllegalArgumentException("Insufficient funds");
            }

        } catch (Exception e) {
            System.err.println("Error during withdrawal: " + e.getMessage());
            return false;
        }
    }

    public static boolean processTransfer(String sourceAccountCode, String destinationAccountCode, double amount) {
        try {
            if (!validateAmount(amount)) {
                throw new IllegalArgumentException("Amount must be positive");
            }

            Account sourceAccount = findAccountByCode(sourceAccountCode);
            Account destinationAccount = findAccountByCode(destinationAccountCode);

            if (sourceAccount == null || destinationAccount == null) {
                throw new IllegalArgumentException("Account(s) not found");
            }

            if (sourceAccountCode.equals(destinationAccountCode)) {
                throw new IllegalArgumentException("Cannot transfer to the same account");
            }

            if (processWithdrawal(sourceAccountCode, amount, "Transfer to " + destinationAccountCode)) {
                if (processDeposit(destinationAccountCode, amount, "Transfer from " + sourceAccountCode)) {
                    return true;
                } else {
                    processDeposit(sourceAccountCode, amount, "Transfer cancellation");
                }
            }
            return false;

        } catch (Exception e) {
            System.err.println("Error during transfer: " + e.getMessage());
            return false;
        }
    }

    public abstract boolean withdraw(double amount);
    public abstract double calculateInterest();
    public abstract void displayDetails();
}