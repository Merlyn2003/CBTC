import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {
    private String transactionId;
    private String transactionType;
    private double amount;
    private LocalDateTime timestamp;

    public Transaction(String transactionId, String transactionType, double amount) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

