package threaded;

import abstractions.Position;
import javafx.application.Platform;

import models.Client;
import models.Hall;
import models.Map;
import shared.Global;
import views.ClientView;

import static shared.Global.pane;
import static shared.MyFileWritter.Write;

public class ClientMover extends Thread{
    private Client client;
    private Position currentPosition;
    private Position targetPosition;
    private boolean isRemoveOnArrival;

    public ClientMover (Client client, Position targetPosition, boolean isRemoveOnArrival) {
        this.client = client;
        this.currentPosition = client;
        this.targetPosition = targetPosition;
        this.isRemoveOnArrival = isRemoveOnArrival;
    }

    public void run() {
        var hall = Hall.getInstance();
        while (!currentPosition.isPosition(targetPosition)) {
            // Make one step towards target position
            makeStep();
            Platform.runLater(() -> client.updateUI());

            // Sleep 0.005 second
            try {
                if(hall.isTerminate()){
                    Platform.runLater(() ->pane.getChildren().clear());
                    return;
                }
                if(client.isStopMoving()){
                    return;
                }
                sleep(5);
                if(hall.isTerminate()){
                    Platform.runLater(() ->pane.getChildren().clear());
                    return;
                }
                if(client.isStopMoving()){
                    return;
                }
            } catch (InterruptedException e) {
                Write("Error while trying to move a client: ");
                System.out.println("Error while trying to move a client: ");
                e.printStackTrace();
            }
        }

        if (isRemoveOnArrival) {
            Platform.runLater(() -> client.remove());
        }
    }

    private void makeStep() {
        // Calculate x
        int x = currentPosition.getX();
        int targetX = targetPosition.getX();
        if (x != targetX) {
            if (targetX - x > 0) {
                ++x;
            }
            else {
                --x;
            }
        }

        // Calculate y
        int y = currentPosition.getY();
        int targetY = targetPosition.getY();
        if (y != targetY) {
            if (targetY - y > 0) {
                ++y;
            }
            else {
                --y;
            }
        }

        // Set coordinates
        currentPosition.setX(x);
        currentPosition.setY(y);
    }
}