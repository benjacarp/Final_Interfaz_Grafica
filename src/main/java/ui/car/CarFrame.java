package ui.car;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ui.controller.CarTableModel;

/**
 * Created by ASUS on 16/03/2016.
 */
public class CarFrame extends Stage{

    private CarTableModel table;
    private final CarFrameController controller;
    private BorderPane pane;
    private ImageView imageView;
    private Image image;
    private Button btnPrestamo;

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
        centerVBox.getChildren().add(table);
        VBox.setMargin(btnPrestamo,   new Insets(10,10,10,130));//top,rigth,bot,left
        centerVBox.getChildren().add(btnPrestamo);
        pane.setCenter(centerVBox);

        VBox rightVBox = new VBox();
        rightVBox.getChildren().add(imageView);
        rightVBox.getChildren().add(hBoxCreator("Marca: ",lblMarca));
        rightVBox.getChildren().add(hBoxCreator("Patente: ",lblPatente));
        rightVBox.getChildren().add(lblAvailability);
        pane.setRight(rightVBox);

        Scene scene = new Scene(pane,550,300);
        scene.getStylesheets().add("/style.css");
        this.setScene(scene);
    }

    private HBox hBoxCreator(String label, Label valor) {

        HBox hbox = new HBox();
        hbox.getChildren().addAll(new Label(label),valor);

        return hbox;
    }

    private void initComponents() {

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
