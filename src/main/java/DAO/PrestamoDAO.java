package dao;

import exception.DataAccessException;
import model.Prestamo;

import java.util.List;

/**
 * Created by ASUS on 17/03/2016.
 */
public class PrestamoDAO extends GenericDAO<Prestamo, Long> {

    private static final PrestamoDAO INSTANCE = new PrestamoDAO();
    private final String QUERY_FIND_SUPPLIERS_BY_SEARCH = "from Prestamo where name like :parm or dni like :parm";

    private PrestamoDAO() {
        super(Prestamo.class);
    }

    public static PrestamoDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Prestamo> findObjectsBySearch(String searchString) throws DataAccessException {
        return this.findObjectsBySearch(QUERY_FIND_SUPPLIERS_BY_SEARCH, searchString);
    }


}