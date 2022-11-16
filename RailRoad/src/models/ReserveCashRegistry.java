package models;

import abstractions.Position;
import javafx.scene.layout.Pane;
import views.ReserveCashRegistryView;

import java.util.List;

public class ReserveCashRegistry extends CashRegistry {
    private ReserveCashRegistryView view;

    public ReserveCashRegistry(int x, int y, String name, int id) {
        super(x, y, name, id);
        onPause = true;
        view = new ReserveCashRegistryView(this);
        view.updateUI();
    }

    public ReserveCashRegistryView getView() {
        return view;
    }
}