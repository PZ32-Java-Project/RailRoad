package threaded;

import abstractions.Position;

public class ClientMover extends Thread{
    Position targetPosition;
    public ClientMover (Position targetPosition){
        this.targetPosition =targetPosition;
    }
    public void run(){
        while(true){

        }
    }
}
