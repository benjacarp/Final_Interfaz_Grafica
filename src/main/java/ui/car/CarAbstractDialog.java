package ui.car;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Car;
import model.Client;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * Created by ASUS on 18/03/2016.
 */
public abstract class CarAbstractDialog extends Stage {

    protected TextField campoMarca;
    protected TextField campoPatente;
    protected TextField campoPrecio;

    protected Button acceptButton;
    protected Button cancelButton;
    protected Button fileChooser;
    protected TextField fileField;

    protected Image image;
    protected ImageView imageView;

    public CarAbstractDialog() {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);

        initComponents();
        createStage();
    }

    private void initComponents() {
        imageView = new ImageView();
        imageView.setFitHeight(150);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(false);
        image = new Image("/default_car.jpg");
        imageView.setImage(image);

        campoMarca = new TextField();
        campoPatente = new TextField();
        campoPrecio = new TextField();
        fileChooser = new Button("...");

        acceptButton = new Button("Aceptar");
        cancelButton = new Button("Cancelar");

        fileChooser.setOnAction(event -> openFileChooser());
        acceptButton.setOnAction(event -> guardar());
    }

    private void createStage() {
        this.setTitle("Edit client");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15,15,15,15));
        grid.setVgap(10);
        grid.setHgap(70);
        grid.setPrefWidth(430);
        Label marca = new Label("Marca: ");
        GridPane.setConstraints(marca,0,0);
        GridPane.setConstraints(campoMarca,1,0);
        Label patente = new Label("Chapa: ");
        GridPane.setConstraints(patente,0,1);
        GridPane.setConstraints(campoPatente,1,1);

        Label precio = new Label("Precio: ");
        GridPane.setConstraints(precio,0,2);
        GridPane.setConstraints(campoPrecio,1,2);

        Label photo = new Label("foto: ");
        GridPane.setConstraints(photo,0,3);
        HBox hBox = new HBox();
        fileField = new TextField();
        hBox.getChildren().add(fileField);
        GridPane.setConstraints(fileChooser,1,3);
        hBox.getChildren().add(fileChooser);
        GridPane.setConstraints(hBox,1,3);
        GridPane.setConstraints(imageView,1,4);

        GridPane.setConstraints(cancelButton,0,5);
        GridPane.setConstraints(acceptButton,1,5);
        grid.getChildren().addAll(marca,campoMarca,patente,campoPatente,photo,hBox,imageView,cancelButton,acceptButton, precio,campoPrecio);

        Scene scene = new Scene(grid,380,375);
        scene.getStylesheets().add("/style.css");
        this.setScene(scene);
    }

    protected abstract void guardar();

    protected void alertClients(Car car, boolean update) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (update) {
            alert.setTitle("Auto Modificado");
            alert.setHeaderText("Se modificaron datos de "+ car.getMarca() +" en el registro de vehiculos");
        } else {
            alert.setTitle("Nuevo Vehiculo");
            alert.setHeaderText("Se agrego el vehiculo "+ car.getMarca() +" al registro de vehiculos");
        }
        alert.showAndWait();
    }

    protected Blob convertImageToBlob(Image image) {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "jpg", stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Blob blob = null;
        byte[] bytes  = stream.toByteArray();
        try {
            blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blob;
    }

    private void openFileChooser() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image File","*.jpg")
        );
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            fileField.setText(selectedFile.getName());


            BufferedImage img = null;
            try {
                img = ImageIO.read(selectedFile);

            } catch (IOException e) {
                e.printStackTrace();
            }

            this.image = SwingFXUtils.toFXImage(img, null);
            this.imageView.setImage(image);
        }
    }
}
