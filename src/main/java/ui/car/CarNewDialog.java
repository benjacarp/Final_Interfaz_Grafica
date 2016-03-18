package ui.car;

import exception.DIGAppException;
import model.Car;
import model.Client;
import service.CarService;
import service.ClientService;
import ui.client.ClientAbstractDialog;

import java.sql.Blob;

/**
 * Created by ASUS on 18/03/2016.
 */
public class CarNewDialog extends CarAbstractDialog {

    public CarNewDialog() {
        super();
        this.setTitle("New Car");
        acceptButton.setText("Save");
    }

    @Override
    protected void guardar() {
        Car car = new Car();
        car.setMarca(this.campoMarca.getText());
        car.setPatente(this.campoPatente.getText());
        car.setPrice(Double.parseDouble(this.campoPrecio.getText()));
        car.setAvailable(true);

        Blob blob = convertImageToBlob(this.image);
        car.setPhoto(blob);

        try {
            CarService.getInstance().save(car);
            alertClients(car, false);
        } catch (DIGAppException e) {
            e.printStackTrace();
        }
        this.close();
    }

}
