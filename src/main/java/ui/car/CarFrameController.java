package ui.car;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import model.Car;
import service.CarService;
import service.ClientService;
import ui.controller.GenericController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * Created by ASUS on 16/03/2016.
 */
public class CarFrameController extends GenericController{

    private final CarFrame view;
    private final CarService service;
    private Car currentCar;

    public CarFrameController(CarFrame carFrame) {
        super(CarService.getInstance());
        this.service = (CarService) this.genericService;
        this.view = carFrame;
    }

    public void changeTableSelection() {
        this.currentCar = view.getTable().getSelectionModel().getSelectedItem();

        cargarDatos(currentCar);
    }

    private void cargarDatos(Car currentCar) {
        if (currentCar != null) {
            try {
                cargarImagen(currentCar);
            } catch (NullPointerException e) {
                System.out.println("null");
                cargarImagenPorDefault();
            }
        } else {
            cargarImagenPorDefault();
        }
    }

    private void cargarImagen(Car currentCar) {
        BufferedImage img;
        Blob photo = currentCar.getPhoto();
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

    private void cargarImagenPorDefault() {
        this.view.getImageView().setImage(new Image("/default_car.jpg"));
    }
}
