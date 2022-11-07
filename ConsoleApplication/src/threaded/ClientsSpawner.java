package threaded;

import abstractions.Position;
import managers.SeedingManager;
import models.CashRegistry;
import models.Client;
import models.Hall;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static shared.Constants.LINE_MAX_CLIENTS_COUNT;

public class ClientsSpawner extends Thread{
    private Hall hall;
    private Lock lock;
    int interval;
    public ClientsSpawner(Hall hall, Lock lock, int interval){
        this.hall=hall;
        this.lock=lock;
        this.interval=interval;
    }
    public void run(){

        var map =hall.getMap();
        boolean enableSpawning = true;
        while(true) {
            try{
                lock.lock();
                int clientsCount = map.getClients().size();
                int clientsLimit = LINE_MAX_CLIENTS_COUNT*map.getCashRegistries().size();
                //int clientsLimit = LINE_MAX_CLIENTS_COUNT * 2;
                if (clientsCount >= clientsLimit)
                    enableSpawning=false;
                else if(clientsCount<clientsLimit*0.7)
                    enableSpawning = true;
                if (enableSpawning) {
                    List<Position> cashRegistries = hall.getMap().getCashRegistries();
                    var clientsList = map.getClients();
                    var entrancesList = map.getEntrances();
                    var client =  new SeedingManager().generateClient(clientsList,entrancesList);
                    Optional<CashRegistry> cashRegistryOptional = findCashRegistry(cashRegistries, client);
                    var cashRegistry = cashRegistryOptional.stream().findFirst().orElse(null);
                    map.getPositions().add(client);
                    var cashLine = cashRegistry.getLine();
                    cashLine.tryAdd(client);
                    cashRegistry.setLine(cashLine);
                    System.out.println("client " + client.getName() +" spawned at: "+ client.getPosition().getX()+","+client.getPosition().getY());
                }
            }
            finally {
                lock.unlock();
                try {
                    if(interval==-1) {
                        var random = new Random();
                        sleep(random.nextInt(3000));
                    }
                    else{
                        sleep(interval);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public Optional<CashRegistry> findCashRegistry(List<Position> cashRegistries, Client client){
        List<CashRegistry> cashRegistriesList = (List<CashRegistry>)(List<?>) cashRegistries;
        Optional<CashRegistry> result  = cashRegistriesList.stream().filter(c->c.isOnPause()==false).min((i, j) -> {
            var cr1 = (Integer)(i.getLine().getClients().size());
            var cr2 = j.getLine().getClients().size();
            return  cr1.compareTo(cr2);
        }).stream().min((i, j) -> {
            var cr1 = (Double)client.getDistance(i.getPosition());
            var cr2 = client.getDistance(j.getPosition());
            return  cr1.compareTo(cr2);
        });
        return result;
    }
}
