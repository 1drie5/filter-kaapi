package domain;

import java.time.LocalDateTime;

public class Transaction {
    private String id;
    private Type type;
    private String accountNumber;
    private Double amount;
    private LocalDateTime timestamp;
    private String note;

    public Transaction(String accountNumber, Double amount, String id, String note, LocalDateTime timestamp, Type type) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.id = id;
        this.note = note;
        this.timestamp = timestamp;
        this.type = type;
    }
}
