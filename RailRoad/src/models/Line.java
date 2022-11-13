package models;

import managers.PriorityComparator;
import shared.Constants;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import static shared.Constants.cashRegistryWidth;
import static shared.Constants.clientSize;

public class Line {
    private int id;
    private Queue<Client> clients;
    private static PriorityComparator comparator = new PriorityComparator();
    public Line(int id){
        this.id = id;
        clients = new PriorityQueue<>(comparator);
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

    public void tryDelete(){
        clients.remove();
    }

}
