package ar.com.syr.transportes.dao;

import java.util.Date;
import java.util.List;

import ar.com.nny.base.dao.GenericDao;
import ar.com.nny.base.utils.QueryStatement;
import ar.com.syr.transportes.bean.Remito;
import ar.com.syr.transportes.bean.Unidad;

/**
 * TODO: description
 */
public class UnidadDao extends GenericDao<Unidad> {

    public UnidadDao() {
        super(Unidad.class, "Unidad");
    }
    
    public List<Unidad> searchByExample(Unidad example){
        if(example.getId() ==null && example.getMarca() ==null && example.getModelo()==null){
            return getAll();
        }
        return this.find(example);
    }
    

    public List<Unidad> find(Unidad example) {
        QueryStatement<Unidad> createQuery = this.getDefaultfindQuery();
        addStringFilter("id", example.getId(), createQuery);
        addStringFilter(Unidad.MARCA, example.getMarca(), createQuery);
        addStringFilter(Unidad.MODELO, example.getModelo(), createQuery);
        return createQuery.find(); 
    }

    protected  QueryStatement<Unidad> addStringFilter(String property, String value, QueryStatement<Unidad> query){
        if(value != null){
            query.addQuery(" AND this."+property+" like ? ");
            query.addParameter("%" + value + "%");
        }
        return query;
    }
    
    private QueryStatement<Unidad> getDefaultfindQuery() {
        return new QueryStatement<Unidad>("FROM " + this.getClassName() + " this WHERE 1=1");

    }

}
