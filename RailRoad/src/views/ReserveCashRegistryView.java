package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import abstractions.IViewable;
import models.CashRegistry;

import static shared.Constants.cashRegistryHeight;
import static shared.Constants.cashRegistryWidth;
import static shared.Global.pane;

public class ReserveCashRegistryView implements IViewable {
    private CashRegistry cashRegistry;
    private Rectangle rectangle;

    public ReserveCashRegistryView(CashRegistry cashRegistry) {
        this.cashRegistry = cashRegistry;
        rectangle = new Rectangle();
        pane.getChildren().add(rectangle);
    }

    public void updateUI(){
        rectangle.setHeight(cashRegistryHeight);
        rectangle.setWidth(cashRegistryWidth);
        rectangle.setTranslateX(cashRegistry.getX());
        rectangle.setTranslateY(cashRegistry.getY());
        if (!cashRegistry.isOnPause()) {
            rectangle.setFill(Color.BLUE);
            rectangle.setStroke(Color.BLUE);
        } else {
            rectangle.setFill(Color.RED);
            rectangle.setStroke(Color.RED);
        }
    }

    public void removeUI() {
        if (rectangle != null) {
            pane.getChildren().remove(rectangle);
        }
    }
}
