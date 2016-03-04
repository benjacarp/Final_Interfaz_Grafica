package ui.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Client;

/**
 * Created by ASUS on 03/03/2016.
 */
public class ClientTableModel extends TableView<Client>{


    private TableColumn<Client, String> nameColumn;
    private TableColumn<Client, Long> dniColumn;

    public ClientTableModel(int minWidth, int minHeight) {
        createColumns();
        this.setItems(getClients());
        this.getColumns().addAll(nameColumn, dniColumn);
//        this.setMinSize(minWidth, minHeight);
        this.prefWidth(minWidth);
        this.prefHeight(minHeight);
    }

    private void createColumns() {
        nameColumn = new TableColumn<>("Name");
        nameColumn.setPrefWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        dniColumn = new TableColumn<>("DNI");
        dniColumn.setPrefWidth(200);
        dniColumn.setCellValueFactory(new PropertyValueFactory<>("dni"));

    }

    private ObservableList<Client> getClients() {
        ObservableList<Client> clients = FXCollections.observableArrayList();

        clients.add(new Client("Benja",34953806L));
        clients.add(new Client("Juan",12345678L));
        clients.add(new Client("Ana",32165487L));

        return clients;
    }


}
