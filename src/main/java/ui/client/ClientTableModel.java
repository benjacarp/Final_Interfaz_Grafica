package ui.client;

import exception.GestionAppException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Client;
import service.ClientService;

import java.util.List;

/**
 * Created by ASUS on 03/03/2016.
 */
public class ClientTableModel extends TableView<Client>{


    private TableColumn<Client, String> nameColumn;
    private TableColumn<Client, Long> dniColumn;

    public ClientTableModel(int minWidth, int minHeight) {
        mapColumns();
        update();
//        this.setItems((ObservableList<Client>) ClientService.getInstance().findAll());
        this.getColumns().addAll(nameColumn, dniColumn);

        this.prefWidth(minWidth);
        this.prefHeight(minHeight);
    }

    private void mapColumns() {
        nameColumn = new TableColumn<>("Name");
        nameColumn.setPrefWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        dniColumn = new TableColumn<>("DNI");
        dniColumn.setPrefWidth(200);
        dniColumn.setCellValueFactory(new PropertyValueFactory<>("dni"));

    }

    public void update() {
        ObservableList<Client> clients = FXCollections.observableArrayList();

        List<Client> clientsArray = null;
        try {
            clientsArray = ClientService.getInstance().findAll();

        } catch (GestionAppException e) {
            e.printStackTrace();
        }

        for (Client c : clientsArray) {
            clients.add(c);
        }

        setItems(clients);
        getSelectionModel().clearSelection();
    }

    public void search(String searchString) {
        ObservableList<Client> clients = FXCollections.observableArrayList();

        List<Client> clientsArray = null;
        try {
            clientsArray = ClientService.getInstance().findBySearch(searchString);

        } catch (GestionAppException e) {
            e.printStackTrace();
        }

        for (Client c : clientsArray) {
            clients.add(c);
        }

        setItems(clients);
        getSelectionModel().clearSelection();
    }
}
