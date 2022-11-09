package managers;

import abstractions.ISeedingManager;
import abstractions.Position;
import models.CashRegistry;
import models.Client;
import models.Entrance;
import models.ReserveCashRegistry;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static shared.Constants.*;

// TODO: create some seed data, add factory pattern
public class SeedingManager implements ISeedingManager {
    private ArrayList<Point> usedPositions;

    private boolean generatedReserveRegistry = false;
    int ID = 1;
    public SeedingManager(){
        usedPositions = new ArrayList<>();
    }
    public Client generateClient(List<Position> clients, List<Position> entrances) {
        Client client;
        while(true){
            boolean flag = false;
            Random rand = new Random();
            int chosenID = rand.nextInt(100);
            for(int i=0; i<clients.size(); ++i){
                var clientTemp =(Client)clients.get(i);
                if(chosenID == clientTemp.getID()){
                    flag = true;
                    break;
                }
            }
            if(flag == false){
                int chosenEntrance = rand.nextInt(0, entrances.size());
                int chosenName;
                int chosenSurname;
                while(true) {
                    chosenName = rand.nextInt(0, names.length);
                    chosenSurname = rand.nextInt(0, surnames.length);
                    boolean flag2 = false;
                    for(int i=0; i<clients.size(); ++i){
                        var clientTemp =(Client)clients.get(i);
                        if(names[chosenName].equals(clientTemp.getName())
                                && surnames[chosenSurname].equals(clientTemp.getSurname())){
                            flag2 = true;
                            break;
                        }
                    }
                    if(flag2 == false){
                        break;
                    }
                }
                client = new Client((Entrance) entrances.get(chosenEntrance), chosenID, names[chosenName], surnames[chosenSurname]);
                break;
            }
        }
        return client;
    }

    public List<Entrance> generateEntrances(int entrancesCount) {
        List<Entrance> entrances = new ArrayList<>();
        if(entrancesCount > 5){
            entrancesCount = 5;
        }
        for (int i = 0; i < entrancesCount; ++i) {
            entrances.add(generateEntrance(i));
        }
        return entrances;
    }

    private Entrance generateEntrance(int side) {
        Random rand = new Random();
        int x;
        int y;
        Entrance entrance;
        switch (side + 1){
            case 1:
                while (true){
                    boolean flag = false;
                    x = rand.nextInt(0, MAP_WIDTH);
                    y = 0;
                    if (CheckPositions(x, y)) break;
                }
                usedPositions.add(new Point(x, y));
                entrance = new Entrance(x, y, "Entrance A");
                return entrance;
            case 2:
                while (true){
                    boolean flag = false;
                    x = rand.nextInt(0, MAP_WIDTH);
                    y = 0;
                    if (CheckPositions(x, y)) break;
                }
                usedPositions.add(new Point(x, y));
                entrance = new Entrance(x, y, "Entrance B");
                return entrance;
            case 3:
                while (true){
                    boolean flag = false;
                    x = rand.nextInt(0, MAP_WIDTH);
                    y = MAP_HEIGHT;
                    if (CheckPositions(x, y)) break;
                }
                usedPositions.add(new Point(x, y));
                entrance = new Entrance(x, y, "Entrance C");
                return entrance;
            case 4:
                while (true){
                    boolean flag = false;
                    x = rand.nextInt(0, MAP_WIDTH);
                    y = MAP_HEIGHT;
                    if (CheckPositions(x, y)) break;
                }
                usedPositions.add(new Point(x, y));
                entrance = new Entrance(x, y, "Entrance D");
                return entrance;
            case 5:
                while (true){
                    boolean flag = false;
                    x = rand.nextInt(0, MAP_WIDTH);
                    y = MAP_HEIGHT;
                    if (CheckPositions(x, y)) break;
                }
                usedPositions.add(new Point(x, y));
                entrance = new Entrance(x, y, "Entrance E");
                return entrance;
            default:
                entrance = new Entrance(0, 0, "Entrance Default");
                usedPositions.add(new Point(0, 0));
                return entrance;
        }
    }
    public List<CashRegistry> generateCashRegistries(int cashRegistriesCount) {
        List<CashRegistry> cashRegistries = new ArrayList<>();
        if(cashRegistriesCount > 5){
            cashRegistriesCount = 5;
        }
        for (int i = 0; i < cashRegistriesCount; ++i) {
            cashRegistries.add(generateCashRegistry(i));
        }
        return cashRegistries;
    }

    public ReserveCashRegistry generateReserveCashRegistry(){
        if(generatedReserveRegistry == false){
            Random rand = new Random();
            int x = rand.nextInt(0, MAP_WIDTH);
            int y = 0;
            while(true){
                if(CheckPositions(x, y)) break;
                x = 0;
                y = rand.nextInt(0, MAP_HEIGHT);
            }
            usedPositions.add(new Point(x, y));
            ReserveCashRegistry cashRegistry = new ReserveCashRegistry(x, y, "ReserveRegistry", 0);

            return cashRegistry;
        }else{
            return null;
        }
    }
    private CashRegistry generateCashRegistry(int side) {
        Random rand = new Random();
        int x;
        int y;
        CashRegistry registry;
        switch (side + 1){
            case 1:
                while (true){
                    x = 0;
                    y = rand.nextInt(0, MAP_HEIGHT);
                    if (CheckPositions(x, y)) break;
                }
                registry = new CashRegistry(x, y, "Cash Registry A", ID);
                usedPositions.add(new Point(x, y));
                ID++;
                return registry;
            case 2:
                while (true){
                    x = 0;
                    y = rand.nextInt(0, MAP_HEIGHT);
                    if (CheckPositions(x, y)) break;
                }
                registry = new CashRegistry(x, y, "Cash Registry B", ID);
                usedPositions.add(new Point(x, y));
                ID++;
                return registry;
            case 3:
                while (true){
                    x = MAP_WIDTH;
                    y = rand.nextInt(0, MAP_HEIGHT);
                    if (CheckPositions(x, y)) break;
                }
                registry = new CashRegistry(x, y, "Cash Registry C", ID);
                usedPositions.add(new Point(x, y));
                ID++;
                return registry;
            case 4:
                while (true){
                    x = MAP_WIDTH;
                    y = rand.nextInt(0, MAP_HEIGHT);
                    if (CheckPositions(x, y)) break;
                }
                registry = new CashRegistry(x, y, "Cash Registry D", ID);
                usedPositions.add(new Point(x, y));
                ID++;
                return registry;
            case 5:
                while (true){
                    x = MAP_WIDTH;
                    y = rand.nextInt(0, MAP_HEIGHT);
                    if (CheckPositions(x, y)) break;
                }
                registry = new CashRegistry(x, y, "Cash Registry E", ID);
                usedPositions.add(new Point(x, y));
                ID++;
                return registry;
            default:
                registry = new CashRegistry(0, 50, "Cash Registry Default", ID);
                usedPositions.add(new Point(0, 50));
                ID++;
                return registry;
        }
    }
    private boolean CheckPositions(int x, int y) {
        boolean flag = false;
        for(int i=0; i<usedPositions.size(); ++i){
            if(x == usedPositions.get(i).getX() && y == usedPositions.get(i).getY()){
                flag = true;
            }
        }
        if(flag == false){
            return true;
        }
        return false;
    }

}