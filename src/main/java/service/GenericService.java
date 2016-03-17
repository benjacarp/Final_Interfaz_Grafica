package service;

import dao.GenericDAO;
import exception.DataAccessException;
import exception.DIGAppException;

import java.util.List;

/**
 * Generic behavior for service layer.
 * 
 * @author Gerardo Martín Roldán
 * 
 * @param <E> Entity class.
 * @param <I> Entity class's id.
 */
public abstract class GenericService<E, I> {
    protected final GenericDAO<E, I> genericDAO;

    public GenericService(GenericDAO genericDAO) {
        this.genericDAO = genericDAO;
    }
    
    public I save(E object) throws DIGAppException {
        try {
            return this.genericDAO.save(object);
        } catch (DataAccessException ex) {
            throw new DIGAppException(ex.getMessage(), ex);
        }
    }
    
    public E update(E object) throws DIGAppException {
        try {
            return this.genericDAO.update(object);
        } catch (DataAccessException ex) {
            throw new DIGAppException(ex.getMessage(), ex);
        }
    }
    
    public void delete(E object) throws DIGAppException {
        try {
            this.genericDAO.delete(object);
        } catch (DataAccessException ex) {
            throw new DIGAppException(ex.getMessage(), ex);
        }
    }
    
    public E findOne(I id) throws DIGAppException {
        try {
            return this.genericDAO.findOne(id);
        } catch (DataAccessException ex) {
            throw new DIGAppException(ex.getMessage(), ex);
        }
    }
    
    public List<E> findAll() throws DIGAppException {
        try {
            return this.genericDAO.findAll();
        } catch (DataAccessException ex) {
            throw new DIGAppException(ex.getMessage(), ex);
        }
    }
    
    public abstract List<E> findBySearch(String searchString) throws DIGAppException;
}
