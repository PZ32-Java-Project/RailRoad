import managers.SeedingManager;
import models.CashRegistry;
import models.Entrance;
import models.Hall;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SeedingManager manager = new SeedingManager();

        List<CashRegistry> registryList = manager.generateCashRegistries(4);

        List<Entrance> entrances = manager.generateEntrances(4);
        int a = 5;
    }
}
