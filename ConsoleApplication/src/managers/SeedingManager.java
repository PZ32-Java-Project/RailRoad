package managers;

import abstractions.ISeedingManager;
import models.CashRegistry;
import models.Client;
import models.Entrance;

import java.util.ArrayList;
import java.util.List;

// TODO: create some seed data, add factory pattern
public class SeedingManager implements ISeedingManager {
    public List<Client> generateClients(int clientsCount) {
        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < clientsCount; ++i) {
            clients.add(generateClient());
        }

        return clients;
    }

    public static Client generateClient() {
        return new Client(0, 0, "Vasyl", "Lysav");
    }

    public List<Entrance> generateEntrances(int entrancesCount) {
        List<Entrance> entrances = new ArrayList<>();
        for (int i = 0; i < entrancesCount; ++i) {
            entrances.add(generateEntrance());
        }

        return entrances;
    }

    private static Entrance generateEntrance() {
        return new Entrance(0, 0, "Entrance A");
    }

    public  List<CashRegistry>  generateCashRegistries(int cashRegistriesCount) {
        List<CashRegistry> cashRegistries = new ArrayList<>();
        for (int i = 0; i < cashRegistriesCount; ++i) {
            cashRegistries.add(generateCashRegistry());
        }
        return cashRegistries;
    }

    public static CashRegistry generateCashRegistry() {
        return new CashRegistry(0, 0, "Cash Registry A");
    }
}
