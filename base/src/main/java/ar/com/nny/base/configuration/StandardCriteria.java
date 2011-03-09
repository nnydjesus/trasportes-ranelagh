package ar.com.nny.base.configuration;

import java.util.Collection;

import org.hibernate.collection.PersistentCollection;

import ar.com.nny.base.utils.BusinessObject;

public class StandardCriteria implements FetchCriteria {

    public boolean hasToFetch(final Object value) {
        return !(value instanceof PersistentCollection && !((PersistentCollection) value).wasInitialized());
    }


    public Object fetch(final BusinessObject parent, final String propertyName, final Object value, final FlexConverter converter, final Collection<String> notLoadedProperties) {
        if ( this.hasToFetch(value) ) {
            return converter.serialize(value);
        } else {
            notLoadedProperties.add(propertyName);
            return converter.notLoadedValue(value);
        }
    }

}
