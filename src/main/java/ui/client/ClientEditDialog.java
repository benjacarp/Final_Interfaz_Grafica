package ui.client;

import exception.DIGAppException;
import javafx.embed.swing.SwingFXUtils;
import model.Client;
import service.ClientService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * Created by ASUS on 16/03/2016.
 */
public class ClientEditDialog extends ClientAbstractDialog{

    private Client currentClient;

    public ClientEditDialog(Client currentClient) {
        super();
        this.setTitle("Modificar Cliente");
        acceptButton.setText("Modificar");
        campoDNI.setDisable(true);
        this.currentClient = currentClient;
        llenarCampos();
    }

    private void llenarCampos() {
        campoNombre.setText(currentClient.getName());
        campoDNI.setText(currentClient.getDni());
        cargarImagen(currentClient);
    }

    private void cargarImagen(Client currentClient) {
        BufferedImage img;
        Blob photo = currentClient.getPhoto();
        byte[] blobBytes = new byte[0];
        try {
            blobBytes = photo.getBytes(1, (int) photo.length());
            img = ImageIO.read(new ByteArrayInputStream(blobBytes));
            this.image = SwingFXUtils.toFXImage(img, null);
            this.imageView.setImage(image);
        } catch (IOException e) {
            System.out.println("sin imagen");
        } catch (NullPointerException ex) {
            System.out.println("sin imagen");
        } catch (SQLException e) {
            System.out.println("sin imagen");
        }
    }

    @Override
    protected void guardar() {
        currentClient.setName(this.campoNombre.getText());
        currentClient.setDni(this.campoDNI.getText());

        Blob blob = convertImageToBlob(this.image);
        currentClient.setPhoto(blob);

        try {
            ClientService.getInstance().update(currentClient);
            alertClients(currentClient, true);
        } catch (DIGAppException e) {
            e.printStackTrace();
        }
        this.close();
    }

}
