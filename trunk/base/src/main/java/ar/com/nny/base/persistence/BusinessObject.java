package ar.com.nny.base.persistence;


import java.io.Serializable;

import javax.persistence.MappedSuperclass;

/**
 * All the domain objects should extend from this class.
 * BusinessObject have an ObjectId, that identifies them within the system.
 * Also some Object methods are implemented, like equals and hashcode
 * (relying on the ID).
 * <p/>
 * BusinessObject that persist in the Database should extend from PersistentObject.
 *
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BusinessObject implements Serializable {

//	@Transient
//	@SerializationStrategy(access=Through.FIELD)
//	private transient BusinessObjectSerializatonMetadata _serializationMetadata;


    /**
     * Business objects should provide an object id. They should guarantee that
     * ids are unique among all the object of that type.
     */
  //  @IgnoreProperty
    public abstract String getId();


    @Override
	public String toString() {
        return this.getClass().getName() + "#" + (getId() == null ? "(No Id)" : getId().toString());
    }


    @Override
	public int hashCode() {
        return getId().hashCode();
    }


    @Override
	public boolean equals(final Object obj) {
        if ( obj == null ) {
            return false;
        }
        if ( !(obj instanceof BusinessObject) ) {
            return false;
        }
        if (((BusinessObject)obj).getId() == null) {
            return false;
        }
        return ((BusinessObject) obj).getId().equals(this.getId());
    }


    public void checkVersionAgainst(final Number version) {
        //do nothing;
    }
}