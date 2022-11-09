package railroad_simulation.abstractions;

import javafx.scene.layout.Pane;
import railroad_simulation.models.CashRegistry;
import railroad_simulation.models.Client;
import railroad_simulation.models.Entrance;

import java.util.ArrayList;
import java.util.List;

public interface ISeedingManager {
    public Client generateClient(List<Position> clients, List<Position> entrances, Pane hall);
    public List<Entrance> generateEntrances(int entrancesCount, Pane hall);
    public List<CashRegistry> generateCashRegistries(int cashRegistriesCount, Pane hall);
}