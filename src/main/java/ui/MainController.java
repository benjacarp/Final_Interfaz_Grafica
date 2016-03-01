package ui;

import javafx.stage.Stage;

/**
 * Created by benjamin.salas on 01/03/2016.
 */
public class MainController {

    private MainFrame view;

    public MainController(MainFrame mainFrame) {
        view = mainFrame;
    }

    public void buttonClicked() {
        System.out.println("Oooh do that again");
        this.view.getButton().setText("Click Again!");

        Stage stage = new FirstDialog();
        stage.show();
    }
}
