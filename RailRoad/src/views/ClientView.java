package views;

import abstractions.IViewable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import models.Client;
import static shared.Global.pane;

public class ClientView implements IViewable {
    private Client client;
    private Circle circle;

    public ClientView(Client client) {
        this.client = client;
    }

    public Circle getCircle() {
        return circle;
    }

    public void updateUI(){
        removeUI();

        Color color;
        switch (client.getClientType()){
            case Veteran -> color=Color.OLIVE;
            case Disabled -> color=Color.YELLOW;
            case WithBaby -> color=Color.HOTPINK;
            default -> color=Color.BLUE;
        }
        circle = new Circle(5, color);
        circle.setStroke(color);
        circle.setTranslateX(client.getX());
        circle.setTranslateY(client.getY());
        pane.getChildren().add(circle);
    }

    public void removeUI(){
        if (circle != null) {
            pane.getChildren().remove(circle);
        }
    }
}