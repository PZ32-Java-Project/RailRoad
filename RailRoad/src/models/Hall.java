package models;

import abstractions.IInputManager;
import abstractions.ISeedingManager;
import abstractions.Position;
import managers.InputManager;
import managers.SeedingManager;
import shared.Constants;
import shared.Global;
import shared.MyFileWritter;
import threaded.ClientServer;
import threaded.ClientsSpawner;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static shared.Constants.*;
import static shared.Global.pane;

// TODO: add storage (List) for lines, etc?
public class Hall {
    private static Hall instance;
    private Map map;
    private IInputManager inputManager;
    private ISeedingManager seedingManager;
    private boolean terminate = false;


    private Hall() {
        map = Map.getInstance();
        inputManager = new InputManager();
        seedingManager = new SeedingManager();

    }


    public static Hall getInstance() {
        if (instance == null){
            instance = new Hall();
        }
        return instance;
    }
    public void initialize(int cashCount, int entranceCount, int spawnInterval, int cashRegistryServeTime){
        terminate = false;
        Constants.cashRegistriesCount = cashCount;
        Constants.entranceCount = entranceCount;
        Constants.spawnInterval = spawnInterval;
        Constants.cashRegistryServeTime = cashRegistryServeTime;
        Lock lock = new ReentrantLock();
        generateCashRegistries();
        generateReserveCashRegistry();
        generateCashEntrances();
        generateExit();
        spawnClients(lock);
        initializeServing(lock);
    }

    public void stop(){
        terminate = true;
        map.setData(new ArrayList<>());
        pane.getChildren().clear();
    }

    public void spawnClients(Lock lock) {
        var thread = new ClientsSpawner(this, lock, spawnInterval); // -1 Для рандомного інтервалу спавна, інакше в мілісекундах
        thread.start();
    }

    public void initializeServing(Lock lock) {
        for (var cashRegistry : map.getCashRegistries()) {
            var thread = new ClientServer((CashRegistry)cashRegistry , lock, cashRegistryServeTime); // -1 Для рандомного інтервалу спавна, інакше в мілісекундах
            thread.start();
        }
            var thread = new ClientServer(map.getReserveCashRegistry() , lock, cashRegistryServeTime); // -1 Для рандомного інтервалу спавна, інакше в мілісекундах
            thread.start();

    }

    public void generateExit() {
        var exit = seedingManager.generateExit();
        var result = map.tryAdd(exit);
        MyFileWritter.Write(result ? "Exit was added successfully" : "Couldn't add an exit");
    }

    public void generateReserveCashRegistry() {
        var reserveCashRegistry = seedingManager.generateReserveCashRegistry();
        var result = map.tryAdd(reserveCashRegistry);
        MyFileWritter.Write(result ? "Reserve cash registry was added successfully" : "Couldn't add reserve registry");
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

    public Map getMap(){
        return map;
    }

    public boolean isTerminate() {
        return terminate;
    }


}