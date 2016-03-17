package ui.controller;

import exception.DIGAppException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Car;
import service.CarService;

import java.util.List;

/**
 * Created by ASUS on 16/03/2016.
 */
public class CarTableModel extends TableView<Car>{

    private TableColumn<Car, String> marcaColumn;
    private TableColumn<Car, String> patenteColumn;

    public CarTableModel(int minWidth, int minHeight) {
        mapColumns();
        update();
        this.getColumns().addAll(marcaColumn, patenteColumn);
        this.prefWidth(minWidth);
        this.prefHeight(minHeight);
    }

    private void update() {
        ObservableList<Car> cars = FXCollections.observableArrayList();

        List<Car> carsArray = null;
        try {
            carsArray = CarService.getInstance().findAll();

        } catch (DIGAppException e) {
            e.printStackTrace();
        }

        for (Car c : carsArray) {
            cars.add(c);
        }

        setItems(cars);
        getSelectionModel().clearSelection();
    }

    private void mapColumns() {
        marcaColumn = new TableColumn<>("Marca");
        marcaColumn.setPrefWidth(200);
        marcaColumn.setCellValueFactory(new PropertyValueFactory<>("marca"));

        patenteColumn = new TableColumn<>("Patente");
        patenteColumn.setPrefWidth(200);
        patenteColumn.setCellValueFactory(new PropertyValueFactory<>("patente"));

    }
}
