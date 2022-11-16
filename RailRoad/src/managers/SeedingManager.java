package managers;

import abstractions.ISeedingManager;
import abstractions.Position;
import models.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static shared.Constants.*;

// TODO: create some seed data, add factory pattern
public class SeedingManager implements ISeedingManager {
    private List<Point> usedPositions;
    private boolean generatedReserveRegistry = false;
    private static int currentClientId = 0;
    private static int currentCashRegistryId = 0;
    private static char currentEntranceSymbol = 'A';
    private static char currentCashRegistrySymbol = 'A';

    public SeedingManager(){
        usedPositions = new ArrayList<>();
    }

    public Client generateClient(List<Position> clients, List<Position> entrances){
        Random rand = new Random();
        int chosenEntrance = rand.nextInt(0, entrances.size());
        String chosenName;
        String chosenSurname;
        while(true) {
            var currentName = names[rand.nextInt(0, names.length)];
            var currentSurname = surnames[rand.nextInt(0, surnames.length)];
            boolean isNameSurnameTaken = clients.stream()
                    .map(c -> (Client)c)
                    .anyMatch(c -> c.getName().equals(currentName) && c.getSurname().equals(currentSurname));
            if (!isNameSurnameTaken) {
                chosenName = currentName;
                chosenSurname = currentSurname;
                break;
            }
        }
        ++currentClientId;
        var chance = rand.nextInt(4);
        ClientTypes type = ClientTypes.Ordinary;
        ClientTypes[] EnumValues = ClientTypes.values();
        if(chance==0){
            type = EnumValues[rand.nextInt(ClientTypes.values().length-1)];
        }
        return new Client(
            (Entrance) entrances.get(chosenEntrance),
            currentClientId,
            chosenName,
            chosenSurname,
            type
        );
    }

    public List<Entrance> generateEntrances(int entrancesCount) {
        List<Entrance> entrances = new ArrayList<>();
        if(entrancesCount > MAX_ENTRANCES_COUNT){
            entrancesCount = MAX_ENTRANCES_COUNT;
        }
        for (int i = 0; i < entrancesCount; ++i) {
            entrances.add(generateEntrance(i));
        }
        return entrances;
    }

    // TODO: Замінити while(true) і CheckPositions() на map.tryAdd()
    private Entrance generateEntrance(int side) {
        Random rand = new Random();
        int x;
        int y = 0;
        while (true) {
            x = rand.nextInt(0, MAP_WIDTH);
            if (CheckPositions(x, y, entranceSize)) {
                break;
            }
        }
        usedPositions.add(new Point(x, y));
        var entrance = new Entrance(x, y, "Entrance " + currentEntranceSymbol);
        ++currentEntranceSymbol;
        return entrance;
    }

    public List<CashRegistry> generateCashRegistries(int cashRegistriesCount) {
        List<CashRegistry> cashRegistries = new ArrayList<>();
        if(cashRegistriesCount > MAX_CASH_REGISTRIES_COUNT){
            cashRegistriesCount = MAX_CASH_REGISTRIES_COUNT;
        }
        for (int i = 0; i < cashRegistriesCount; ++i) {
            boolean isRight = i > 2;
            cashRegistries.add(generateCashRegistry(isRight));
        }
        return cashRegistries;
    }

    public Exit generateExit() {
        var rand = new Random();
        int x = rand.nextInt(exitRadius, MAP_WIDTH - exitRadius);
        int y = MAP_HEIGHT;
        return new Exit(x, y);
    }

    public ReserveCashRegistry generateReserveCashRegistry(){
        if(generatedReserveRegistry == false){
            Random rand = new Random();
            int x = rand.nextInt(cashRegistryWidth, MAP_WIDTH - cashRegistryWidth);
            int y = 0;
            while(true){
                if(CheckPositions(x, y, cashRegistryWidth)) break;
                /*x = 0;
                y = rand.nextInt(cashRegistryWidth, MAP_HEIGHT - cashRegistryWidth);*/
            }
            usedPositions.add(new Point(x, y));
            ReserveCashRegistry cashRegistry = new ReserveCashRegistry(x, y, "ReserveRegistry", 0);

            return cashRegistry;
        }else{
            return null;
        }
    }

    private CashRegistry generateCashRegistry(boolean isRight) {
        Random rand = new Random();
        int x = isRight ? MAP_WIDTH - cashRegistryWidth*2 : cashRegistryWidth;
        int y;
        while (true) {
            y = rand.nextInt(cashRegistryWidth, MAP_WIDTH - cashRegistryWidth);
            if (CheckPositions(x, y, cashRegistryWidth)) {
                break;
            }
        }
        usedPositions.add(new Point(x, y));
        var registry = new CashRegistry(x, y, "Cash Registry " + currentCashRegistrySymbol,
                currentCashRegistryId);
        ++currentCashRegistrySymbol;
        return registry;
    }

    private boolean CheckPositions(int x, int y, int width) {
        boolean flag = true;
        for (Point usedPosition : usedPositions) {
            int tempX = (int)usedPosition.getX();
            int tempY = (int)usedPosition.getY();
            if ((x > (tempX - (width/2 + 3)) && y > (tempY - (width/2 + 3)))
            && (x < (tempX + (width/2 + 3)) && y < (tempY + (width/2 + 3)))) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}