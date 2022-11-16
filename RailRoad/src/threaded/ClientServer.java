package threaded;

import javafx.application.Platform;
import models.CashRegistry;
import models.Hall;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;

import static shared.MyFileWritter.Write;

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
        if(hall.isTerminate()) {
            /*cashRegistry.getLine().getClients().clear();
            int k = cashRegistry.getLine().getClients().size();*/
            //this.stop();
        }
        //Edit for priority queue
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
                        if(hall.isTerminate()){
                            Write("ClientServer "+ cashRegistry.getId() +" has stopped");
                            System.out.println("ClientServer "+ cashRegistry.getId() +" has stopped");
                            return;
                        }
                        if(interval==-1) {
                            var random = new Random();
                            sleep(random.nextInt(5000)+10000);
                        }
                        else{
                            sleep(interval);
                        }
                        if(hall.isTerminate()){
                            Write("ClientServer "+ cashRegistry.getId() +" has stopped");
                            System.out.println("ClientServer "+ cashRegistry.getId() +" has stopped");
                            return;
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        lock.lock();
                        if(hall.isTerminate()) {
                            cashRegistry.setLine(null);
                        }
                        var line = cashRegistry.getLine();
                        var client = line.getClients().poll();
                        var pos = map.getPositions();
                        pos.remove(client);

                            //Dodo:    Зробити біг на вихід
                        Platform.runLater(() -> client.remove());
                        cashRegistry.updatedLineUI();
                        Write("client " + client.getName() + " served at cash registry :" + cashRegistry.getName());
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