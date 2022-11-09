package models;

import abstractions.Position;

public class CashRegistry extends Position {
    protected String name;
    protected Line line;
    protected int ID;
    protected boolean onPause;

    public CashRegistry(int x, int y, String name, int ID) {
        super(x, y);
        this.name = name;
        this.ID = ID;
        line = new Line(ID);
        onPause=false;
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
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
