package views;

import abstractions.IViewable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.CashRegistry;
import models.Map;

import static shared.Constants.cashRegistryHeight;
import static shared.Constants.cashRegistryWidth;
import static shared.Global.pane;

public class CashRegistryView implements IViewable {
    private CashRegistry cashRegistry;
    private Rectangle rectangle;

    public CashRegistryView(CashRegistry cashRegistry) {
        this.cashRegistry = cashRegistry;
        rectangle = new Rectangle();
        pane.getChildren().add(rectangle);

        rectangle.setOnMouseClicked(mouseEvent -> {
            var reserveCashRegistry = Map.getInstance().getReserveCashRegistry();
            var isOnPause = cashRegistry.isOnPause();

            // Disable/enable cash registry
            cashRegistry.setOnPause(!isOnPause);
            updateUI();

            // Enable/disable reserve cash registry
            if (reserveCashRegistry.isOnPause() != isOnPause) {
                reserveCashRegistry.setOnPause(isOnPause);
                reserveCashRegistry.getView().updateUI();
            }
        });
    }

   public void updateUI(){
        rectangle.setHeight(cashRegistryHeight);
        rectangle.setWidth(cashRegistryWidth);
        rectangle.setTranslateX(cashRegistry.getX());
        rectangle.setTranslateY(cashRegistry.getY());
        if(!cashRegistry.isOnPause()) {
            rectangle.setFill(Color.GREEN);
            rectangle.setStroke(Color.GREEN);
        }else{
            rectangle.setFill(Color.ORANGE);
            rectangle.setStroke(Color.ORANGE);
        }
    }

   public void removeUI() {
        if (rectangle != null) {
            pane.getChildren().remove(rectangle);
        }
   }
}