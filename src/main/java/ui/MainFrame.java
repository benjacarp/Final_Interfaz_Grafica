package ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by benjamin.salas on 01/03/2016.
 */
public class MainFrame extends Stage{

    private TextField textField;
    private Button button;
    private MainController controller;

    public MainFrame() {
        this.controller = new MainController(this);

        initComponents();

        createStage();
    }

    private void createStage() {
        StackPane layout = new StackPane();
        layout.getChildren().add(textField);
        layout.getChildren().add(button);

        Scene scene = new Scene(layout,300,250);
//        scene.getStylesheets().add("/style.css");
        scene.getStylesheets().add("/style.css");
        setScene(scene);
    }

    private void initComponents() {

        textField = new TextField();

        button = new Button();
        button.setText("Click me!");
        button.setOnAction(e -> controller.buttonClicked());
    }

    public Button getButton() {
        return button;
    }
}
