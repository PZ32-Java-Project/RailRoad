package models;

import abstractions.IInputManager;
import abstractions.ISeedingManager;
import abstractions.Position;
import managers.InputManager;
import managers.SeedingManager;
import shared.Constants;
import threaded.ClientServer;
import threaded.ClientsSpawner;

import javafx.scene.layout.Pane;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static shared.Constants.*;

// TODO: add storage (List) for lines, etc?
public class Hall {
    private Map map;
    private IInputManager inputManager;
    private ISeedingManager seedingManager;
    private Pane pane;
    private boolean terminate = false;

    public Hall(Pane pane) {
        map = Map.getInstance();
        inputManager = new InputManager();
        seedingManager = new SeedingManager(pane);
        this.pane = pane;
    }

    public void initialize(int cashCount, int entranceCount, int spawnInterval, int cashRegistryServeTime){
        terminate = false;
        Constants.cashRegistriesCount = cashCount;
        Constants.entranceCount = entranceCount;
        Constants.spawnInterval = spawnInterval;
        Constants.cashRegistryServeTime = cashRegistryServeTime;
        Lock lock = new ReentrantLock();
        generateCashRegistries();
        generateCashEntrances();
        spawnClients(lock);
        initializeServing(lock);
    }

    public void stop(){
        terminate =true;
    }

    public void spawnClients(Lock lock) {
        /*for (Position pos : map.getClients()) {
            Client newClient = (Client)pos;
            newClient.clientUI();
        }*/
        var thread = new ClientsSpawner(this, lock, spawnInterval, pane); // -1 Для рандомного інтервалу спавна, інакше в мілісекундах
        thread.start();
    }

    public void initializeServing(Lock lock) {
        for (var cashRegistry :map.getCashRegistries() ) {
            var thread = new ClientServer((CashRegistry)cashRegistry ,this, lock, cashRegistryServeTime); // -1 Для рандомного інтервалу спавна, інакше в мілісекундах
            thread.start();
        }
    }

    public void generateCashRegistries(){
        var cashRegistries =  seedingManager.generateCashRegistries(cashRegistriesCount);
        map.getPositions().addAll(cashRegistries);
    }

    public void generateCashEntrances(){
        var entrances =  seedingManager.generateEntrances(entranceCount);
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

    public boolean isTerminate() {
        return terminate;
    }


}