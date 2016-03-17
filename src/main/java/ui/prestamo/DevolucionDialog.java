package ui.prestamo;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by ASUS on 17/03/2016.
 */
public class DevolucionDialog extends Stage {

    private TextField prestamoNumber;
    private Button btnDevolucion;

    public DevolucionDialog() {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);

        initComponents();
        createStage();
    }

    private void createStage() {
        VBox vBox = new VBox();

        vBox.getChildren().add(new Label("Nro. de Prestamo"));
        vBox.getChildren().add(prestamoNumber);
        vBox.getChildren().add(btnDevolucion);

        Scene scene = new Scene(vBox,300,300);
        scene.getStylesheets().add("/style.css");
        this.setScene(scene);
    }

    private void initComponents() {
        prestamoNumber = new TextField();
        btnDevolucion = new Button("Devolver");
    }
}
