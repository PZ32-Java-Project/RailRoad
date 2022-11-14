package managers;

import models.Client;

import java.util.Comparator;

public class PriorityComparator implements Comparator<Client> {
    @Override
    public int compare(Client c1, Client c2) {
        return c1.getClientType().compareTo(c2.getClientType());
    }
}
