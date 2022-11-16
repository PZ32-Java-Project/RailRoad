package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.CashRegistry;
import shared.Global;

import static shared.Constants.cashRegistryHeight;
import static shared.Constants.cashRegistryWidth;
import static shared.Global.pane;

public class CashRegistryView {
    private static Rectangle cash_reg;

    public static Rectangle getCash_reg() {
        return cash_reg;
    }

    public static void setCash_reg(Rectangle cash_reg) {
        CashRegistryView.cash_reg = cash_reg;
    }
    public static void updateCashRegistryUI(CashRegistry cr){
        cash_reg = new Rectangle();
        cash_reg.setHeight(cashRegistryHeight);
        cash_reg.setWidth(cashRegistryWidth);
        cash_reg.setTranslateX(cr.getX());
        cash_reg.setTranslateY(cr.getY());
        if(!cr.isOnPause()) {
            cash_reg.setFill(Color.GREEN);
            cash_reg.setStroke(Color.GREEN);
        }else{
            cash_reg.setFill(Color.ORANGE);
            cash_reg.setStroke(Color.ORANGE);
        }
        pane.getChildren().add(cash_reg);
    }
}