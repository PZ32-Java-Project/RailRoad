package views;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import models.Client;
import static shared.Global.pane;

public class ClientView {
    private Circle clientCircle;

    public Circle getClientCircle() {
        return clientCircle;
    }

    public void setClientCircle(Circle clientCircle) {
        this.clientCircle = clientCircle;
    }
    public void updateClientUI(Client client){
        if (clientCircle != null) {
            pane.getChildren().remove(clientCircle);
        }
        Color color;
        switch (client.getClientType()){
            case Veteran -> color=Color.OLIVE;
            case Disabled -> color=Color.YELLOW;
            case WithBaby -> color=Color.HOTPINK;
            default -> color=Color.BLUE;
        }
        clientCircle = new Circle(5, color);
        clientCircle.setStroke(color);
        clientCircle.setTranslateX(client.getX());
        clientCircle.setTranslateY(client.getY());
        clientCircle.setFill(color);
        pane.getChildren().add(clientCircle);
    }
    public void removeClientUI(){
        if (clientCircle != null) {
            pane.getChildren().remove(clientCircle);
        }
    }
}