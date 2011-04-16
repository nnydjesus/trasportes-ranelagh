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
    
    public List<Remito> searchByExample(Remito example, Date desde, Date hasta){
        String id = example.getId();
        if (!id.equals("") && desde != null && hasta != null)
            return this.find(example, desde, hasta);
        if (!id.equals("") && desde == null && hasta == null)
            return this.find(example);
        if (desde != null && hasta == null)
            return this.find(id, desde);
        if(id.equals("") && desde == null && hasta == null && example.getEmpleado()!=null)
            return getbyEmpleado(example);
        if (id.equals("") && desde != null && hasta != null)
            return this.find(desde, hasta,example);
        return getAll() ;
    }
    
        private List<Remito> getbyEmpleado(Remito example){
            if(!example.getEmpleado().getId().equals("")){
                QueryStatement<Remito> query = this.getDefaultfindQuery();
                query.addQuery(" this.empleado = ?");
                query.addParameter(example.getEmpleado());
                return query.find();
            }else{
                return getAll();
            }
        }

    public List<Remito> find(Remito example) {
        QueryStatement<Remito> createQuery = this.getDefaultfindQuery();
        createQuery.addQuery(" this.id like ? ");
        createQuery.addParameter("%" + example.getId() + "%");
        return find(createQuery,example) ;
    }

    public List<Remito> find(Remito example, final Date desde) {
        QueryStatement<Remito> createQuery = this.getDefaultfindQuery();
        createQuery.addQuery(" this.id like ? AND this.fecha >= ?");
        createQuery.addParameter("%" + example.getId() + "%");
        createQuery.addParameter(desde);
        return find(createQuery,example) ;

    }

    public List<Remito> find(final Date desde, final Date hasta,Remito example) {
        QueryStatement<Remito> createQuery = this.getDefaultfindQuery();
        createQuery.addQuery(" this.fecha >= ? AND this.fecha <= ?");
        createQuery.addParameter(desde);
        createQuery.addParameter(hasta);
        return find(createQuery,example) ;

    }

    public List<Remito> find(Remito example, final Date desde, final Date hasta) {
        QueryStatement<Remito> createQuery = this.getDefaultfindQuery();
        createQuery.addQuery(" this.id like ? AND this.fecha >= ? AND this.fecha <= ?");
        createQuery.addParameter("%" + example.getId() + "%");
        createQuery.addParameter(desde);
        createQuery.addParameter(hasta);
        return find(createQuery,example) ;
    }
    
    private List<Remito> find(QueryStatement<Remito> query, Remito example){
        if(example.getEmpleado() != null && !example.getEmpleado().getId().equals("")){
            query.addQuery(" AND this.empleado = ?");
            query.addParameter(example.getEmpleado());
        }
            
        return query.find();
    }

    private QueryStatement<Remito> getDefaultfindQuery() {
        return new QueryStatement<Remito>("FROM " + this.getClassName() + " this WHERE ");

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
