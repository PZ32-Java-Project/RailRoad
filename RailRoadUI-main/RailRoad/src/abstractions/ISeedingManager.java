package abstractions;

import models.CashRegistry;
import models.Client;
import models.Entrance;

import java.util.List;

public interface ISeedingManager {
    public Client generateClient(List<Position> clients, List<Position> entrances);
    public List<Entrance> generateEntrances(int entrancesCount);
    public List<CashRegistry> generateCashRegistries(int cashRegistriesCount);
}