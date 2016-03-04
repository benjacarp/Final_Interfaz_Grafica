package ui.client;

import javafx.collections.ObservableList;
import model.Client;
import service.ClientService;
import ui.GenericController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 03/03/2016.
 */
public class ClientFrameController extends GenericController{
    private final ClientFrame view;
    private final ClientService service;

    public ClientFrameController(ClientFrame view) {
        super(ClientService.getInstance());
        this.service = (ClientService) this.genericService;
        this.view = view;
    }
    public void addButtonClicked() {

        System.out.println("Add new Client");

    }

    public ObservableList getClients() {

        List<Client> list = new ArrayList<Client>();

        Client c1 = new Client();
        c1.setName("jorge");

        Client c2 = new Client();
        c2.setName("luis");

        list.add(c1);
        list.add(c2);

        return (ObservableList) list;
    }
}
