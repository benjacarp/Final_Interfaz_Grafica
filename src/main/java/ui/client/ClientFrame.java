package ui.client;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jiconfont.icons.FontAwesome;
import model.Client;
import ui.MainFrame;
import ui.utils.UIConstants;

/**
 * Created by ASUS on 03/03/2016.
 */
public class ClientFrame extends Stage {

    private Button addButton;
    private Button modifyButton;
    private Button deleteButton;

    private Label displayName;
    private SimpleStringProperty name;

    private ClientTableModel table;
    private Client currentClient;

    private BorderPane pane;

    private ClientFrameController controller;
    private Image image;
    private ImageView imageView;
    private TextField searchField;
    private Button searchBtn;

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
        HBox hBox = new HBox();
        hBox.getChildren().add(this.searchField);
        hBox.getChildren().add(this.searchBtn);
        vBox.getChildren().add(hBox);

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

        pane.setCenter(vBox);

        /////////////rigth panel
        VBox rigth = new VBox();
        vBox.setPadding(new Insets(15,15,15,15));
        imageView = new ImageView();
        image = new Image("/default image small.png");
        imageView.setImage(image);
        rigth.getChildren().add(imageView);

        rigth.getChildren().add(displayName);

        pane.setRight(rigth);

        Scene scene = new Scene(pane,432,300);
        scene.getStylesheets().add("/style.css");
        this.setScene(scene);

    }

    private void initComponents() {

        displayName = new Label();
        displayName.setText("Nombre");

        this.searchField = new TextField();
        this.searchField.setMinWidth(320);
        this.searchField.setPrefWidth(320);
        this.searchField.setOnKeyPressed(e -> controller.sensitiveSearch(this.searchField.getText()));

        this.searchBtn = new Button("Buscar");
        this.searchBtn.setMinWidth(80);
        this.searchBtn.setPrefWidth(80);
        this.searchBtn.setOnAction(e -> this.controller.search(searchField.getText()));

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
        modifyButton.setOnAction(e -> controller.checkClient());
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
        table.getSelectionModel().selectedIndexProperty().addListener(e -> controller.changeTableSelection());
        /*table.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                loadPhoto();
            }
        });*/
    }

    private void loadPhoto() {
        String name = table.getSelectionModel().getSelectedItem().getName();
        displayName.setText(name);
    }

    public ClientTableModel getTable() {
        return table;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Label getDisplayName() {
        return displayName;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Client getCurrentClient() {
        return currentClient;
    }
}
