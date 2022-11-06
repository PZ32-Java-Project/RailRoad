package models;

import abstractions.Position;
import shared.Constants;

import java.util.ArrayList;
import java.util.Random;

public class Client extends Position {
    private String name;
    private String surname;
    private int ticketsCount;
    private int ID;

    public Client(Entrance entrance, int ID, String name, String surname)
    {
        super(entrance.getX(), entrance.getY());
        this.name = name;
        this.surname = surname;
        this.ID = ID;
        generateTickets();
    }

    private void generateTickets() {
        var random = new Random();
        ticketsCount = random.nextInt(Constants.CLIENT_MAX_TICKETS_COUNT);
        ++ticketsCount;
    }

    //region GetSet
    public int getID(){
        return ID;
    }
    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    @Override
    public String getData() {
        return this.toString();
    }

    public void setName(String name) {
        this.name = name;
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