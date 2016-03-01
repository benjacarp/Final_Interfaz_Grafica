package ui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by benjamin.salas on 01/03/2016.
 */
public class FirstDialog extends Stage {

    private Label welcome;

    public FirstDialog() {
        this.initStyle(StageStyle.UTILITY);                // esto imita el comportamiento
        this.initModality(Modality.APPLICATION_MODAL);

        initComponents();

        createStage();
    }

    private void createStage() {
        StackPane layout = new StackPane();
        layout.getChildren().add(welcome);

        Scene scene = new Scene(layout,200,250);
//        scene.getStylesheets().add("/style.css");
        scene.getStylesheets().add("/style.css");
        setScene(scene);
    }

    private void initComponents() {
        welcome = new Label("Bienvenido!");
    }
}
