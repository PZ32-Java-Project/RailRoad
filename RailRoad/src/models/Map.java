package models;

import abstractions.Position;
import javafx.geometry.Pos;
import shared.Constants;
import shared.Global;
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
        if (instance == null){
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
    public List<Position> getPositions(){
        return positions;
    }
    public List<Position> getClients(){
     return positions.stream().filter(c -> c instanceof Client).collect(Collectors.toList());
    }
    public List<Position> getEntrances(){
        return positions.stream().filter(c -> c instanceof Entrance).collect(Collectors.toList());
    }
    public List<Position> getCashRegistries(){
        return positions.stream()
                .filter(c -> c instanceof CashRegistry).collect(Collectors.toList());
    }
    public List<Position> getAvailableCashRegistries() {
        return positions.stream()
                .filter(p -> p instanceof CashRegistry)
                .map(p -> (CashRegistry)p)
                .filter(c -> !c.onPause)
                .collect(Collectors.toList());
    }
    public void setData(List<Position> positions){
        this.positions=positions;
    }
    // TODO: add map matrix string to check functionality
    @Override
    public String toString() {
        return "";
    }
    public void removeAt(Position position) {
        positions.remove(position);
    }

    public Exit getExit() {
        return (Exit)positions.stream()
                .filter(p -> p instanceof Exit)
                .findFirst()
                .orElse(null);
    }

    public ReserveCashRegistry getReserveCashRegistry() {
        return (ReserveCashRegistry)positions.stream()
                .filter(p -> p instanceof ReserveCashRegistry)
                .findFirst()
                .orElse(null);
    }
}