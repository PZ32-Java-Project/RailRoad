package railroad_simulation.models;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import railroad_simulation.abstractions.Position;

import java.awt.*;

public class CashRegistry extends Position {
    private String name;
    private Line line;
    private int ID;
    private boolean onPause;

    private Rectangle cash_reg;
    public CashRegistry(int x, int y, String name, int ID, Pane hall) {
        super(x, y);
        this.name = name;
        this.ID = ID;
        line = new Line();
        onPause=false;
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
        if(this.onPause)
        {
            cash_reg.setFill(Color.RED);
        }
        else
        {
            cash_reg.setFill(Color.GREEN);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
