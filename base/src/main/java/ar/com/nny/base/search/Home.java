package ar.com.nny.base.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import ar.com.nny.base.common.Observable;
import ar.com.nny.base.dao.GenericDao;
import ar.com.nny.base.utils.IdentificablePersistentObject;
import ar.com.nny.base.utils.ReflectionUtils;

public class Home<T extends IdentificablePersistentObject> {

    private int proximoId = 1;

    private List<T> objects = new ArrayList<T>();

    protected GenericDao<T> dao;
    private Class<T> clazz;

    public Home(final Class<T> clazz, boolean refresh ) {
        this(new GenericDao<T>(clazz, clazz.getCanonicalName()), refresh);
    }
    
    public Home(final Class<T> clazz) {
        this(clazz, true);
    }

    public Home(final GenericDao<T> genreDao, Boolean refresh) {
        this.dao = genreDao;
        this.clazz = genreDao.getPersistentClass();
        if(refresh)
            this.refresh();
    }
    public Home(final GenericDao<T> genreDao) {
        this(genreDao, true);
    }

    protected String getProximoId() {
        return this.proximoId++ + "";
    }

    // ********************************************************
    // ** Altas, bajas y modificaciones.
    // ********************************************************

    public void save(final T object) {
        this.validatingCreation(object);
//        if (!object.getId().equals("")) {
            this.objects.add(object);
            dao.save(object);
//        }
    }

    protected void validatingCreation(final T object) {
        // Nothing by default
    }

    public void update(final T object) {
        // T replaced = this.buscarPorId(object.getId());
        // this.objects.remove(this.objects.indexOf(replaced));
        this.objects.remove(object);
        this.objects.add(object);
        dao.update(object);
    }

    public void delete(final T object) {
        this.validatingDeleting(object);
        this.objects.remove(object);
        dao.undelete(object);
    }

    protected void validatingDeleting(final T object) {
        // Nothing by default
    }

    // ********************************************************
    // ** Busquedas
    // ********************************************************

    @SuppressWarnings("unchecked")
    public List<T> searchByExample(final T example) {
        return (List<T>) CollectionUtils.select(this.objects, this.getCriteria(example));
    }

    protected Predicate getCriteria(T example){
        return getCriterioTodas();
    }

    public T getById(final String id) {
        return dao.getById(id);
        // TODO Mejorar el mensaje de error
//        throw new RuntimeException("No se encontro el objeto con el id: " + id);
    }

    public List<T> refresh() {
        this.objects.removeAll(objects);
        this.objects = dao.getAll();
        return this.objects;
    }

    public List<T> getAll() {
        return this.objects;
    }

    // ********************************************************
    // ** Criterios de busqueda
    // ********************************************************

    protected Predicate getCriterioTodas() {
        return new Predicate() {
            public boolean evaluate(final Object arg) {
                return true;
            }
        };
    }

    protected Predicate getCriterioPorId(final String id) {
        return new Predicate() {
            public boolean evaluate(final Object arg) {
                Observable unaEntidad = (Observable) arg;
                return unaEntidad.getId().toLowerCase().contains(id.toLowerCase());
            }
        };
    }

    public T createExample(){
        return ReflectionUtils.instanciate(this.clazz);
    }

}
