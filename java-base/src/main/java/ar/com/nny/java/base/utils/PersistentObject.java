package ar.com.nny.java.base.utils;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;


/**
 * Persistent objects are BusinessObjects that persist in the data store.
 * They add the state version, used for optimistic locking.
 * <p/>
 * WARNING: Business objects should not directly extend PersistentObject.
 *
 * @author Claudio
 */
@MappedSuperclass
public abstract class PersistentObject extends Observable {

    @Version
//    @SerializationStrategy(access = Through.READ_ONLY_FIELD)
    private Long stateVersion;


//    @IgnoreProperty
    public Long getStateVersion() {
        return stateVersion;
    }


    public boolean isPersisted() {
        return this.stateVersion != null;
    }


//   /**
//     * Checks if an optimistic lock collision will be detected
//     * against certain version number.
//     */
//    @Override
//	public void checkVersionAgainst(final Number version) {
//        if ( version == null || this.stateVersion == null ) {
//            return;
//        }
//
//        if ( this.stateVersion > version.longValue() ) {
//            throw new RuntimeException("Optimistic lock collision detected on " + this + ". " +
//                    "Previous version [" + this.stateVersion + "] - New Version [" + version + "]");
//        }
//    }
}
