package ui.car;

import exception.DIGAppException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Car;
import service.CarService;

import java.awt.*;
import java.util.List;

/**
 * Created by ASUS on 16/03/2016.
 */
public class CarTableModel extends TableView<Car>{

    private TableColumn<Car, String> marcaColumn;
    private TableColumn<Car, String> patenteColumn;
    private TableColumn<Car, Double> priceColumn;

    public CarTableModel(int minWidth, int minHeight) {
        mapColumns();
        update();
        this.getColumns().addAll(marcaColumn, patenteColumn, priceColumn);
        this.prefWidth(minWidth);
        this.prefHeight(minHeight);
    }

    public void update() {
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
        marcaColumn.setPrefWidth(198);
        marcaColumn.setCellValueFactory(new PropertyValueFactory<>("marca"));

        patenteColumn = new TableColumn<>("Patente");
        patenteColumn.setPrefWidth(100);
        patenteColumn.setCellValueFactory(new PropertyValueFactory<>("patente"));

        priceColumn = new TableColumn<>("Precio");
        priceColumn.setPrefWidth(60);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
}
