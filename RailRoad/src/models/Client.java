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

    public Client(Entrance entrance, int id, String name, String surname, Pane pane) {
        super(entrance.getX(), entrance.getY());
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.pane = pane;
        generateTickets();
    }
    private void generateTickets() {
        var random = new Random();
        ticketsCount = random.nextInt(Constants.CLIENT_MAX_TICKETS_COUNT);
        ++ticketsCount;
    }
    public void MoveTo(Position position){

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
        this.clientCircle = new Circle(clientSize, Color.BLUE);
        clientCircle.setStroke(Color.BLUE);
        clientCircle.setTranslateX(this.getX());
        clientCircle.setTranslateY(this.getY());
        clientCircle.setFill(Color.BLUE);
        pane.getChildren().add(clientCircle);
    }

    /*
    @Override
    protected Long call() throws Exception {
        this.clientUI();
        return null;
    }
    */

    //endregion
}