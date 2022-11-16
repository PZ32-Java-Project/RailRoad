package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import models.Entrance;

import static shared.Constants.entranceSize;
import static shared.Global.pane;

public class EntranceView {
    private Circle entranceCircle;

    public Circle getEntranceCircle() {
        return entranceCircle;
    }

    public void setEntranceCircle(Circle entranceCircle) {
        this.entranceCircle = entranceCircle;
    }

    public void updateEntranceView(Entrance entrance){
        Color myGray = Color.rgb(240, 240, 240);
        entranceCircle = new Circle(entranceSize, Color.ORANGE);
        entranceCircle.setStroke(myGray);
        entranceCircle.setTranslateX(entrance.getX());
        entranceCircle.setTranslateY(entrance.getY());
        entranceCircle.setFill(myGray);
        pane.getChildren().add(entranceCircle);
    }
    public void removeEntranceFromUI(){
        pane.getChildren().remove(entranceCircle);
    }
}