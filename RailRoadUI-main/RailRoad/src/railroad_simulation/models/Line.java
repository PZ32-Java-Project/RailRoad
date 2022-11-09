package railroad_simulation.models;

import railroad_simulation.shared.Constants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Line {
    private Queue<Client> clients;
    public Line(){
        clients = new LinkedList<>();
    }
    public boolean tryAdd(Client client) {
        // If line is maxed
        if (clients.size() == Constants.LINE_MAX_CLIENTS_COUNT) {
            return false;
        }
        clients.add(client);
        return true;
    }
    public Queue<Client> getClients() {
        return clients;
    }

    public void setClients(Queue<Client> clients) {
        this.clients = clients;
    }
}
