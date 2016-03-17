package ui.prestamo;

import exception.DIGAppException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Car;
import service.CarService;
import ui.client.ComboClient;

/**
 * Created by ASUS on 17/03/2016.
 */
public class PrestamoDialog extends Stage{

    private Car car;
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
        vBox.getChildren().add(comboClient);
        vBox.getChildren().add(btnConfirm);

        Scene scene = new Scene(vBox,300,300);
        scene.getStylesheets().add("/style.css");
        this.setScene(scene);
    }

    private void initComponents() {
        lblCar = new Label("");
        lblCar.setText(car.toString());

        btnConfirm = new Button("Confirmar Prestamo");
        btnConfirm.setOnAction(event -> confirmPrestamo());

        comboClient = new ComboClient();
    }

    private void confirmPrestamo() {
        System.out.println("Confirmado");
        car.setAvailable(false);
        try {
            CarService.getInstance().update(car);
        } catch (DIGAppException e) {
            e.printStackTrace();
        }
        this.close();
    }
}
