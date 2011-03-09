package ar.com.nny.base.configuration;

import java.util.Collection;
import java.util.Iterator;

import org.hibernate.MappingException;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.mapping.PersistentClass;

import ar.com.nny.base.utils.PersistentObject;
import ar.com.nny.base.utils.ReflectionUtils;

/**
 * This configuration adds the following features:
 * - Filters are now inherited. Filters declared in a superclass are automatically applied to the children classes.
 * (useful for SoftDeleteable objects).
 *
 * @author Claudio
 */
public class FlexyAnnotationConfiguration extends AnnotationConfiguration {

    private boolean characteristicAdded = false;


    public void addCharacteristicClass(final Class<? extends PersistentObject> persistentClass) throws MappingException {
        try {
            if ( !characteristicAdded ) {
                addClass(AbstractCharacteristic.class);
                characteristicAdded = true;
            }
            addInputStream(new CharacteristicMapper((Class<? extends AbstractCharacteristic>) persistentClass).getMappingAsStream());
        }
        catch ( final MappingException e ) {
            throw e;
        }
        catch ( final Exception e ) {
            throw new MappingException(e);
        }
    }


    @Override
    protected void secondPassCompile() throws MappingException {
        super.secondPassCompile();

        for ( final Iterator it = this.getClassMappings(); it.hasNext(); ) {
            final PersistentClass mapping = (PersistentClass) it.next();
            final Class mappedClass = ReflectionUtils.classForName(mapping.getClassName());

            final Collection<Filters> manyFilters = ReflectionUtils.getAnnotationInherited(mappedClass, Filters.class);
            for ( final Filters filters : manyFilters ) {
                for ( final Filter filter : filters.value() ) {
                    mapping.addFilter(filter.name(), filter.condition());
                }
            }
        }
    }

}
