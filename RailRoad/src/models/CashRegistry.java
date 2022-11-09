package models;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import abstractions.Position;

import java.awt.*;

public class CashRegistry extends Position {
    private Rectangle cash_reg;
    protected String name;
    protected Line line;
    protected int id;
    protected boolean onPause;

    public CashRegistry(int x, int y, String name, int id, Pane hall) {
        super(x, y);
        this.name = name;
        this.id = id;
        line = new Line(id);
        onPause = false;
        this.cash_reg = new Rectangle();
        cash_reg.setHeight(10);
        cash_reg.setWidth(10);
        cash_reg.setStroke(Color.GREEN);
        cash_reg.setTranslateX(x);
        cash_reg.setTranslateY(y);
        cash_reg.setFill(Color.GREEN);
        hall.getChildren().add(cash_reg);
    }

    public CashRegistry(int x, int y, String name, Line line) {
        super(x, y);
        this.name = name;
        this.line = line;
    }

    @Override
    public String getData() {
        return this.toString();
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public boolean isOnPause() {
        return onPause;
    }

    public void setOnPause(boolean onPause) {
        this.onPause = onPause;
        if (this.onPause) {
            cash_reg.setFill(Color.RED);
        }
        else {
            cash_reg.setFill(Color.GREEN);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
