package ar.com.nny.base.utils;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;


import ar.com.nny.base.persistence.PersistentObject;
import ar.com.nny.base.persistence.SerializationStrategy;
import ar.com.nny.base.persistence.Through;


/**
 * Persistent objects are BusinessObjects that persist in the data store.
 * They add the state version, used for optimistic locking.
 *
 */
@MappedSuperclass
public abstract class IdentificablePersistentObject extends PersistentObject {

    @SerializationStrategy(access = Through.READ_ONLY_ACCESSOR)
//    @DocumentId
    @Deprecated
    @Transient
    private String id;


    public IdentificablePersistentObject() {
        //this.id = UUIDGenerator.getInstance().generateRandomBasedUUID().toString();
    }


    public String getId() {
        return id;
    }
    public void setId(String id) {
    }
    

}

