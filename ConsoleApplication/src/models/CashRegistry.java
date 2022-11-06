package models;

import abstractions.Position;

public class CashRegistry extends Position {
    private String name;
    private Line line;
    private int ID;

    public CashRegistry(int x, int y, String name, int ID) {
        super(x, y);
        this.name = name;
        this.ID = ID;
        line = new Line();
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
}
