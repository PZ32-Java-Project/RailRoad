package abstractions;

import models.CashRegistry;
import models.Client;
import models.Entrance;

import java.util.ArrayList;
import java.util.List;

public interface ISeedingManager {
    public Client generateClient(ArrayList<Client> clients, ArrayList<Entrance> entrances);
    public List<Entrance> generateEntrances(int entrancesCount);
    public List<CashRegistry> generateCashRegistries(int cashRegistriesCount);
}