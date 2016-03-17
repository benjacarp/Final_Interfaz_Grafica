package ui;

import javafx.stage.Stage;
import ui.car.CarFrame;
import ui.client.ClientFrame;

/**
 * Created by benjamin.salas on 01/03/2016.
 */
public class MainController {

    private MainFrame view;

    public MainController(MainFrame mainFrame) {
        view = mainFrame;
    }

    public void socioClick() {

        Stage stage = new ClientFrame();
        stage.show();
    }

    public void carClick() {
        Stage stage = new CarFrame();
        stage.show();
    }
}
