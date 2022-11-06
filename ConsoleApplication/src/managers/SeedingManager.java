package managers;

import abstractions.ISeedingManager;
import abstractions.Position;
import models.CashRegistry;
import models.Client;
import models.Entrance;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static shared.Constants.*;

// TODO: create some seed data, add factory pattern
public class SeedingManager implements ISeedingManager {
    private ArrayList<Point> usedPositions;

    public SeedingManager(){
        usedPositions = new ArrayList<>();
    }
    public  Client generateClient(List<Position> clients, List<Position> entrances) {
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
                int chosenEntrance = rand.nextInt(0, 4);
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

    @Override
    public List<Entrance> generateEntrances(int entrancesCount) {
        List<Entrance> entrances = new ArrayList<>();
        if(entrancesCount > 4){
            entrancesCount = 4;
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
        switch (side){
            case 1:
                x = rand.nextInt(0, MAP_WIDTH);
                y = 0;
                usedPositions.add(new Point(x, y));
                entrance = new Entrance(x, y, "Entrance A");
                return entrance;
            case 2:
                x = rand.nextInt(0, MAP_WIDTH);
                y = MAP_HEIGHT;
                usedPositions.add(new Point(x, y));
                entrance = new Entrance(x, y, "Entrance B");
                return entrance;
            case 3:
                x = 0;
                y = rand.nextInt(0, MAP_HEIGHT);
                usedPositions.add(new Point(x, y));
                entrance = new Entrance(x, y, "Entrance C");
                return entrance;
            case 4:
                x = MAP_WIDTH;
                y = rand.nextInt(0, MAP_HEIGHT);
                usedPositions.add(new Point(x, y));
                entrance = new Entrance(x, y, "Entrance D");
                return entrance;
            default:
                entrance = new Entrance(0, 0, "Entrance E");
                usedPositions.add(new Point(0, 0));
                return entrance;
        }
    }

    public List<CashRegistry> generateCashRegistries(int cashRegistriesCount) {
        List<CashRegistry> cashRegistries = new ArrayList<>();
        if(cashRegistriesCount > 4){
            cashRegistriesCount = 4;
        }
        for (int i = 0; i < cashRegistriesCount; ++i) {
            cashRegistries.add(generateCashRegistry(i, (i+1)));
        }

        return cashRegistries;
    }

    private CashRegistry generateCashRegistry(int side, int ID) {
        Random rand = new Random();
        switch (side){
            case 1:
                return new CashRegistry(rand.nextInt(0, MAP_WIDTH), 0, "Cash Registry A", ID);
            case 2:
                return new CashRegistry(rand.nextInt(0, MAP_WIDTH), MAP_HEIGHT, "Cash Registry B", ID);
            case 3:
                return new CashRegistry(0, rand.nextInt(0, MAP_HEIGHT), "Cash Registry C", ID);
            case 4:
                return new CashRegistry(MAP_WIDTH, rand.nextInt(0, MAP_HEIGHT), "Cash Registry D", ID);
            default:
                return new CashRegistry(1, 1, "Cash Registry E", ID);
        }
    }
}