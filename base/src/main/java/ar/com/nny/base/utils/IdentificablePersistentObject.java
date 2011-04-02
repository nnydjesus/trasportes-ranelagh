package ar.com.nny.base.utils;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import ar.com.nny.base.persistence.PersistentObject;
import ar.com.nny.base.persistence.SerializationStrategy;
import ar.com.nny.base.persistence.Through;
import  org.apache.commons.lang.math.RandomUtils;;
/**
 * Persistent objects are BusinessObjects that persist in the data store. They
 * add the state version, used for optimistic locking.
 * 
 */
@MappedSuperclass
public abstract class IdentificablePersistentObject extends PersistentObject {

    @Id
    @SerializationStrategy(access = Through.ACCESSOR)
    private String id;

    public IdentificablePersistentObject() {
         this.id = String.valueOf(RandomUtils.nextDouble()*((double)10000000));
        // UUIDGenerator.getInstance().generateRandomBasedUUID().toString();
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

}
