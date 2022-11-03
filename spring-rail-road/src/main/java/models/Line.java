package models;

import shared.Constants;

import java.util.Queue;

public class Line {
    private Queue<Client> clients;

    public boolean tryAdd(Client client) {
        // If line is maxed
        if (clients.size() == Constants.LINE_MAX_CLIENTS_COUNT) {
            return false;
        }

        clients.add(client);
        return true;
    }
}
