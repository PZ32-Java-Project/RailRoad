package views;

import abstractions.IViewable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import models.Entrance;

import static shared.Constants.entranceSize;
import static shared.Global.pane;

public class EntranceView implements IViewable {
    private Entrance entrance;
    private Circle circle;

    public EntranceView(Entrance entrance) {
        this.entrance = entrance;
    }

    public void updateUI(){
        Color myGray = Color.rgb(240, 240, 240);
        circle = new Circle(entranceSize, Color.ORANGE);
        circle.setStroke(myGray);
        circle.setTranslateX(entrance.getX());
        circle.setTranslateY(entrance.getY());
        circle.setFill(myGray);
        pane.getChildren().add(circle);
    }
    public void removeUI(){
        pane.getChildren().remove(circle);
    }
}