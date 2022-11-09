package railroad_simulation.models;

import javafx.concurrent.Task;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import railroad_simulation.abstractions.Position;
import railroad_simulation.shared.Constants;

import java.util.Random;

public class Client extends Position {
    private String name;
    private String surname;
    private int ticketsCount;
    private int ID;

    //додати поля Circle і Pane
    // в конструктор додати панель
    private Circle client;
    private Pane hall;
    public Client(Entrance entrance, int ID, String name, String surname, Pane hall)
    {
        super(entrance.getX(), entrance.getY());
        this.name = name;
        this.surname = surname;
        this.ID = ID;
        this.hall = hall;
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

    public void clientUI()
    {
        this.client = new Circle(1, Color.BLUE);
        client.setStroke(Color.BLUE);
        client.setTranslateX(this.getX());
        client.setTranslateY(this.getY());
        client.setFill(Color.BLUE);
        hall.getChildren().add(client);
    }

   /* @Override
    protected Long call() throws Exception {
        this.clientUI();
        return null;
    }*/

    //endregion
}