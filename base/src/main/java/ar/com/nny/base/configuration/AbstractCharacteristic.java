package ar.com.nny.base.configuration;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import ar.com.nny.base.dao.FieldQueryCache;
import ar.com.nny.base.dao.GetAllCacheable;
import ar.com.nny.base.utils.IdentificablePersistentObject;

/**
 * Provee funcionalidad basica para manejar caracteristicas y clasificaciones de un objeto.
 *
 * @author rodro
 */
@GetAllCacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public abstract class AbstractCharacteristic extends IdentificablePersistentObject {

    @FieldQueryCache
    private String code;

    @BusinessLabel
    @FieldQueryCache
    private String name;

    private AbstractCharacteristic superCharacteristic;

    private Set<AbstractCharacteristic> subCharacteristics = new HashSet<AbstractCharacteristic>();


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public AbstractCharacteristic getSuperCharacteristic() {
        return superCharacteristic;
    }


    private void setSuperCharacteristic(AbstractCharacteristic superCharacteristic) {
        this.superCharacteristic = superCharacteristic;
    }


    public Set<AbstractCharacteristic> getSubCharacteristics() {
        return Collections.unmodifiableSet(subCharacteristics);
    }


    @SuppressWarnings("unused")
	private void setSubCharacteristics(Set<AbstractCharacteristic> subCharacteristics) {
        this.subCharacteristics = subCharacteristics;
    }


    public void addSubCharacteristic(AbstractCharacteristic subCharacteristic) {
        subCharacteristics.add(subCharacteristic);
        subCharacteristic.setSuperCharacteristic(this);
    }
}
