package models;

import abstractions.Position;

public class CashRegistry extends Position {
    private String name;
    private Line line;

    public CashRegistry(int x, int y, String name) {
        super(x, y);
        this.name = name;
        line = new Line();
    }

    public CashRegistry(int x, int y, String name, Line line) {
        super(x, y);
        this.name = name;
        this.line = line;
    }
}
