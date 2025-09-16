public class Withdrawal extends Operation {
    private String destination;

    public Withdrawal(double amount, String destination) {
        super(amount);
        this.destination = destination;
    }

    public Withdrawal(double amount) {
        this(amount, "ATM withdrawal");
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String getDetails() {
        return String.format("%s - WITHDRAWAL: %.2f DH - Destination: %s - ID: %s",
                getFormattedDate(), amount, destination, id.substring(0, 8));
    }
}