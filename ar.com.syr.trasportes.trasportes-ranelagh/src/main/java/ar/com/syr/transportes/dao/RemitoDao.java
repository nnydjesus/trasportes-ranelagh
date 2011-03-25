package ar.com.syr.transportes.dao;

import java.util.Date;
import java.util.List;

import ar.com.nny.base.dao.GenericDao;
import ar.com.nny.base.utils.QueryStatement;
import ar.com.syr.transportes.bean.Remito;

/**
 * TODO: description
 */
public class RemitoDao extends GenericDao<Remito> {

    public RemitoDao() {
        super(Remito.class, "Remito");
    }

    public List<Remito> find(final String id) {
        QueryStatement<Remito> createQuery = this.getDefaultfindQuery();
        createQuery.addQuery(" WHERE this.id like ? ");
        createQuery.addParameter("%" + id + "%");
        return createQuery.find();
    }

    public List<Remito> find(final String id, final Date desde) {
        QueryStatement<Remito> createQuery = this.getDefaultfindQuery();
        createQuery.addQuery(" WHERE this.id like ? AND this.fecha >= ?");
        createQuery.addParameter("%" + id + "%");
        createQuery.addParameter(desde);
        return createQuery.find();

    }

    public List<Remito> find(final Date desde, final Date hasta) {
        QueryStatement<Remito> createQuery = this.getDefaultfindQuery();
        createQuery.addQuery(" WHERE this.fecha >= ? AND this.fecha <= ?");
        createQuery.addParameter(desde);
        createQuery.addParameter(hasta);
        return createQuery.find();

    }

    public List<Remito> find(final String id, final Date desde, final Date hasta) {
        QueryStatement<Remito> createQuery = this.getDefaultfindQuery();
        createQuery.addQuery(" WHERE this.id like ? AND this.fecha >= ? AND this.fecha <= ?");
        createQuery.addParameter("%" + id + "%");
        createQuery.addParameter(desde);
        createQuery.addParameter(hasta);
        return createQuery.find();
    }

    private QueryStatement<Remito> getDefaultfindQuery() {
        return new QueryStatement<Remito>("FROM " + this.getClassName() + " this ");

    }

    public static void main(final String[] args) {
        RemitoDao dao = new RemitoDao();
        System.out.println(dao.find("remito", new Date()));

        // Query createQuery = dao.session().createQuery(
        // "FROM " + dao.getClassName() + " this WHERE this.id like '%:as%' ");
        // createQuery.getQueryString().concat(" AND this.id = 'hola'");
        //
        // QueryStatement<Remito> queryStatement = new
        // QueryStatement<Remito>("FROM " + dao.getClassName()
        // + " this WHERE this.id like ? ", "%r%");
        // // queryStatement.addQuery(" AND this.id = 'hola'");
        //
        // System.out.println(queryStatement.toString());
        // System.out.println(queryStatement.find());
        // System.out.println(createQuery.getQueryString());

    }
}
