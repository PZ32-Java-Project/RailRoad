package models;

import abstractions.IInputManager;
import abstractions.ISeedingManager;
import managers.InputManager;
import managers.SeedingManager;
import shared.Constants;
import threaded.ClientServer;
import threaded.ClientsSpawner;

import static shared.Constants.LINE_MAX_CLIENTS_COUNT;

// TODO: add storage (List) for lines, etc?
public class Hall {
    private Map map;
    private IInputManager inputManager;
    private ISeedingManager seedingManager;

    public Hall() {
        map = Map.getInstance();
        inputManager = new InputManager();
        seedingManager = new SeedingManager();
    }
    public void spawnClients(){
        var thread = new ClientsSpawner(this);
        thread.start();
/*        int i=0;
        boolean enableSpawning = true;
        while(i<100) {
            int clientsCount = map.getClients().size();
            //int clientsLimit = LINE_MAX_CLIENTS_COUNT*map.getCashRegistries().size()
            int clientsLimit = LINE_MAX_CLIENTS_COUNT * 2;
            if (clientsCount >= clientsLimit)
                enableSpawning=false;
            else if(clientsCount<clientsLimit*0.7)
                enableSpawning = true;
            if (enableSpawning) {
                var client = SeedingManager.generateClient();
                map.getPositions().add(client);
                System.out.println("client " + client.getName() +" spawned at: "+ client.getPosition().getX()+","+client.getPosition().getY());
            }
            i++;
        }*/
    }
    public void generateCashRegistries(int count){
        var cashRegistries =  seedingManager.generateCashRegistries(count);
        map.getPositions().addAll(cashRegistries);
    }
    public void initializeServing() {

        for (var cashRegistry :map.getCashRegistries() ) {
            var thread = new ClientServer((CashRegistry)cashRegistry ,this);
            thread.start();
        }

    }
    public void setDataFromUserInput() {
        Constants.entranceCount = inputManager.getEntranceCount();
        Constants.cashRegistriesCount = inputManager.getCashRegistriesCount();
        Constants.cashRegistryServeTimeMax = inputManager.getCashRegistryServeTime();
    }

    public void seedData() {
        // Call seedingManager here:
        // var cashRegistries = seedingManager.generateCashRegistries(Constants.cashRegistriesCount);
        // map.tryAdd(cashRegistries.get(0));
    }
    public Map getMap(){
        return map;
    }
}
