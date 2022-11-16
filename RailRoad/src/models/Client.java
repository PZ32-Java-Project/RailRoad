package models;

import abstractions.Position;
import shared.Constants;
import views.ClientView;

import java.util.Random;

public class Client extends Position implements  Comparable<Client>  {
    private String name;
    private String surname;
    private int ticketsCount;
    private ClientView view;
    private int id;
    private ClientTypes clientType;

    public Client(Entrance entrance, int id, String name, String surname, ClientTypes priority) {
        super(entrance.getX(), entrance.getY());
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.clientType = priority;
        generateTickets();
        initializeView();
    }

    public void initializeView() {
        this.view = new ClientView(this);
    }

    public ClientView getView() {
        return view;
    }

    public void setView(ClientView view) {
        this.view = view;
    }
    private void generateTickets() {
        var random = new Random();
        ticketsCount = random.nextInt(Constants.CLIENT_MAX_TICKETS_COUNT)+1;
    }

    public void updateUI(){
        view.updateUI();
    }

    public void remove() {
        view.removeUI();
        var map = Map.getInstance();
        map.removeAt(this);
    }
    @Override
    public int compareTo(Client o) {
        return clientType.compareTo(o.clientType);
    }

    //region GetSet
    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }
    public ClientTypes getClientType(){
        return clientType;
    }

    public int getTicketsCount() {
        return ticketsCount;
    }
    @Override
    public String toString(){
        return "Name: " + name
                + "\nTickets count: " + ticketsCount
                + "\nClient type: " + clientType;
    }
    //endregion
}