package railroad_simulation.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RailRoadApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("RailRoadGUI.fxml"));
        primaryStage.setTitle("RailRoad Simulator");
        primaryStage.setScene(new Scene(root, 750, 520)); //перший параметр ширина, другий висота
        primaryStage.show();
    }
}
