package ar.com.nny.base.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import ar.com.nny.base.common.Observable;
import ar.com.nny.base.dao.GenericDao;
import ar.com.nny.base.utils.IdentificablePersistentObject;

public abstract class Home<T extends IdentificablePersistentObject> {

    private int proximoId = 1;

    private List<T> objects = new ArrayList<T>();

    private GenericDao<T> dao;

    public Home(final Class<T> clazz) {
        this(new GenericDao<T>(clazz));
    }

    public Home(final GenericDao<T> genreDao) {
        this.dao = genreDao;
        this.refresh();
    }

    protected String getProximoId() {
        return this.proximoId++ + "";
    }

    // ********************************************************
    // ** Altas, bajas y modificaciones.
    // ********************************************************

    public void agregar(final T object) {
        this.validarCreacion(object);
        if (object.getId() == null) {
            object.setId(this.getProximoId());
        }
        this.objects.add(object);
        dao.save(object);
    }

    protected void validarCreacion(final T object) {
        // Nothing by default
    }

    public void actualizar(final T object) {
        // T replaced = this.buscarPorId(object.getId());
        // this.objects.remove(this.objects.indexOf(replaced));
        this.objects.remove(object);
        this.objects.add(object);
        dao.update(object);
    }

    public void eliminar(final T object) {
        this.validarEliminacion(object);
        this.objects.remove(object);
        dao.undelete(object);
    }

    protected void validarEliminacion(final T object) {
        // Nothing by default
    }

    // ********************************************************
    // ** Busquedas
    // ********************************************************

    @SuppressWarnings("unchecked")
    public List<T> searchByExample(final T example) {
        return (List<T>) CollectionUtils.select(this.objects, this.getCriterio(example));
    }

    protected abstract Predicate getCriterio(T example);

    public T buscarPorId(final int id) {
        for (T candidate : this.buscarTodos()) {
            if (candidate.getId().equals(id))
                return candidate;
        }

        // TODO Mejorar el mensaje de error
        throw new RuntimeException("No se encontro el objeto con el id: " + id);
    }

    public List<T> refresh() {
        this.objects.removeAll(objects);
        this.objects = dao.getAll();
        return this.objects;
    }

    public List<T> buscarTodos() {
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

    public abstract T createExample();

}
