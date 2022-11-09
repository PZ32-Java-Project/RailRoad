package railroad_simulation.models;

import railroad_simulation.abstractions.IInputManager;
import railroad_simulation.abstractions.ISeedingManager;
import railroad_simulation.abstractions.Position;
import railroad_simulation.managers.InputManager;
import railroad_simulation.managers.SeedingManager;
import railroad_simulation.shared.Constants;
import railroad_simulation.threaded.ClientServer;
import railroad_simulation.threaded.ClientsSpawner;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static railroad_simulation.shared.Constants.*;

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

    public void initialize(int cashCount, int entranceCount, int spawnInterval, int cashRegistryServeTime, Pane hall){
        Constants.cashRegistriesCount=cashCount;
        Constants.entranceCount =entranceCount;
        Constants.spawnInterval = spawnInterval;
        Constants.cashRegistryServeTime=cashRegistryServeTime;
        Lock lock = new ReentrantLock();
        generateCashRegistries(hall);
        generateCashEntrances(hall);
        spawnClients(lock, hall);

        initializeServing(lock);
    }

    public void spawnClients(Lock lock, Pane hall){
        for(Position pos : map.getClients()){
            Client newClient = (Client)pos;
            newClient.clientUI();
        }
        var thread = new ClientsSpawner(this, lock, spawnInterval, hall); // -1 Для рандомного інтервалу спавна, інакше в мілісекундах
        thread.start();
    }

    public void initializeServing(Lock lock) {
        for (var cashRegistry :map.getCashRegistries() ) {
            var thread = new ClientServer((CashRegistry)cashRegistry ,this, lock, cashRegistryServeTime); // -1 Для рандомного інтервалу спавна, інакше в мілісекундах
            thread.start();
        }
    }

    public void generateCashRegistries(Pane hall){
        var cashRegistries =  seedingManager.generateCashRegistries(cashRegistriesCount, hall);
        map.getPositions().addAll(cashRegistries);
    }

    public void generateCashEntrances(Pane hall){
        var entrances =  seedingManager.generateEntrances(entranceCount, hall);
        map.getPositions().addAll(entrances);
    }

    public void setDataFromUserInput() {
        Constants.entranceCount = inputManager.getEntranceCount();
        cashRegistriesCount = inputManager.getCashRegistriesCount();
        Constants.cashRegistryServeTime = inputManager.getCashRegistryServeTime();
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