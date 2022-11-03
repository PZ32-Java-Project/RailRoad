package models;

import java.time.LocalDateTime;

public class Ticket {
    private LocalDateTime date;
    private String description;

    public Ticket(String description) {
        date = LocalDateTime.now();
        this.description = description;
    }
}