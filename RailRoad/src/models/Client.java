package models;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import abstractions.Position;
import shared.Constants;
import java.util.Random;

import static shared.Constants.clientSize;

public class Client extends Position {
    private String name;
    private String surname;
    private int ticketsCount;
    private int id;
    private Circle clientCircle;
    private Pane pane;

    private ClientTypes priority;

    public Client(Entrance entrance, int id, String name, String surname, Pane pane, ClientTypes priority) {
        super(entrance.getX(), entrance.getY());
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.pane = pane;
        this.priority = priority;
        generateTickets();
    }
    private void generateTickets() {
        var random = new Random();
        ticketsCount = random.nextInt(Constants.CLIENT_MAX_TICKETS_COUNT)+1;
    }
    //region GetSet
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
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

    public void updateUI()
    {
        if (clientCircle != null) {
            pane.getChildren().remove(clientCircle);
        }

        clientCircle = new Circle(5, Color.BLUE);
        clientCircle.setStroke(Color.BLUE);
        clientCircle.setTranslateX(this.getX());
        clientCircle.setTranslateY(this.getY());
        clientCircle.setFill(Color.BLUE);

        pane.getChildren().add(clientCircle);
    }

    public void remove() {
        if (clientCircle != null) {
            pane.getChildren().remove(clientCircle);
        }

        var map = Map.getInstance();
        map.removeAt(this);
    }

    /*
    @Override
    protected Long call() throws Exception {
        this.clientUI();
        return null;
    }
    */

    //endregion
    public enum ClientTypes{
        Disabled(0),
        WithBaby(1),
        Veteran(2),
        Ordinary(3);
        public final int priority;
        private ClientTypes(int priority) {
            this.priority = priority;
        }
    }
}

