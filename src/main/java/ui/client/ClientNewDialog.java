package ui.client;

import exception.DIGAppException;
import model.Client;
import service.ClientService;

import java.sql.Blob;

/**
 * Created by ASUS on 15/03/2016.
 */
public class ClientNewDialog extends ClientAbstractDialog{

    public ClientNewDialog() {
        super();
        this.setTitle("New Employee");
        acceptButton.setText("Save");
    }

    @Override
    protected void guardar() {
        Client client = new Client();
        client.setName(this.campoNombre.getText());
        client.setDni(this.campoDNI.getText());

        Blob blob = convertImageToBlob(this.image);
        client.setPhoto(blob);

        try {
            ClientService.getInstance().save(client);
            alertClients(client, false);
        } catch (DIGAppException e) {
            e.printStackTrace();
        }
        this.close();
    }


}
