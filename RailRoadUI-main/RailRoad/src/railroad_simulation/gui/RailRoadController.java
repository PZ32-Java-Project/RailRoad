package railroad_simulation.gui;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import railroad_simulation.models.Hall;

public class RailRoadController {
    // для автоматичного або ручного вводу радіо-бат

    @FXML
    Pane hall;

    Hall railRoad;

    @FXML
    public void initialize()
    {
        railRoad = new Hall();
        railRoad.initialize(5, 2, -1, -1, hall);
    }
}
