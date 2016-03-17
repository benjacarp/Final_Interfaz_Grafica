package ui.main;

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

    public void devolucionClick() {
        System.out.println("Devolucion");
    }

    public void prestamoClick() {
        System.out.println("ver Prestamos");
    }

    public void nuevoPrestamoClick() {
        System.out.println("Nuevo Prestamo");
    }
}
