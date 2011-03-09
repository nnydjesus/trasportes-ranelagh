package ar.com.nny.base.dao;


import ar.com.nny.base.utils.PersistentObject;
import ar.com.nny.base.utils.QueryStatement;

public interface QueryStatementBuilder<T extends PersistentObject> {

    public QueryStatement<T> getQuery();

}
