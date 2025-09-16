import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.io.*;

public abstract class Account {
    protected String code;
    protected double balance;
    protected List<Operation> operationsList;

    // hashmap for faster search
    private static HashMap<String, Account> accounts = new HashMap<>();
    private static int counter = 1;
    private static final Pattern CODE_PATTERN = Pattern.compile("^CPT-\\d{5}$");

    public Account(String code) {
        this.code = code;
        this.balance = 0.0;
        this.operationsList = new ArrayList<>();
        accounts.put(code, this);
    }

    public static String generateAccountCode() {
        return String.format("CPT-%05d", counter++);
    }

    public String getCode() {
        return code;
    }

    public double getBalance() {
        return balance;
    }

    public abstract boolean withdraw(double amount);
    public abstract double calculateInterest();
    public abstract void displayDetails();
}