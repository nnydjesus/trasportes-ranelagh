package ar.com.nny.base.configuration;

import java.util.Collection;

import ar.com.nny.base.utils.BusinessObject;

public interface FetchCriteria {

    public Object fetch(BusinessObject parent, String propertyName, Object value, FlexConverter converter, Collection<String> notLoadedProperties);

}
