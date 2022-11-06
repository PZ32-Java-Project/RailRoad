package models;

import abstractions.IInputManager;
import abstractions.ISeedingManager;
import managers.InputManager;
import managers.SeedingManager;
import shared.Constants;
import threaded.ClientServer;
import threaded.ClientsSpawner;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static shared.Constants.*;

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

    public void initialize(int cashCount, int entranceCount){
        cashRegistriesCount=cashCount;
        Constants.entranceCount =entranceCount;
        Lock lock = new ReentrantLock();
        generateCashRegistries();
        generateCashEntrances();
        spawnClients(lock);
        initializeServing(lock);
    }
    public void generateCashRegistries(){
        var cashRegistries =  seedingManager.generateCashRegistries(cashRegistriesCount);
        map.getPositions().addAll(cashRegistries);
    }
    public void generateCashEntrances(){
        var entrances =  seedingManager.generateEntrances(entranceCount);
        map.getPositions().addAll(entrances);
    }
    public void spawnClients(Lock lock){
        var thread = new ClientsSpawner(this, lock,-1); // -1 Для рандомного інтервалу спавна, інакше в мілісекундах
        thread.start();
    }
    public void initializeServing(Lock lock) {

        for (var cashRegistry :map.getCashRegistries() ) {
            var thread = new ClientServer((CashRegistry)cashRegistry ,this, lock, -1); // -1 Для рандомного інтервалу спавна, інакше в мілісекундах
            thread.start();
        }

    }
    public void setDataFromUserInput() {
        Constants.entranceCount = inputManager.getEntranceCount();
        cashRegistriesCount = inputManager.getCashRegistriesCount();
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
