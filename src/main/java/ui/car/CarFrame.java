package ui.car;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jiconfont.icons.FontAwesome;
import ui.controller.CarFrameController;
import ui.main.MainFrame;
import ui.utils.UIConstants;

/**
 * Created by ASUS on 16/03/2016.
 */
public class CarFrame extends Stage{

    private CarTableModel table;
    private final CarFrameController controller;
    private BorderPane pane;
    private Button btnPrestamo;

    private ImageView imageView;
    private Button btnUpdate;
    private Button btnDelete;

    private TextField searchField;
    private Button btnNew;

    private Image image;
    //panel info
    private Label lblMarca;
    private Label lblPatente;
    private Label lblAvailability;

    public CarFrame() {
        this.controller = new CarFrameController(this);
        initComponents();
        createStage();
    }

    private void createStage() {
        pane = new BorderPane();
        VBox centerVBox = new VBox();
        centerVBox.getChildren().add(hBoxCreator(searchField,btnNew));
        centerVBox.getChildren().add(table);
        VBox.setMargin(btnPrestamo,   new Insets(10,10,10,130));//top,rigth,bot,left
        centerVBox.getChildren().add(btnPrestamo);
        pane.setCenter(centerVBox);

        VBox rightVBox = new VBox();
        rightVBox.getChildren().add(imageView);
        rightVBox.getChildren().add(hBoxCreator(new Label("Marca: "),lblMarca));
        rightVBox.getChildren().add(hBoxCreator(new Label("Patente: "),lblPatente));
        rightVBox.getChildren().add(lblAvailability);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15,15,15,30));
        grid.setVgap(10);
        grid.setHgap(40);
        GridPane.setConstraints(btnUpdate,0,0);
        GridPane.setConstraints(btnDelete,1,0);
        grid.getChildren().addAll(btnUpdate,btnDelete);
        rightVBox.getChildren().add(grid);
        pane.setRight(rightVBox);

        Scene scene = new Scene(pane,550,300);
        scene.getStylesheets().add("/style.css");
        this.setScene(scene);
    }

    private HBox hBoxCreator(Node... nodes) {

        HBox hbox = new HBox();
        hbox.getChildren().addAll(nodes);

        return hbox;
    }

    private void initComponents() {

        searchField = new TextField();
        searchField.setMinWidth(250);
        btnNew = new Button("New");
        btnNew.setGraphic(MainFrame.addIcon(FontAwesome.PLUS, UIConstants.ICON_STANDAR_SIZE, Color.WHITE));
        btnNew.setOnAction(event -> controller.newClicked());
        btnNew.setMinWidth(50);

        imageView = new ImageView();
        imageView.setFitHeight(150);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(false);
        image = new Image("/default_car.jpg");
        imageView.setImage(image);

        //info
        lblMarca = new Label("");
        lblPatente = new Label("");
        lblAvailability = new Label("");

        //edit buttons
        btnUpdate = new Button("Update");
        btnUpdate.setOnAction(e -> controller.updateClick());
        btnDelete = new Button("Delete");
        btnDelete.setOnAction(e -> controller.deleteClick());

        btnPrestamo = new Button("Nuevo Prestamo");
        btnPrestamo.setPrefWidth(100);
        btnPrestamo.setMinHeight(40);
        btnPrestamo.setOnAction(event -> controller.nuevoPrestamo());

        table = new CarTableModel(300,300);
        table.getSelectionModel().selectedIndexProperty().addListener(e -> controller.changeTableSelection());
    }

    public CarTableModel getTable() {
        return table;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Label getLblMarca() {
        return lblMarca;
    }

    public Label getLblPatente() {
        return lblPatente;
    }

    public Label getLblAvailability() {
        return lblAvailability;
    }
}
