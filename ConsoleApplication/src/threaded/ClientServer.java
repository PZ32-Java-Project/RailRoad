package threaded;

import managers.SeedingManager;
import models.CashRegistry;
import models.Hall;
import shared.Constants;

import static shared.Constants.LINE_MAX_CLIENTS_COUNT;
import static shared.Constants.cashRegistriesCount;

public class ClientServer extends Thread{
    private CashRegistry cashRegistry;
    private Hall hall;
    public ClientServer (CashRegistry cashRegistry, Hall hall){
        this.cashRegistry=cashRegistry;
        this.hall=hall;
    }
    public void run(){
        var map = hall.getMap();
        //Edit for priority queue
        //Edit for technical pause
        while (!cashRegistry.isOnPause()){
            if(cashRegistry.getLine().getClients()!=null)
                if(cashRegistry.getLine().getClients().size()>0) {
                    try {
                        //sleep(Constants.cashRegistryServeTimeMax);
                        sleep(10000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    var line = cashRegistry.getLine();
                    var client  = line.getClients().poll();
                    var pos = map.getPositions();
                    var res = pos.remove(client);

                    System.out.println("client " + client.getName() +" served at cash registry :" +cashRegistry.getName());

                }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
