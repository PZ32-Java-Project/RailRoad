package models;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import abstractions.Position;
import shared.Global;
import threaded.ClientMover;
import views.CashRegistryView;

import static java.lang.Thread.sleep;
import static shared.Constants.*;

public class CashRegistry extends Position {
    protected String name;
    protected Line line;
    protected int id;
    protected boolean onPause;

    public CashRegistry(int x, int y, String name, int id) {
        super(x, y);
        this.name = name;
        this.id = id;
        line = new Line(id);
        onPause = false;
        var view = new CashRegistryView(this);
        view.updateUI();
    }
    public CashRegistry(int x, int y, String name, Line line) {
        super(x, y);
        this.name = name;
        this.line = line;
        var view = new CashRegistryView(this);
        view.updateUI();
    }

    public Line getLine() {
        return line;
    }
    public void setLine(Line line) {
        this.line = line;
    }
    public boolean isOnPause() {
        return onPause;
    }
    private ConcretePosition findVacant(int positionInLine){
        int y = (getY() + cashRegistryHeight / 2);
        int x;
        if(getX() == cashRegistryWidth){ // крива перевірка на ліву-праву касу
            x = (getX() + positionInLine * (clientSize * 3) + clientSize + cashRegistryWidth * 2);
        }
        else {
            x = (getX() - positionInLine * (clientSize * 3) - (clientSize + cashRegistryWidth));
        }
        return new ConcretePosition(x,y);
    }
    public void setOnPause(boolean onPause) {
        this.onPause = onPause;
    }
    public ConcretePosition findVacantPositionOnTail(){
        int lineSize = line.getClients().size();
        return findVacant(lineSize);
    }
    public void updatedLineUI(){

        try {
            line.getClients().stream().forEach(c->c.setStopMoving(true));
            sleep(7);
            line.getClients().stream().forEach(c->c.setStopMoving(false));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //  int lineSize = line.getClients().size();
        int i = 0;
        for (var client:line.getClients()){
            var targetPosition =  findVacant(i);
            var clientMover = new ClientMover(client, targetPosition, false);
            i++;
            clientMover.start();
        }
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}