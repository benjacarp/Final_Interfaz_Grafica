package dao;

import config.HibernateUtil;
import exception.DataAccessException;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

/**
 * Generic dao class to work with any entity class.
 * 
 * @author martin
 * @param <T> Entity class that will be used.
 * @param <I> Id type.
 */
public abstract class GenericDAO<T, I> {
    private static final Logger LOGGER = Logger.getLogger(GenericDAO.class);
    private final Class clazz;
    private String findAllQuery;    
    protected Session session;
    private Transaction transaction;

    /**
     * Class constructor.
     * 
     * @param clazz 
     */
    public GenericDAO(Class clazz) {
        this.clazz = clazz;
        this.initQueries();
    }
    
    private void initQueries() {
        this.findAllQuery = new StringBuilder()
                .append("from ")
                .append(this.clazz.getSimpleName())
                .toString();
    }
    
    protected void startOperation() {
        this.session = HibernateUtil.openSession();
        this.transaction = this.session.beginTransaction();
    }
    
    protected void finishOperation() {
        this.transaction.commit();
    }
    
    protected void handleException(Exception ex) throws DataAccessException {
        if (this.transaction != null) {
            this.transaction.rollback();
            LOGGER.error("Transaction rolledback.", ex);
        }
        throw new DataAccessException(ex);
    }
    
    private void validateNotNull(T object) throws DataAccessException {
        if (object == null) {
            throw new DataAccessException("The object to be proccessed can not be null.");
        }
    }

    public I save(T object) throws DataAccessException {
        this.validateNotNull(object);
        
        I result = null;
        
        try {
            this.startOperation();
            result = (I) this.session.save(object);
            this.finishOperation();
        } catch (Exception ex) {
            this.handleException(ex);
        }
        
        return result;
    }

    public T update(T object) throws DataAccessException {
        this.validateNotNull(object);
        
        T result = null;
        
        try {
            this.startOperation();
            result = (T) this.session.merge(object);
            this.finishOperation();
        } catch (Exception ex) {
            this.handleException(ex);
        }
        
        return result; 
    }

    public void delete(T object) throws DataAccessException {
        this.validateNotNull(object);
        
        try {
            this.startOperation();
            this.session.delete(object);
            this.finishOperation();
        } catch (Exception ex) {
            this.handleException(ex);
        }        
    }
    

    public T findOne(I id) throws DataAccessException {        
        T result = null;
        
        try {
            this.startOperation();
            result = (T) this.session.get(this.clazz, (Serializable) id);
            this.finishOperation();            
        } catch (Exception ex) {
            this.handleException(ex);
        }
        
        if (result == null) {
            throw new DataAccessException(new StringBuilder()
                    .append("Cannot find data for ")
                    .append(this.clazz.getSimpleName())
                    .append(" with id=")
                    .append(id).toString());
        }
        
        return result;
    }
    

    public List<T> findAll() throws DataAccessException {
        List<T> result = null;
        
        try {
            this.startOperation();
            Query query = this.session.createQuery(this.findAllQuery);
            result = query.list();
            this.finishOperation();
        } catch (Exception ex) {
            this.handleException(ex);
        }
        
        return result;
    }
    
    public abstract List<T> findObjectsBySearch(String searchString) throws DataAccessException;
    
    protected List<T> findObjectsBySearch(String queryString, String searchString) throws DataAccessException {
        List<T> result = null;
        
        try {
            this.startOperation();
            Query query = this.session.createQuery(queryString)
                    .setString("parm", "%" + searchString + "%");
            result = query.list();
            this.finishOperation();
        } catch (Exception ex) {
            this.handleException(ex);
        }
        
        return result;
    }
}
