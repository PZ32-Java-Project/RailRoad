package models;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import abstractions.Position;
import threaded.ClientMover;

import static shared.Constants.*;
import static shared.MyFileWritter.Write;

public class CashRegistry extends Position {
    private Rectangle cash_reg;
    protected String name;
    protected Line line;
    protected int id;
    protected boolean onPause;
    public CashRegistry(int x, int y, String name, int id, Pane hall) {
        super(x, y);
        this.name = name;
        this.id = id;
        line = new Line(id);
        onPause = false;
        this.cash_reg = new Rectangle();
        cash_reg.setHeight(cashRegistryHeight);
        cash_reg.setWidth(cashRegistryWidth);
        cash_reg.setStroke(Color.GREEN);
        cash_reg.setTranslateX(x);
        cash_reg.setTranslateY(y);
        cash_reg.setFill(Color.GREEN);
        hall.getChildren().add(cash_reg);
    }
    public CashRegistry(int x, int y, String name, Line line) {
        super(x, y);
        this.name = name;
        this.line = line;
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
        if (this.onPause) {
            cash_reg.setFill(Color.RED);
        }
        else {
            cash_reg.setFill(Color.GREEN);
        }
    }
<<<<<<< HEAD
    public ConcretePosition findVacantPosition (){

        int lineSize = this.line.getClients().size();
        int y = (this.getY());
        int x;
        if(this.getX() == cashRegistryWidth){ // крива перевірка на ліву-праву касу
            x= (getX() + lineSize * (clientSize * 2 + 2) + clientSize + cashRegistryWidth);
        }
        else {
            x = (getX() - lineSize * (clientSize * 2 +2) - (clientSize + cashRegistryWidth));
        }
        Write(this.name+": "+ x+", "+y);
        return new ConcretePosition(x,y);
    }
    public void moveLine(){
        int y = (this.getY());
        int offsetX;
        if(this.getX() == cashRegistryWidth){ // крива перевірка на ліву-праву касу
            offsetX = clientSize * 2 + 2 ;
        }
        else {
            offsetX = - clientSize * 2 - 2;
        }
       Write(this.name+": "+ offsetX +", "+y);
        var clientsList = line.getClients();


        for (var client:clientsList) {
            var targetPosition = new ConcretePosition(client.getX() - offsetX, client.getY());
=======
    public ConcretePosition findVacantPositionOnTail(){
        int lineSize = line.getClients().size();
        return findVacant(lineSize);
    }
    public void updatetLineUI(){
      //  int lineSize = line.getClients().size();
        int i=0;
        for (var client:line.getClients()){
            var targetPosition =  findVacant(i);
>>>>>>> 1946d9b665b17c9f885d842160c09098910ab45f
            var clientMover  = new ClientMover(client, targetPosition);
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
