package ui.car;

import exception.DIGAppException;
import javafx.embed.swing.SwingFXUtils;
import model.Car;
import service.CarService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * Created by ASUS on 18/03/2016.
 */
public class CarEditDialog  extends CarAbstractDialog {

    private Car currentCar;

    public CarEditDialog(Car currentCar) {
        super();
        this.setTitle("Update Employee");
        acceptButton.setText("Update");
        this.currentCar = currentCar;
        llenarCampos();
    }

    private void llenarCampos() {
        campoMarca.setText(currentCar.getMarca());
        campoPatente.setText(currentCar.getPatente());
        cargarImagen(currentCar);
    }

    private void cargarImagen(Car currentCar) {
        BufferedImage img;
        Blob photo = currentCar.getPhoto();
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
        currentCar.setMarca(this.campoMarca.getText());
        currentCar.setPatente(this.campoPatente.getText());
        currentCar.setPrice(Double.parseDouble(this.campoPrecio.getText()));

        Blob blob = convertImageToBlob(this.image);
        currentCar.setPhoto(blob);

        try {
            CarService.getInstance().update(currentCar);
            alertClients(currentCar, true);
        } catch (DIGAppException e) {
            e.printStackTrace();
        }
        this.close();
    }

}
