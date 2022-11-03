package abstractions;

import models.CashRegistry;
import models.Client;
import models.Entrance;

import java.util.List;

public interface ISeedingManager {
    public List<Client> generateClients(int clientsCount);
    public List<Entrance> generateEntrances(int entrancesCount);
    public List<CashRegistry> generateCashRegistries(int cashRegistriesCount);
}
