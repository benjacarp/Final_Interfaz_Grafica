package ui.client;

import exception.GestionAppException;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Client;
import service.ClientService;
import ui.GenericController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by ASUS on 03/03/2016.
 */
public class ClientFrameController extends GenericController{
    private final ClientFrame view;
    private final ClientService service;
    private Client currentClient;

    public ClientFrameController(ClientFrame view) {
        super(ClientService.getInstance());
        this.service = (ClientService) this.genericService;
        this.view = view;
    }
    public void addButtonClicked() {

        System.out.println("Add new Client");
        Stage stage = new ClientNewDialog();
        stage.showAndWait();
        System.out.println("update table view");
        this.view.getTable().update();

    }

    public ObservableList getClients() {

        List<Client> list = new ArrayList<Client>();

        Client c1 = new Client();
        c1.setName("jorge");

        Client c2 = new Client();
        c2.setName("luis");

        list.add(c1);
        list.add(c2);

        return (ObservableList) list;
    }

    public void deleteButtonClicked() {
        Client clientToBeDeleted = view.getTable().getSelectionModel().getSelectedItem();
        if (clientToBeDeleted != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Needed");
            alert.setHeaderText("You are about to delete: " + clientToBeDeleted.getName() + "(dni: " + clientToBeDeleted.getDni() + ")");
            alert.setContentText("Are you ok with this?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                try {
                    service.delete(clientToBeDeleted);
                    this.view.getTable().update();
                } catch (GestionAppException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void changeTableSelection() {

        this.currentClient = view.getTable().getSelectionModel().getSelectedItem();

        cargarDatos(currentClient);
    }

    private void cargarDatos(Client currentClient) {
        if (currentClient != null) {
            try {
                this.view.getDisplayName().setText(currentClient.getName());
                cargarImagen(currentClient);
            } catch (NullPointerException e) {
                System.out.println("null");
                cargarImagenPorDefault();
            }
        } else {
            this.view.getDisplayName().setText("");
            cargarImagenPorDefault();
        }
    }

    private void cargarImagenPorDefault() {
        this.view.getImageView().setImage(new Image("/default image small.png"));
    }

    private void cargarImagen(Client currentClient) {
        BufferedImage img;
        Blob photo = currentClient.getPhoto();
        byte[] blobBytes = new byte[0];
        try {
            blobBytes = photo.getBytes(1, (int) photo.length());
            img = ImageIO.read(new ByteArrayInputStream(blobBytes));
            Image image = SwingFXUtils.toFXImage(img, null);
            this.view.getImageView().setImage(image);
        } catch (IOException e) {
            cargarImagenPorDefault();
            e.printStackTrace();
        } catch (NullPointerException ex) {
            cargarImagenPorDefault();
            System.out.println("sin imagen");
        } catch (SQLException e) {
            cargarImagenPorDefault();
            e.printStackTrace();
        }
    }

    private static void saveBytesToFile(String filePath, byte[] fileBytes) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(filePath);
        outputStream.write(fileBytes);
        outputStream.close();
    }

    public void checkClient() {
        System.out.println(currentClient);
    }

    public void search(String searchString) {
        this.view.getTable().search(searchString);
    }

    public void sensitiveSearch(String text) {
        System.out.println(text);
        if (text != null) {
            this.view.getTable().search(text);
        } else {
            //mostrar todos
            this.view.getTable().update();
        }
    }

    public void updateClient() {
        System.out.println("Modify client" + currentClient);
        Stage stage = new ClientEditDialog(currentClient);
        stage.showAndWait();
        this.view.getTable().update();
    }
}
