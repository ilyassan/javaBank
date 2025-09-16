import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public abstract class Operation {
    protected String id;
    protected LocalDateTime date;
    protected double amount;

    public Operation(double amount) {
        this.id = UUID.randomUUID().toString(); // a 128-bit number
        this.date = LocalDateTime.now();
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getFormattedDate() {
        return date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));
    }

    public abstract String getDetails();
}