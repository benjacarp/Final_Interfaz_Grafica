package ui.prestamo;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Prestamo;

/**
 * Created by ASUS on 17/03/2016.
 */
public class PrestamoHistoryDialog  extends Stage {

    private PrestamoTable table;
    private TextField searchField;
    private Button btnDevolver;
    private Prestamo currentPrestamo;

    public PrestamoHistoryDialog() {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);

        initComponents();
        createStage();
    }

    private void createStage() {
        VBox vBox = new VBox();
        vBox.getChildren().add(searchField);
        vBox.getChildren().add(table);
        vBox.getChildren().add(btnDevolver);

        Scene scene = new Scene(vBox,350,350);
        scene.getStylesheets().add("/style.css");
        this.setScene(scene);
    }

    private void initComponents() {
        table = new PrestamoTable(200,200);
        table.getSelectionModel().selectedIndexProperty().addListener(e -> changeTableSelection());

        searchField = new TextField();

        btnDevolver = new Button("Devolver");
        btnDevolver.setOnAction(event -> devolver());
    }

    private void changeTableSelection() {
        this.currentPrestamo = table.getSelectionModel().getSelectedItem();
    }

    private void devolver() {
        Stage stage = new DevolucionDialog(currentPrestamo.getId());
        stage.show();
        this.close();
    }

}
