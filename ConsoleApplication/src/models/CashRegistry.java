package models;

import abstractions.Position;

public class CashRegistry extends Position {
    private String name;
    private Line line;
    private int ID;
    private boolean onPause;

    public CashRegistry(int x, int y, String name, int ID) {
        super(x, y);
        this.name = name;
        this.ID = ID;
        line = new Line();
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
}
