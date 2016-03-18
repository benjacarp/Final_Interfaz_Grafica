package ui.car;

import exception.DIGAppException;
import javafx.scene.control.ComboBox;
import model.Car;
import service.CarService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 17/03/2016.
 */
public class ComboCar extends ComboBox {

    public ComboCar() {
        initModel();
    }

    private void initModel() {
        List<Car> cars = null;

        try {
            cars = CarService.getInstance().findAll();

        } catch (DIGAppException e) {
            e.printStackTrace();
        }
//        for (Car c : cars) {
//            if (!c.isAvailable()) {
//                cars.remove(c);
//            }
//        }

        if (!cars.isEmpty()) {
            ArrayList<Car> auxiliar = new ArrayList<>();

            for (Car c:cars) {
                if (c.isAvailable()) {
                    auxiliar.add(c);
                }
            }

            this.getItems().addAll(auxiliar);
        }

//        setSelectionModel(null);
    }

    public Car getSelectedCar(){
        return (Car) this.getSelectionModel().getSelectedItem();
    }

}
