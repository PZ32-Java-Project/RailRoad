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
    public Client generateClient(List<Client> clients, List<Entrance> entrances) {
        Client client;
        while(true){
            boolean flag = false;
            Random rand = new Random();
            int chosenID = rand.nextInt(100);
            for(int i=0; i<clients.size(); ++i){
                if(chosenID == clients.get(i).getID()){
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
                        if(names[chosenName].equals(clients.get(i).getName())
                                && surnames[chosenSurname].equals(clients.get(i).getSurname())){
                            flag2 = true;
                            break;
                        }
                    }
                    if(flag2 == false){
                        break;
                    }
                }
                client = new Client(entrances.get(chosenEntrance), chosenID, names[chosenName], surnames[chosenSurname]);
                break;
            }
        }

        return client;
    }

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
                while (true){
                    boolean flag = false;
                    x = rand.nextInt(0, MAP_WIDTH);
                    y = 0;
                    for(int i=0; i<usedPositions.size(); ++i){
                        if(x ==usedPositions.get(i).getX() && y == usedPositions.get(i).getY()){
                            flag = true;
                        }
                    }
                    if(flag == false){
                        break;
                    }
                }
                usedPositions.add(new Point(x, y));
                entrance = new Entrance(x, y, "Entrance A");
                return entrance;
            case 2:
                while (true){
                    boolean flag = false;
                    x = rand.nextInt(0, MAP_WIDTH);
                    y = MAP_HEIGHT;
                    for(int i=0; i<usedPositions.size(); ++i){
                        if(x ==usedPositions.get(i).getX() && y == usedPositions.get(i).getY()){
                            flag = true;
                        }
                    }
                    if(flag == false){
                        break;
                    }
                }
                usedPositions.add(new Point(x, y));
                entrance = new Entrance(x, y, "Entrance B");
                return entrance;
            case 3:
                while (true){
                    boolean flag = false;
                    x = 0;
                    y = rand.nextInt(0, MAP_HEIGHT);
                    for(int i=0; i<usedPositions.size(); ++i){
                        if(x ==usedPositions.get(i).getX() && y == usedPositions.get(i).getY()){
                            flag = true;
                        }
                    }
                    if(flag == false){
                        break;
                    }
                }
                usedPositions.add(new Point(x, y));
                entrance = new Entrance(x, y, "Entrance C");
                return entrance;
            case 4:
                while (true){
                    boolean flag = false;
                    x = MAP_WIDTH;
                    y = rand.nextInt(0, MAP_HEIGHT);
                    for(int i=0; i<usedPositions.size(); ++i){
                        if(x ==usedPositions.get(i).getX() && y == usedPositions.get(i).getY()){
                            flag = true;
                        }
                    }
                    if(flag == false){
                        break;
                    }
                }
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
        int x;
        int y;
        CashRegistry registry;
        switch (side){
            case 1:
                while (true){
                    x = rand.nextInt(0, MAP_WIDTH);
                    y = 0;
                    boolean flag = false;
                    for (int i=0; i<usedPositions.size(); ++i){
                        if(usedPositions.get(i).x == x && usedPositions.get(i).y == y){
                            flag = true;
                            break;
                        }
                    }
                    if(flag == false){
                        break;
                    }
                }
                registry = new CashRegistry(x, y, "Cash Registry A", ID);
                return registry;
            case 2:
                while (true){
                    x = rand.nextInt(0, MAP_WIDTH);
                    y = MAP_HEIGHT;
                    boolean flag = false;
                    for (int i=0; i<usedPositions.size(); ++i){
                        if(usedPositions.get(i).x == x && usedPositions.get(i).y == y){
                            flag = true;
                            break;
                        }
                    }
                    if(flag == false){
                        break;
                    }
                }
                registry = new CashRegistry(x, y, "Cash Registry B", ID);
                return registry;
            case 3:
                while (true){
                    x = 0;
                    y = rand.nextInt(0, MAP_HEIGHT);
                    boolean flag = false;
                    for (int i=0; i<usedPositions.size(); ++i){
                        if(usedPositions.get(i).x == x && usedPositions.get(i).y == y){
                            flag = true;
                            break;
                        }
                    }
                    if(flag == false){
                        break;
                    }
                }
                registry = new CashRegistry(x, y, "Cash Registry C", ID);
                return registry;
            case 4:
                while (true){
                    x = MAP_WIDTH;
                    y = rand.nextInt(0, MAP_HEIGHT);
                    boolean flag = false;
                    for (int i=0; i<usedPositions.size(); ++i){
                        if(usedPositions.get(i).x == x && usedPositions.get(i).y == y){
                            flag = true;
                            break;
                        }
                    }
                    if(flag == false){
                        break;
                    }
                }
                registry = new CashRegistry(x, y, "Cash Registry D", ID);
                return registry;
            default:
                registry = new CashRegistry(1, 0, "Cash Registry E", ID);
                return registry;
        }
    }
}