package ui.client;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jiconfont.icons.FontAwesome;
import ui.MainFrame;
import ui.utils.UIConstants;

/**
 * Created by ASUS on 03/03/2016.
 */
public class ClientFrame extends Stage {

    private Button addButton;
    private Button modifyButton;
    private Button deleteButton;

    private ClientTableModel table;

    private BorderPane pane;

    private ClientFrameController controller;

    public ClientFrame() {

        this.controller = new ClientFrameController(this);

        initComponents();

        createStage();
    }

    private void createStage() {
        pane = new BorderPane();

        pane.setTop(new Label("Clientes"));

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(15,15,15,15));
        vBox.getChildren().add(table);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15,15,15,15));
        grid.setVgap(10);
        grid.setHgap(70);
        grid.setPrefWidth(430);
        GridPane.setConstraints(addButton,0,0);
        GridPane.setConstraints(modifyButton,1,0);
        GridPane.setConstraints(deleteButton,2,0);

        grid.getChildren().addAll(addButton,modifyButton,deleteButton);
        vBox.getChildren().add(grid);

        Scene scene = new Scene(vBox,432,300);
        scene.getStylesheets().add("/style.css");
        this.setScene(scene);

    }

    private void initComponents() {

        addButton = new Button();
        addButton.setText("Agregar");
        addButton.setGraphic(MainFrame.addIcon(FontAwesome.PLUS, UIConstants.ICON_STANDAR_SIZE, Color.WHITE));
        addButton.setContentDisplay(ContentDisplay.TOP);
        addButton.setOnAction(e -> controller.addButtonClicked());
        addButton.setPrefWidth(80);
        addButton.setPrefHeight(50);
        addButton.setPadding(new Insets(10,10,10,10));

        modifyButton = new Button();
        modifyButton.setText("Modificar");
        modifyButton.setGraphic(MainFrame.addIcon(FontAwesome.PENCIL, UIConstants.ICON_STANDAR_SIZE, Color.WHITE));
        modifyButton.setContentDisplay(ContentDisplay.TOP);
        modifyButton.setOnAction(e -> controller.addButtonClicked());
        modifyButton.setPrefWidth(80);
        modifyButton.setPrefHeight(50);
        modifyButton.setPadding(new Insets(10,10,10,10));

        deleteButton = new Button();
        deleteButton.setText("Eliminar");
        deleteButton.setGraphic(MainFrame.addIcon(FontAwesome.TRASH_O, UIConstants.ICON_STANDAR_SIZE, Color.WHITE));
        deleteButton.setContentDisplay(ContentDisplay.TOP);
        deleteButton.setOnAction(e -> controller.deleteButtonClicked());
        deleteButton.setPrefWidth(80);
        deleteButton.setPrefHeight(50);
        deleteButton.setPadding(new Insets(10,10,10,10));

        table = new ClientTableModel(400,200);

    }

    public ClientTableModel getTable() {
        return table;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }
}
