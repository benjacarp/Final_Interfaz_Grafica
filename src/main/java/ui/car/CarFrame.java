package ui.car;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by ASUS on 16/03/2016.
 */
public class CarFrame extends Stage{

    private CarTableModel table;
    private final CarFrameController controller;
    private BorderPane pane;

    public CarFrame() {
        this.controller = new CarFrameController(this);
        initComponents();
        createStage();
    }

    private void createStage() {
        pane = new BorderPane();
        VBox vbox = new VBox();
        vbox.getChildren().add(table);
        pane.setCenter(vbox);

        Scene scene = new Scene(pane,532,300);
        scene.getStylesheets().add("/style.css");
        this.setScene(scene);
    }

    private void initComponents() {
        table = new CarTableModel(300,300);
    }
}
