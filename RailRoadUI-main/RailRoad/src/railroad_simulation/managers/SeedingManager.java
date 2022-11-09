package railroad_simulation.managers;

import javafx.scene.layout.Pane;
import railroad_simulation.abstractions.ISeedingManager;
import railroad_simulation.abstractions.Position;
import railroad_simulation.models.CashRegistry;
import railroad_simulation.models.Client;
import railroad_simulation.models.Entrance;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static railroad_simulation.shared.Constants.*;

// TODO: create some seed data, add factory pattern
public class SeedingManager implements ISeedingManager {
    private ArrayList<Point> usedPositions;
    public SeedingManager(){
        usedPositions = new ArrayList<>();
    }
    public Client generateClient(List<Position> clients, List<Position> entrances, Pane hall) {
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
                client = new Client((Entrance) entrances.get(chosenEntrance), chosenID, names[chosenName], surnames[chosenSurname], hall);

                break;
            }
        }
        return client;
    }

    public List<Entrance> generateEntrances(int entrancesCount, Pane hall) {
        List<Entrance> entrances = new ArrayList<>();
        if(entrancesCount > 4){
            entrancesCount = 4;
        }
        for (int i = 0; i < entrancesCount; ++i) {
            entrances.add(generateEntrance(i, hall));
        }
        return entrances;
    }

    private Entrance generateEntrance(int side, Pane hall) {
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
                entrance = new Entrance(x, y, "Entrance A", hall);
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
                entrance = new Entrance(x, y, "Entrance B", hall);
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
                entrance = new Entrance(x, y, "Entrance C", hall);
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
                entrance = new Entrance(x, y, "Entrance D", hall);
                return entrance;
            default:
                entrance = new Entrance(0, 0, "Entrance E", hall);
                usedPositions.add(new Point(0, 0));
                return entrance;
        }
    }

    public List<CashRegistry> generateCashRegistries(int cashRegistriesCount, Pane hall) {
        List<CashRegistry> cashRegistries = new ArrayList<>();
        if(cashRegistriesCount > 4){
            cashRegistriesCount = 4;
        }
        for (int i = 0; i < cashRegistriesCount; ++i) {
            cashRegistries.add(generateCashRegistry(i, (i+1), hall));
        }
        return cashRegistries;
    }

    private CashRegistry generateCashRegistry(int side, int ID, Pane hall) {
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
                registry = new CashRegistry(x, y, "Cash Registry A", ID, hall);
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
                registry = new CashRegistry(x, y, "Cash Registry B", ID, hall);
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
                registry = new CashRegistry(x, y, "Cash Registry C", ID, hall);
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
                registry = new CashRegistry(x, y, "Cash Registry D", ID, hall);
                return registry;
            default:
                registry = new CashRegistry(1, 0, "Cash Registry E", ID, hall);
                return registry;
        }
    }
}