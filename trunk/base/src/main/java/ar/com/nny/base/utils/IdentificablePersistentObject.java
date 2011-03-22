package ar.com.nny.base.utils;

import javax.persistence.MappedSuperclass;

import ar.com.nny.base.persistence.PersistentObject;


/**
 * Persistent objects are BusinessObjects that persist in the data store.
 * They add the state version, used for optimistic locking.
 *
 * @author Claudio
 */
@MappedSuperclass
public abstract class IdentificablePersistentObject extends PersistentObject {

//    @EmbeddedId
//    @SerializationStrategy(access = Through.READ_ONLY_ACCESSOR)
//    @DocumentId
//    @FieldBridge(impl = UUIDBridge.class)
//    private String id;


    public IdentificablePersistentObject() {
        //this.id = UUIDGenerator.getInstance().generateRandomBasedUUID().toString();
    }


    public String getId() {
        return null;
    }
    

}