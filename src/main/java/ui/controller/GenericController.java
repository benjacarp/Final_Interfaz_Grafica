package ui.controller;

import exception.DIGAppException;
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
    
    public I save(E entity) throws DIGAppException {
        return this.genericService.save(entity);
    }
    
    public E update(E entity) throws DIGAppException {
        return this.genericService.update(entity);
    }
    
    public void delete(E entity) throws DIGAppException {
        this.genericService.delete(entity);
    }
    
    public E findOne(I id) throws DIGAppException {
        return this.genericService.findOne(id);
    }
    
    public List<E> findAll() throws DIGAppException {
        return this.genericService.findAll();
    }
    
    public List<E> findBySearch(String searchString) throws DIGAppException {
        return this.genericService.findBySearch(searchString);
    }
}
