package railroad_simulation.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import models.Hall;

public class RailRoadController {
    // для автоматичного або ручного вводу радіо-бат
    @FXML
    Pane pane;
    Hall hall;

    @FXML
    public void initialize()
    {
        // Platform.setImplicitExit(false);
        hall = new Hall(pane);
        hall.initialize(5, 2, -1, -1);
    }
}
