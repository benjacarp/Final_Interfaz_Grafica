package service;

import dao.PrestamoDAO;
import exception.DIGAppException;
import exception.DataAccessException;
import model.Prestamo;

import java.util.List;

/**
 * Created by ASUS on 17/03/2016.
 */
public class PrestamoService extends GenericService<Prestamo, Long> {
    private static final PrestamoService INSTANCE = new PrestamoService();
    private final PrestamoDAO prestamoDAO;

    public PrestamoService() {
        super(PrestamoDAO.getInstance());
        this.prestamoDAO = (PrestamoDAO) this.genericDAO;
    }

    public static PrestamoService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Prestamo> findBySearch(String searchString) throws DIGAppException {
        try {
            return this.prestamoDAO.findObjectsBySearch(searchString);
        } catch (DataAccessException e) {
            throw new DIGAppException(e);
        }
    }
}
