package railroad_simulation.models;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import railroad_simulation.abstractions.Position;

public class Entrance extends Position {
    private String name;
    Circle entr;

    public Entrance(int x, int y, String name, Pane hall) {
        super(x, y);
        this.name = name;
        this.entr = new Circle(10, Color.ORANGE);
        entr.setStroke(Color.WHITE);
        entr.setTranslateX(this.getX());
        entr.setTranslateY(this.getY());
        entr.setFill(Color.WHITE);
        hall.getChildren().add(entr);
    }

    @Override
    public String getData() {
        return null;
    }
}
