package models;

import abstractions.Position;
import shared.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Client extends Position {
    private String name;
    private String surname;
    private List<Ticket> tickets;

    public Client(int x, int y, String name, String surname)
    {
        super(x, y);
        this.name = name;
        this.surname = surname;
        tickets = new ArrayList<>();
        generateTickets();
    }

    private void generateTickets() {
        var random = new Random();
        int ticketsCount = random.nextInt(Constants.CLIENT_MAX_TICKETS_COUNT);
        ++ticketsCount;

        // TODO: add description generation?
        for (int i = 0; i < ticketsCount; ++i) {
            tickets.add(new Ticket("Destination: Lviv"));
        }

    }
}