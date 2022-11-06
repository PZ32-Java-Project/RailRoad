package models;

import abstractions.Position;
import shared.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Singleton pattern
public class Map {
    private static Map instance;
    private final int width;
    private final int height;
    private List<Position> positions;

    private Map() {
        width = Constants.MAP_WIDTH;
        height = Constants.MAP_HEIGHT;
        positions = new ArrayList<>();
    }

    public static Map getInstance() {
        if (instance == null)
        {
            instance = new Map();
        }

        return instance;
    }

    public boolean tryAdd(Position entity) {
        // If position is taken
        if (positions.stream().anyMatch(e -> e.isPosition(entity))) {
            return false;
        }
        positions.add(entity);
        return true;
    }
    public List<Position>getPositions(){
        return positions;
    }
    public List<Position>getClients(){
     return positions.stream().filter(c -> c instanceof Client).collect(Collectors.toList());
    }
    public List<Position> getCashRegistries(){
        return positions.stream().filter(c -> c instanceof CashRegistry).collect(Collectors.toList());
    }
    public void setData(List<Position> positions){
        this.positions=positions;
    }
    // TODO: add map matrix string to check functionality
    @Override
    public String toString() {
        return "";
    }
}
