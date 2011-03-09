package ar.com.nny.base.configuration;

import ar.com.nny.base.dao.GenericDao;
import ar.com.nny.base.utils.PersistentObject;

/**
 * This DAO is used for objects that are being register without a
 * concrete DAO class. It only provides the functionality of GenericDAO.
 *
 * @author Claudio Fernandez
 */
public class ClasslessDAO<T extends PersistentObject> extends GenericDao {

    private final Class<T> persistentClass;


    public ClasslessDAO(Class persistentClass) {
        this.persistentClass = persistentClass;
    }


    @Override
    public Class getPersistentClass() {
        return persistentClass;
    }

}
