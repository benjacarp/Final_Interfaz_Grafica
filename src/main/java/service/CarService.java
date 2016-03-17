package service;

import dao.CarDAO;
import exception.DataAccessException;
import exception.GestionAppException;
import model.Car;

import java.util.List;

/**
 * Created by ASUS on 16/03/2016.
 */
public class CarService extends GenericService<Car, Long> {
    private static final CarService INSTANCE = new CarService();
    private final CarDAO carDAO;

    public CarService() {
        super(CarDAO.getInstance());
        this.carDAO = (CarDAO) this.genericDAO;
    }

    public static CarService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Car> findBySearch(String searchString) throws GestionAppException {
        try {
            return this.carDAO.findObjectsBySearch(searchString);
        } catch (DataAccessException e) {
            throw new GestionAppException(e);
        }
    }
}

