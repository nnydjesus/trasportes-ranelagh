package ar.com.syr.transportes.search;

import java.util.List;

import org.apache.commons.collections.Predicate;

import ar.com.nny.base.dao.GenericDao;
import ar.com.nny.base.search.Home;
import ar.com.syr.transportes.bean.Unidad;
import ar.com.syr.transportes.dao.UnidadDao;

public class HomeUnidad extends Home<Unidad> {

    private final static HomeUnidad INSTANCE = new HomeUnidad();

    public static HomeUnidad getInstance() {
        return INSTANCE;
    }

    private HomeUnidad() { 
        super(new UnidadDao());
    }

    @Override
    protected Predicate getCriteria(final Unidad example) {
        return this.getCriterioTodas();
    }

    @Override
    public Unidad createExample() {
        return new Unidad();
    }

    @Override
    public List<Unidad> searchByExample(final Unidad example) {
        return  ((UnidadDao)dao).searchByExample(example);
    }
}
