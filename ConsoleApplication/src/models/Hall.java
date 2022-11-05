package models;

import abstractions.IInputManager;
import abstractions.ISeedingManager;
import managers.InputManager;
import managers.SeedingManager;
import shared.Constants;

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
}
