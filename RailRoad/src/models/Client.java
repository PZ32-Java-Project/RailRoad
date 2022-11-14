package models;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import abstractions.Position;
import shared.Constants;
import java.util.Random;

public class Client extends Position implements  Comparable<Client>  {
    private String name;
    private String surname;
    private int ticketsCount;
    private int id;
    private Circle clientCircle;
    private Pane pane;
    private ClientTypes clientType;

    public Client(Entrance entrance, int id, String name, String surname, ClientTypes priority, Pane pane) {
        super(entrance.getX(), entrance.getY());
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.clientType = priority;
        this.pane = pane;
        generateTickets();
    }
    private void generateTickets() {
        var random = new Random();
        ticketsCount = random.nextInt(Constants.CLIENT_MAX_TICKETS_COUNT)+1;
    }

    public void updateUI(){
        if (clientCircle != null) {
            pane.getChildren().remove(clientCircle);
        }
        Color color;
        switch (clientType){
            case Veteran -> color=Color.OLIVE;
            case Disabled -> color=Color.YELLOW;
            case WithBaby -> color=Color.HOTPINK;
            default -> color=Color.BLUE;
        }
        clientCircle = new Circle(5, color);
        clientCircle.setStroke(color);
        clientCircle.setTranslateX(this.getX());
        clientCircle.setTranslateY(this.getY());
        clientCircle.setFill(color);
        pane.getChildren().add(clientCircle);
    }

    public void remove() {
        if (clientCircle != null) {
            pane.getChildren().remove(clientCircle);
        }

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
    //endregion
}

