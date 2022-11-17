package threaded;

import abstractions.ISeedingManager;
import abstractions.Position;
import javafx.application.Platform;
import managers.SeedingManager;
import models.CashRegistry;
import models.Client;
import models.Hall;
import models.Map;

import java.util.*;
import java.util.concurrent.locks.Lock;

import static shared.Constants.LINE_MAX_CLIENTS_COUNT;
import static shared.MyFileWritter.Write;

public class ClientsSpawner extends Thread{
    private Hall hall;
    private Lock lock;
    int interval;
    private ISeedingManager seedingManager;

    public ClientsSpawner(Hall hall, Lock lock, int interval) {
        this.hall = hall;
        this.lock = lock;
        this.interval = interval;
        seedingManager = new SeedingManager();
    }

    public void run() {
        var map = Map.getInstance();
        boolean enableSpawning = true;
        while(true) {
            try {
                if(hall.isTerminate()){
                    return;
                }else {
                    lock.lock();
                    int clientsCount = map.getClients().size();
                    int clientsLimit = LINE_MAX_CLIENTS_COUNT * (map.getCashRegistries().size()-1);
                    if (clientsCount >= clientsLimit)
                        enableSpawning = false;
                    else if (clientsCount < clientsLimit * 0.7)
                        enableSpawning = true;
                    if (enableSpawning) {
                            var cashRegistries = map.getAvailableCashRegistries();
                            var clientsList = map.getClients();
                            var entrancesList = map.getEntrances();
                            var client = seedingManager.generateClient(clientsList, entrancesList);
                            Platform.runLater(() -> client.updateUI());
                            Optional<CashRegistry> cashRegistryOptional = findCashRegistry(cashRegistries, client);
                            var cashRegistry = cashRegistryOptional.stream().findFirst().orElse(null);
                            map.getPositions().add(client);
                            var cashLine = cashRegistry.getLine();
                            cashLine.tryAdd(client);
                            cashRegistry.setLine(cashLine);
                            cashRegistry.updatedLineUI();
                            Write("client " + client.getName() + " spawned at: " + client.getPosition().getX() + "," + client.getPosition().getY());
                            System.out.println("client " + client.getName() + " spawned at: " + client.getPosition().getX() + "," + client.getPosition().getY());
                    }
                }
            }
            finally {
                lock.unlock();
                try {
                    if(hall.isTerminate()){
                        Write("Spawner has stopped");
                        System.out.println("Spawner has stopped");
                        return;
                    }
                    if(interval==-1) {
                        var random = new Random();
                        sleep(random.nextInt(3000));
                    }
                    else{
                        sleep(interval);
                    }
                    if(hall.isTerminate()){
                        Write("Spawner has stopped");
                        System.out.println("Spawner has stopped");
                        return;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public Optional<CashRegistry> findCashRegistry(List<Position> cashRegistries, Client client){
        List<CashRegistry> cashRegistriesList = (List<CashRegistry>)(List<?>) cashRegistries;
        var minLineSize =  (cashRegistries.stream()
                .map(c->((CashRegistry)c).getLine().getClients().size())
                .min(Comparator.naturalOrder()))
                .stream().findFirst().orElse(0);
        var closestCash  = cashRegistriesList.stream()
                .filter(c->!c.isOnPause()&&c.getLine().getClients().size() == minLineSize);
        var closestVacantCash =  (closestCash.min((i, j) -> {
            var cr1 = (Double)(client.getDistance(i.getPosition()));
            var cr2 = (Double)(client.getDistance(j.getPosition()));
            return  cr1.compareTo(cr2);
        }));
        return closestVacantCash;
    }
}