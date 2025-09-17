package Models;

public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String code, double interestRate) {
        super(code);
        this.interestRate = interestRate;
    }

    public SavingsAccount(String code) {
        this(code, 0.025);
    }

    public static SavingsAccount create() {
        String code = generateAccountCode();
        return new SavingsAccount(code);
    }

    public static SavingsAccount create(double interestRate) {
        String code = generateAccountCode();
        return new SavingsAccount(code, interestRate);
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public double calculateInterest() {
        return balance * interestRate;
    }

    @Override
    public void displayDetails() {
        System.out.printf("Savings Account: %s - Balance: %.2f DH - Interest Rate: %.2f%%\n",
                code, balance, interestRate * 100);
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}