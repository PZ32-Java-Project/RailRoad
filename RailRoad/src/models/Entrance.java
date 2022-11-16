package models;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import abstractions.Position;
import views.EntranceView;

import static shared.Constants.entranceSize;

public class Entrance extends Position {
    private String name;
    private EntranceView entranceView = new EntranceView();
    public Entrance(int x, int y, String name) {
        super(x, y);
        this.name = name;
        entranceView.updateEntranceView(this);
    }
    public void remove(){
        entranceView.removeEntranceFromUI();
        var map = Map.getInstance();
        map.removeAt(this);
    }
}