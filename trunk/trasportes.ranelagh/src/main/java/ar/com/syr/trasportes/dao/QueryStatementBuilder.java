package ar.com.syr.trasportes.dao;


import ar.com.syr.trasportes.utils.PersistentObject;
import ar.com.syr.trasportes.utils.QueryStatement;

public interface QueryStatementBuilder<T extends PersistentObject> {

    public QueryStatement<T> getQuery();

}
