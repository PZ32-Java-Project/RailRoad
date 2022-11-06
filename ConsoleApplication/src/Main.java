import managers.SeedingManager;
import models.Client;
import models.Entrance;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        SeedingManager manager = new SeedingManager();
        ArrayList<Client> clients = new ArrayList<>();
        ArrayList<Entrance> entrances = (ArrayList<Entrance>)manager.generateEntrances(4);
        for(int i=0; i< 10; ++i){
            Client client = manager.generateClient(clients, entrances);
            clients.add(client);
            System.out.println("Name: " + clients.get(i).getName() + " Surname: " + clients.get(i).getSurname() +
                    " ID: " + clients.get(i).getID());
        }
    }
}
