package threaded;

import javafx.application.Platform;
import models.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;

import static shared.MyFileWritter.Write;

public class ClientServer extends Thread{
    private CashRegistry cashRegistry;
    private Exit exit;
    private Hall hall;
    private Lock lock;
    private int interval;
    public ClientServer (CashRegistry cashRegistry,  Lock lock, int interval){
        this.cashRegistry=cashRegistry;

        this.lock=lock;
        this.interval=interval;
        this.exit = Map.getInstance().getExit();
    }
    public void run(){
        var hall = Hall.getInstance();
        //Edit for priority queue
        while (true) {
            var lol =  (CashRegistry)(hall.getMap().getCashRegistries().get(0));
            if(cashRegistry.getName()==lol.getName())
            {
                var a= 10;
            }
            if(!cashRegistry.isOnPause() || (cashRegistry instanceof ReserveCashRegistry)) {
                var isNull = false;
                var isNotEmpty = false;
                try {
                    lock.lock();
                    isNull = cashRegistry.getLine().getClients() != null;
                    isNotEmpty = cashRegistry.getLine().getClients().size() > 0;
                } finally {
                    lock.unlock();
                }
                if (isNull) {
                    if (isNotEmpty) {
                        var line = cashRegistry.getLine();
                        var client = line.getClients().peek();
                        try {
                            var ticketsCount = cashRegistry.getLine().getClients().peek().getTicketsCount();
                            if (hall.isTerminate()) {
                                Write("ClientServer " + cashRegistry.getId() + " has stopped");
                                System.out.println("ClientServer " + cashRegistry.getId() + " has stopped");
                                return;
                            }

                            if (interval == -1) {
                                var random = new Random();
                                sleep((random.nextInt(2500) + 5000) * ticketsCount);
                            } else {
                                sleep(interval);
                            }
                            if (hall.isTerminate()) {
                                Write("ClientServer " + cashRegistry.getId() + " has stopped");
                                System.out.println("ClientServer " + cashRegistry.getId() + " has stopped");
                                return;
                            }
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            lock.lock();
                            if (hall.isTerminate()) {
                                cashRegistry.setLine(null);
                            }

                            // Retrieve client
                            //  var line = cashRegistry.getLine();
                            //  client = line.getClients().poll();
                            line.getClients().remove(client);
                            cashRegistry.updatedLineUI();

                            // Move to an exit
                            var clientMover = new ClientMover(client, exit, true);
                            try {
                                client.setStopMoving(true);
                                sleep(7);
                                client.setStopMoving(false);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            clientMover.start();

                            Write("client " + client.getName() + " served at cash registry :" + cashRegistry.getName());
                            System.out.println("client " + client.getName() + " served at cash registry :" + cashRegistry.getName());
                        } finally {
                            lock.unlock();
                        }
                    }
                }
                if(!isNull){
                    var a= 10;
                }
                try {
                    sleep(100);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                var clients = cashRegistry.getLine().getClients();
                var reserve = hall.getMap().getReserveCashRegistry();
                reserve.getLine().getClients().addAll(clients);
                reserve.updatedLineUI();
                cashRegistry.updatedLineUI();
                cashRegistry.getLine().getClients().clear();
            }
            }
        }
    }
