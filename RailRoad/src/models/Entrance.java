package models;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import abstractions.Position;

import static shared.Constants.entranceSize;

public class Entrance extends Position {
    private String name;
    private Circle entranceCircle;
    public Entrance(int x, int y, String name, Pane pane) {
        super(x, y);
        this.name = name;
        this.entranceCircle = new Circle(entranceSize, Color.ORANGE);
        entranceCircle.setStroke(Color.WHITE);
        entranceCircle.setTranslateX(this.getX());
        entranceCircle.setTranslateY(this.getY());
        entranceCircle.setFill(Color.WHITE);
        pane.getChildren().add(entranceCircle);
    }
}
