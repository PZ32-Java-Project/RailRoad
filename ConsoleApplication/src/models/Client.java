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

    //region GetSet
    @Override
    public String getData() {
        return this.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getTicketsCount() {
        return ticketsCount;
    }

    public void setTicketsCount(int ticketsCount) {
        this.ticketsCount = ticketsCount;
    }
    //endregion
}