package models;

import managers.InputManager;
import managers.SeedingManager;
import shared.Constants;

// TODO: add storage (List) for lines, etc?
public class Hall {
    private Map map;
    private InputManager inputManager;
    private SeedingManager seedingManager;

    public Hall() {
        map = Map.getInstance();
        inputManager = new InputManager();
        seedingManager = new SeedingManager();
    }

    public void setDataFromUserInput() {
        Constants.entranceCount = inputManager.getEntranceCount();
        Constants.cashRegistriesCount = inputManager.getCashRegistriesCount();
        Constants.cashRegistryServeTime = inputManager.getCashRegistryServeTime();
    }

    public void SeedData() {
        // Call seedingManager here:
        // var cashRegistries = seedingManager.generateCashRegistries(Constants.cashRegistriesCount);
        // map.tryAdd(...);
    }
}
