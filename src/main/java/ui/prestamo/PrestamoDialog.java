package ui.prestamo;

import exception.DIGAppException;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Car;
import model.Prestamo;
import service.CarService;
import service.PrestamoService;
import ui.client.ComboClient;

/**
 * Created by ASUS on 17/03/2016.
 */
public class PrestamoDialog extends Stage{

    private Car car;
    private Button btnSearch;
    private ComboClient comboClient;
    private Label lblCar;
    private Button btnConfirm;

    public PrestamoDialog(Car car) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);

        this.car = car;
        initComponents();
        createStage();
    }

    private void createStage() {
        VBox vBox = new VBox();
        vBox.getChildren().add(lblCar);
        vBox.getChildren().add(btnSearch);
        vBox.getChildren().add(comboClient);
        vBox.getChildren().add(btnConfirm);

        Scene scene = new Scene(vBox,300,300);
        scene.getStylesheets().add("/style.css");
        this.setScene(scene);
    }

    private void initComponents() {
        lblCar = new Label("");
        lblCar.setText(car.toString());
        btnSearch = new Button("...");
        btnSearch.setOnAction(event -> btnSearchClick());

        btnConfirm = new Button("Confirmar Prestamo");
        btnConfirm.setOnAction(event -> confirmPrestamo());

        comboClient = new ComboClient();
    }

    private void btnSearchClick() {
        System.out.println("select car...");
    }

    private void confirmPrestamo() {
        System.out.println("Confirmado");

        Prestamo prestamo = new Prestamo();
        prestamo.setCar(car);
        prestamo.setClient(comboClient.getSelectedClient());
        prestamo.setActive(true);

        try {
            PrestamoService.getInstance().save(prestamo);
        } catch (DIGAppException e) {
            e.printStackTrace();
        }

        car.setAvailable(false);
        try {
            CarService.getInstance().update(car);
        } catch (DIGAppException e) {
            e.printStackTrace();
        }
        showAlertNewPrestamo(prestamo);
        this.close();
    }

    private void showAlertNewPrestamo(Prestamo prestamo) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nuevo prestamo");
        alert.setHeaderText("Se creó un nuevo prestamo");
        alert.setContentText("Nro de prestamo: " + prestamo.getId() +
                "\n" + "Client: " + prestamo.getClient().toString() +
                "\n" + prestamo.getCar().toString());
        alert.showAndWait();
    }
}
