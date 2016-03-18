package ui.prestamo;

import exception.DIGAppException;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Car;
import model.Client;
import model.Prestamo;
import service.PrestamoService;

import java.util.List;

/**
 * Created by ASUS on 17/03/2016.
 */
public class PrestamoTable extends TableView<Prestamo> {

    private TableColumn<Prestamo, String> numberColumn;
    private TableColumn<Prestamo, String> clientColumn;
    private TableColumn<Prestamo, String> carColumn;

    public PrestamoTable(int minWidth, int minHeight) {
        mapColumns();
        update();
        this.getColumns().addAll(numberColumn, clientColumn, carColumn);
        this.prefWidth(minWidth);
        this.prefHeight(minHeight);
    }

    public void update() {
        ObservableList<Prestamo> prestamos = FXCollections.observableArrayList();

        List<Prestamo> prestamoList = null;
        try {
            prestamoList = PrestamoService.getInstance().findAll();

        } catch (DIGAppException e) {
            e.printStackTrace();
        }

        for (Prestamo c : prestamoList) {
            if (c.isActive()) {
                prestamos.add(c);
            }
        }

        setItems(prestamos);
        getSelectionModel().clearSelection();
    }

    private void mapColumns() {
        numberColumn = new TableColumn<>("Prestamo");
        numberColumn.setPrefWidth(100);
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        clientColumn = new TableColumn<>("Cliente");
        clientColumn.setPrefWidth(100);
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("client"));

        carColumn = new TableColumn<>("Auto");
        carColumn.setPrefWidth(100);
        carColumn.setCellValueFactory(new PropertyValueFactory<>("car"));
    }
}
