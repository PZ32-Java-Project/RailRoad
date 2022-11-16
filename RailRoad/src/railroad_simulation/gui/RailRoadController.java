package railroad_simulation.gui;

import abstractions.Position;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import models.Client;
import models.Hall;
import shared.Global;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RailRoadController {
    @FXML
    Pane pane;
    @FXML
    Pane control;
    @FXML
    RadioButton auto;
    @FXML
    RadioButton manual;
    @FXML
    Slider cashSlider;
    @FXML
    Slider entranceSlider;
    @FXML
    Slider spawnSlider;
    @FXML
    Slider servingSlider;
    @FXML
    Label cashLabel;
    @FXML
    Label entranceLabel;
    @FXML
    Label spawnLabel;
    @FXML
    Label servingLabel;
    @FXML
    Button stopButton;
    @FXML
    Button setButton;
    @FXML
    TextArea clientInfoField;
    Hall hall;

    final ToggleGroup group = new ToggleGroup();
    @FXML
    public void initialize()
    {
        auto.setToggleGroup(group);
        manual.setToggleGroup(group);
        hall = new Hall();
        Global.pane = pane;
        servingLabel.setVisible(false);
        spawnLabel.setVisible(false);
        entranceLabel.setVisible(false);
        cashLabel.setVisible(false);
        cashSlider.setVisible(false);
        entranceSlider.setVisible(false);
        spawnSlider.setVisible(false);
        servingSlider.setVisible(false);
        stopButton.setVisible(false);
        setButton.setVisible(false);
        clientInfoField.setStyle("-fx-control-inner-background: rgb(255, 250, 232)");
    }
    @FXML
    public void modeClick(){
        Clear();
        pane.getChildren().clear();
        Global.pane = pane;
        stopButton.setVisible(true);
        if(auto.isSelected()){
            servingLabel.setVisible(false);
            spawnLabel.setVisible(false);
            entranceLabel.setVisible(false);
            cashLabel.setVisible(false);
            cashSlider.setVisible(false);
            entranceSlider.setVisible(false);
            spawnSlider.setVisible(false);
            servingSlider.setVisible(false);
            setButton.setVisible(false);
            stopButton.setLayoutX(89);
            stopButton.setLayoutY(111);
            hall.initialize(3, 3, 500, -1);
        }else if(manual.isSelected()){
            servingLabel.setVisible(true);
            spawnLabel.setVisible(true);
            entranceLabel.setVisible(true);
            cashLabel.setVisible(true);
            cashSlider.setVisible(true);
            entranceSlider.setVisible(true);
            spawnSlider.setVisible(true);
            servingSlider.setVisible(true);
            setButton.setVisible(true);
            stopButton.setLayoutX(117);
            stopButton.setLayoutY(334);
            setButton.setLayoutX(58);
            setButton.setLayoutY(334);
        }
    }
    @FXML
    public void setManualModeSettings(){
        Clear();
        pane.getChildren().clear();
        Global.pane = pane;
        hall.initialize((int)cashSlider.getValue(),
                (int)entranceSlider.getValue(),
                (int)(spawnSlider.getValue() * 100),
                (int)(servingSlider.getValue() * 1000));
    }

    @FXML
    public void Clear(){
        hall.stop();
    }
    @FXML
    public void getClientInfoMouseMoved(){
        clientInfoField.setText("");
        pane.setOnMouseMoved(e -> {
            hall.getMap().getClients()
                    .stream()
                    .peek(client ->
                            ((Client) client)
                            .getCl()
                            .getClientCircle()
                            .setOnMouseEntered( en -> {
                                clientInfoField.setText(client.toString());
                            })).collect(Collectors.toList());
        });
    }
}