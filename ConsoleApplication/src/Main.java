import models.Hall;

public class Main {
    public static void main(String[] args) {
        var hall = new Hall();
        hall.initialize(4,4,1000,5000); // hall.initialize(4,4,-1,-1); для рандому
        try {
            Thread.sleep(5000);
            hall.stop();
            Thread.sleep(5000);
            hall.initialize(4,4,1000,5000); // hall.initialize(4,4,-1,-1); для рандому
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
