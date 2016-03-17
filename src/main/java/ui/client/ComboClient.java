package ui.client;

import exception.DIGAppException;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import model.Client;
import service.ClientService;

import java.util.List;

/**
 * Created by ASUS on 17/03/2016.
 */
public class ComboClient extends ComboBox {

    public ComboClient() {
        initModel();
    }

    private void initModel() {
        List<Client> clients = null;

        try {
            clients = ClientService.getInstance().findAll();

        } catch (DIGAppException e) {
            e.printStackTrace();
        }

        if (!clients.isEmpty()) {
            this.getItems().addAll(clients);
        }
    }

    public Client getEmployee(){
        return (Client) this.getSelectionModel().getSelectedItem();
    }
}
