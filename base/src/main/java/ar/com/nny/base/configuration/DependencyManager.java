package ar.com.nny.base.configuration;

import org.apache.commons.collections15.collection.AbstractCollectionDecorator;

import java.io.Serializable;
import java.util.*;

/**
 * Se banca una lista de objetos agregados en cualquier orden. Los devuelve iterados
 * por orden de dependencias.
 * Se penso originalmente para @GeneratorDepends de los dataGenerators asociados a los
 * beans de negocio.
 *
 * @author hernan.liendo@gmail.com
 */
public final class DependencyManager<E extends DependecyAware> extends AbstractCollectionDecorator<E> implements Serializable {

    private final Set<Class> circularReference = new HashSet<Class>();


    public DependencyManager() {
        this(null);
    }


    public DependencyManager(final Collection<E> _es) {
        super(new ArrayList<E>());

        if ( _es != null ) {
            addAll(_es);
        }
    }


    public boolean add(final E _e) {
        addRecursively(_e, (List<DependecyAware>) collection);
        return true;
    }


    private void addRecursively(final DependecyAware _dependecyAware, final List<DependecyAware> _linkedList) {
        final Collection<DependecyAware> _innerDependencies = _dependecyAware.dependencies();

        if ( _innerDependencies != null && !_innerDependencies.isEmpty() ) {
            for ( final DependecyAware _innerDependency : _innerDependencies ) {
                addRecursively(_innerDependency, _linkedList);
            }
        }

        if ( !circularReference.add(_dependecyAware.getClass()) ) {
            return;
        }

        _linkedList.add(_dependecyAware);
    }


    public boolean addAll(final Collection<? extends E> _es) {
        boolean _result = false;

        if ( _es != null ) {
            for ( final E _e : _es ) {
                if ( add(_e) ) {
                    _result = true;
                }
            }
        }
        return _result;
    }
}
