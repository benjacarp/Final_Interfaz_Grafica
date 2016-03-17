package service;

import dao.ClientDAO;
import exception.DIGAppException;
import exception.DataAccessException;
import model.Client;

import java.util.List;

/**
 * Created by ASUS on 04/03/2016.
 */
public class ClientService extends GenericService<Client, Long> {
    private static final ClientService INSTANCE = new ClientService();
    private final ClientDAO clientDAO;

    public ClientService() {
        super(ClientDAO.getInstance());
        this.clientDAO = (ClientDAO) this.genericDAO;
    }

    public static ClientService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Client> findBySearch(String searchString) throws DIGAppException {
        try {
            return this.clientDAO.findObjectsBySearch(searchString);
        } catch (DataAccessException e) {
            throw new DIGAppException(e);
        }
    }
}
