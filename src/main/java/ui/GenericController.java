package ui;

import exception.GestionAppException;
import javafx.fxml.Initializable;
import service.GenericService;

import java.util.List;

/**
 * Generic behavior for controllers.
 * 
 * @author Gerardo Martín Roldán
 * 
 * @param <E> Entity class.
 * @param <I> Entity class's id.
 */
public abstract class GenericController<E, I> {
    protected final GenericService<E, I> genericService;

    public GenericController(GenericService genericService) {
        this.genericService = genericService;
    }
    
    public I save(E entity) throws GestionAppException {
        return this.genericService.save(entity);
    }
    
    public E update(E entity) throws GestionAppException {
        return this.genericService.update(entity);
    }
    
    public void delete(E entity) throws GestionAppException {
        this.genericService.delete(entity);
    }
    
    public E findOne(I id) throws GestionAppException {
        return this.genericService.findOne(id);
    }
    
    public List<E> findAll() throws GestionAppException {
        return this.genericService.findAll();
    }
    
    public List<E> findBySearch(String searchString) throws GestionAppException {
        return this.genericService.findBySearch(searchString);
    }
}
