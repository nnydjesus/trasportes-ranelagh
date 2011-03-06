package ar.com.nny.java.base.dao;


import ar.com.nny.java.base.utils.PersistentObject;
import ar.com.nny.java.base.utils.QueryStatement;

public interface QueryStatementBuilder<T extends PersistentObject> {

    public QueryStatement<T> getQuery();

}
