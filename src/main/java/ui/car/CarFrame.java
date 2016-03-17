package ui.car;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
    private Image image ;

    public CarFrame() {
        this.controller = new CarFrameController(this);
        initComponents();
        createStage();
    }

    private void createStage() {
        pane = new BorderPane();
        VBox centerVBox = new VBox();
        centerVBox.getChildren().add(table);
        pane.setCenter(centerVBox);

        VBox rightVBox = new VBox();
        rightVBox.getChildren().add(imageView);
        pane.setRight(rightVBox);

        Scene scene = new Scene(pane,550,300);
        scene.getStylesheets().add("/style.css");
        this.setScene(scene);
    }

    private void initComponents() {

        imageView = new ImageView();
        imageView.setFitHeight(150);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(false);
        image = new Image("/default_car.jpg");
        imageView.setImage(image);

        table = new CarTableModel(300,300);
        table.getSelectionModel().selectedIndexProperty().addListener(e -> controller.changeTableSelection());
    }

    public CarTableModel getTable() {
        return table;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
