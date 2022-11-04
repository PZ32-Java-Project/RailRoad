package models;

import abstractions.Position;
import shared.Constants;

import java.util.Random;

public class Client extends Position {
    private String name;
    private String surname;
    private int ticketsCount;

    public Client(int x, int y, String name, String surname)
    {
        super(x, y);
        this.name = name;
        this.surname = surname;
        generateTickets();
    }

    private void generateTickets() {
        var random = new Random();
        ticketsCount = random.nextInt(Constants.CLIENT_MAX_TICKETS_COUNT);
        ++ticketsCount;
    }

    @Override
    public String getData() {
        return this.toString();
    }
}