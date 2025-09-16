public class Deposit extends Operation {
    private String source;

    public Deposit(double amount, String source) {
        super(amount);
        this.source = source;
    }

    public Deposit(double amount) {
        this(amount, "Cash deposit");
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String getDetails() {
        return String.format("%s - DEPOSIT: %.2f DH - Source: %s - ID: %s",
                getFormattedDate(), amount, source, id.substring(0, 8));
    }
}