package ui.prestamo;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by ASUS on 17/03/2016.
 */
public class PrestamoHistoryDialog  extends Stage {

    private PrestamoTable table;

    public PrestamoHistoryDialog() {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);

        initComponents();
        createStage();
    }

    private void createStage() {
        VBox vBox = new VBox();
        vBox.getChildren().add(table);

        Scene scene = new Scene(vBox,350,350);
        scene.getStylesheets().add("/style.css");
        this.setScene(scene);
    }

    private void initComponents() {
        table = new PrestamoTable(200,200);
    }

}
