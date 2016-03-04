package dao;

import exception.DataAccessException;
import model.Client;

import java.util.List;

/**
 * Created by ASUS on 04/03/2016.
 */
public class ClientDAO extends GenericDAO<Client, Long> {

    private static final ClientDAO INSTANCE = new ClientDAO();
    private final String QUERY_FIND_SUPPLIERS_BY_SEARCH = "from Client where name like :parm or dni like :parm";

    private ClientDAO() {
        super(Client.class);
    }

    public static ClientDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Client> findObjectsBySearch(String searchString) throws DataAccessException {
        return this.findObjectsBySearch(QUERY_FIND_SUPPLIERS_BY_SEARCH, searchString);
    }
}
