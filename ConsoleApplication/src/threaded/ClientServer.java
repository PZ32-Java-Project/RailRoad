package threaded;

import managers.SeedingManager;
import models.CashRegistry;
import models.Hall;
import shared.Constants;

import java.util.Random;
import java.util.concurrent.locks.Lock;

import static shared.Constants.LINE_MAX_CLIENTS_COUNT;
import static shared.Constants.cashRegistriesCount;

public class ClientServer extends Thread{
    private CashRegistry cashRegistry;
    private Hall hall;
    private Lock lock;
    private int interval;
    public ClientServer (CashRegistry cashRegistry, Hall hall, Lock lock, int interval){
        this.cashRegistry=cashRegistry;
        this.hall=hall;
        this.lock=lock;
        this.interval=interval;
    }
    public void run(){
        var map = hall.getMap();
        //Edit for priority queue
        //Edit for technical pause
        while (!cashRegistry.isOnPause()) {
            var isNull = false;
            var isNotEmpty = false;
            try {
                lock.lock();
                isNull = cashRegistry.getLine().getClients() != null;
                isNotEmpty = cashRegistry.getLine().getClients().size() > 0;
            } finally {
                lock.unlock();
            }
            if (isNull){
                if (isNotEmpty) {
                    try {
                        if(interval==-1) {
                            var random = new Random();
                            //interval=random.nextInt(5000)+10000;
                            sleep(random.nextInt(5000)+10000);
                        }
                        else{
                            sleep(interval);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        lock.lock();
                        var line = cashRegistry.getLine();
                        var client = line.getClients().poll();
                        var pos = map.getPositions();
                        var res = pos.remove(client);
                        System.out.println("client " + client.getName() + " served at cash registry :" + cashRegistry.getName());
                    }
                    finally {
                        lock.unlock();
                    }
                }
            }
            try {
                    sleep(100);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
