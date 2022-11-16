package views;

import abstractions.IViewable;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

import models.Exit;
import shared.Constants;
import shared.Global;

public class ExitView implements IViewable {
    private Exit exit;
    private Circle circle;

    public ExitView(Exit exit) {
        this.exit = exit;
    }

    public void updateUI() {
        var grayColor = Color.rgb(240, 240, 240);
        circle = new Circle(Constants.entranceSize, grayColor);
        circle.setStroke(grayColor);
        circle.setTranslateX(exit.getX());
        circle.setTranslateY(exit.getY());
        Global.pane.getChildren().add(circle);
    }

    public void removeUI() {
        Global.pane.getChildren().remove(circle);
    }
}
