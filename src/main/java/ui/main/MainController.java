package ui.main;

import javafx.stage.Stage;
import ui.car.CarFrame;
import ui.client.ClientFrame;
import ui.prestamo.DevolucionDialog;
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

        Stage stage = new ClientFrame();
        stage.show();
    }

    public void carClick() {
        Stage stage = new CarFrame();
        stage.show();
    }

    public void devolucionClick() {
        Stage stage = new DevolucionDialog(0);
        stage.show();
    }

    public void prestamoClick() {
        Stage stage = new PrestamoHistoryDialog();
        stage.show();
    }

    public void nuevoPrestamoClick() {
        System.out.println("Nuevo Prestamo");
    }
}
