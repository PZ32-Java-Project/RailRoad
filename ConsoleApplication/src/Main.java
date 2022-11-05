import managers.SeedingManager;
import models.Hall;
import models.Map;

public class Main {
    public static void main(String[] args) {
        var hall = new Hall();
        hall.generateCashRegistries(4);
        hall.spawnClients();

        hall.initializeServing();
        System.out.println("lol");
    }
}
