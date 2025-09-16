public class CurrentAccount extends Account {
    private double overdraft;
    public static final double DEFAULT_OVERDRAFT = 500;

    public CurrentAccount(String code, double overdraft) {
        super(code);
        this.overdraft = overdraft;
    }

    public CurrentAccount(String code) {
        this(code, DEFAULT_OVERDRAFT);
    }

    public static CurrentAccount create() {
        String code = generateAccountCode();
        return new CurrentAccount(code);
    }

    public static CurrentAccount create(double overdraft) {
        String code = generateAccountCode();
        return new CurrentAccount(code, overdraft);
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance != 0 && (amount > 0 && (balance + overdraft) >= amount)) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public double calculateInterest() {
        return 0.0;
    }

    @Override
    public void displayDetails() {
        System.out.printf("Current Account: %s - Balance: %.2f DH - Overdraft: %.2f DH\n",
                code, balance, overdraft);
    }

    public double getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }
}