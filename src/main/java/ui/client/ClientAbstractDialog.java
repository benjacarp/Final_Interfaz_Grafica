package ui.client;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import model.Client;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * Created by ASUS on 14/03/2016.
 */
public abstract class ClientAbstractDialog extends Stage {

    protected TextField campoNombre;
    protected TextField campoDNI;

    protected Button acceptButton;
    private Button cancelButton;
    private Button fileChooser;
    private TextField fileField;

    protected Image image;
    protected ImageView imageView;

    public ClientAbstractDialog() {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);

        initComponents();
        createStage();
    }

    private void createStage() {
        this.setTitle("Edit client");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15,15,15,15));
        grid.setVgap(10);
        grid.setHgap(70);
        grid.setPrefWidth(430);
        Label nombre = new Label("Nombre: ");
        GridPane.setConstraints(nombre,0,0);
        GridPane.setConstraints(campoNombre,1,0);
        Label dni = new Label("DNI: ");
        GridPane.setConstraints(dni,0,1);
        GridPane.setConstraints(campoDNI,1,1);
        Label photo = new Label("photo: ");
        GridPane.setConstraints(photo,0,2);

        HBox hBox = new HBox();
        fileField = new TextField();
        hBox.getChildren().add(fileField);
        GridPane.setConstraints(fileChooser,1,2);      //campo para elegir foto dsd la maquina
        hBox.getChildren().add(fileChooser);
        GridPane.setConstraints(hBox,1,2);
        GridPane.setConstraints(imageView,1,3);

        GridPane.setConstraints(cancelButton,0,4);
        GridPane.setConstraints(acceptButton,1,4);
        grid.getChildren().addAll(nombre,campoNombre,dni,campoDNI,photo,hBox,imageView,cancelButton,acceptButton);

        Scene scene = new Scene(grid,300,300);
        scene.getStylesheets().add("/style.css");
        this.setScene(scene);
    }

    private void initComponents() {
        imageView = new ImageView();
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(false);
        image = new Image("/default image small.png");
        imageView.setImage(image);

        campoDNI = new TextField();
        campoNombre = new TextField();
        fileChooser = new Button("...");
        fileChooser.setOnAction(event -> openFileChooser());

        acceptButton = new Button("Aceptar");
        acceptButton.setOnAction(event -> guardar());
        cancelButton = new Button("Cancelar");
    }

    protected abstract void guardar();

    protected void alertClients(Client client, boolean update) {
        Alert alert = new Alert(AlertType.INFORMATION);
        if (update) {
            alert.setTitle("Cliente Modificado");
            alert.setHeaderText("Se modificaron datos de "+ client.getName() +" en el registro de clientes");
        } else {
            alert.setTitle("Nuevo Cliente");
            alert.setHeaderText("Se agrego a "+ client.getName() +" al registro de clientes");
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
