package dao;

import exception.DataAccessException;
import model.Car;

import java.util.List;

/**
 * Created by ASUS on 16/03/2016.
 */
public class CarDAO extends GenericDAO<Car, Long> {

    private static final CarDAO INSTANCE = new CarDAO();
    private final String QUERY_FIND_SUPPLIERS_BY_SEARCH = "from Car where name like :parm or dni like :parm";

    private CarDAO() {
        super(Car.class);
    }

    public static CarDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Car> findObjectsBySearch(String searchString) throws DataAccessException {
        return this.findObjectsBySearch(QUERY_FIND_SUPPLIERS_BY_SEARCH, searchString);
    }
}

