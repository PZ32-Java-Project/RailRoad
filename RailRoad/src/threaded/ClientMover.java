package threaded;

import abstractions.Position;
import javafx.application.Platform;

import models.Client;

import static shared.MyFileWritter.Write;

public class ClientMover extends Thread{
    Client client;
    Position currentPosition;
    Position targetPosition;

    public ClientMover (Client client, Position targetPosition) {
        this.client = client;
        this.currentPosition = client;
        this.targetPosition = targetPosition;
    }

    public void run() {
        while (!currentPosition.isPosition(targetPosition)) {
            // Make one step towards target position
            makeStep();
            Platform.runLater(() -> client.updateUI());

            // Sleep 0.005 second
            try {
                sleep(5);
            } catch (InterruptedException e) {
                Write("Error while trying to move a client: ");
                System.out.println("Error while trying to move a client: ");
                e.printStackTrace();
            }
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
