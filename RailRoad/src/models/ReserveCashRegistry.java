package models;

import abstractions.Position;
import javafx.scene.layout.Pane;

import java.util.List;

public class ReserveCashRegistry extends CashRegistry{
    private List<Position> cashRegistries;
    //Play thread in constructor
    private void run(){
        int amountOfCashReg = cashRegistries.size();
        while (true) {
            if(onPause) {
                for (var cash : cashRegistries) {
                    var tmp = (CashRegistry) cash;
                    if (tmp.isOnPause()) {
                        onPause = false;
                        addFromLine(((CashRegistry) cash).line);
                    }
                }
            }
            else{
                int amountInProcess = 0;
                for (var cash : cashRegistries) {
                    var tmp = (CashRegistry) cash;
                    if (tmp.isOnPause()) {
                        onPause = false;
                        addFromLine(((CashRegistry) cash).line);
                    }
                    else {
                        amountInProcess++;
                    }
                }
                if (amountInProcess == amountOfCashReg)
                    onPause = true;
            }
        }
    }

    //Adding from crashed line clients and then delete them from
    private void addFromLine(Line line){
        for (var item: line.getClients()) {
            if(this.line.tryAdd(item)){
                line.tryDelete();
            }
        }
    }

    public ReserveCashRegistry(int x, int y, String name, int ID) {
        super(x, y, name, ID);
        Map map = Map.getInstance();
        cashRegistries = map.getCashRegistries();
        onPause = true;
        ReserveCashRegistry reserveCashRegistry = new ReserveCashRegistry(x, y, name, ID);
    }

    public ReserveCashRegistry(int x, int y, String name, Line line) {
        super(x, y, name, line);
        Map map = Map.getInstance();
        cashRegistries = map.getCashRegistries();
        onPause = true;
    }
}