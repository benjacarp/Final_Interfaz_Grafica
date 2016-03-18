package ui.controller;

import javafx.stage.Stage;
import ui.car.CarFrame;
import ui.client.ClientFrame;
import ui.main.MainFrame;
import ui.prestamo.DevolucionDialog;
import ui.prestamo.PrestamoDialog;
import ui.prestamo.PrestamoHistoryDialog;

/**
 * Created by benjamin.salas on 01/03/2016.
 */
public class MainController {

    private MainFrame view;

    public MainController(MainFrame mainFrame) {
        view = mainFrame;
    }

    public void socioClick() {

        Stage stage =  new ClientFrame();
        stage.show();
        stage.setResizable(false);
    }

    public void carClick() {
        Stage stage = new CarFrame();
        stage.show();
        stage.setResizable(false);
    }

    public void devolucionClick() {
        Stage stage = new DevolucionDialog(0);
        stage.show();
        stage.setResizable(false);
    }

    public void prestamoClick() {
        Stage stage = new PrestamoHistoryDialog();
        stage.setResizable(false);
        stage.show();
    }

    public void nuevoPrestamoClick() {
        Stage stage = new PrestamoDialog(null);
        stage.show();
        stage.setResizable(false);
    }
}
