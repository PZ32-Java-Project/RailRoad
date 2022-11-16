package models;

import abstractions.Position;
import views.ExitView;

public class Exit extends Position {
    private ExitView view;

    public Exit(int x, int y) {
        super(x, y);
        initializeView();
    }

    private void initializeView() {
        view = new ExitView(this);
        view.addUi();
    }
}
