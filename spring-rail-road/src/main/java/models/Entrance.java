package models;

import abstractions.Position;

public class Entrance extends Position {
    private String name;

    public Entrance(int x, int y, String name) {
        super(x, y);
        this.name = name;
    }
}
