package ar.com.syr.transportes.search;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.Predicate;

import ar.com.nny.base.search.Home;
import ar.com.syr.transportes.bean.Remito;
import ar.com.syr.transportes.dao.RemitoDao;

public class HomeRemito extends Home<Remito> {

    private final static HomeRemito INSTANCE = new HomeRemito();

    public static HomeRemito getInstance() {
        return INSTANCE;
    }

    private HomeRemito() {
        super(new RemitoDao());
    }

    @Override
    protected Predicate getCriteria(final Remito example) {
        return this.getCriterioTodas();
    }

    @Override
    public Remito createExample() {
        return new Remito();
    }

    @Override
    public List<Remito> searchByExample(final Remito example) {
        return ((RemitoDao) dao).find(example.getId());
    }

    public List<Remito> searchByExample(final String id, final Date desde, final Date hasta) {
        RemitoDao remitoDao = (RemitoDao) dao;
        if (!id.equals("") && desde != null && hasta != null)
            return remitoDao.find(id, desde, hasta);
        if (!id.equals("") && desde == null && hasta == null)
            return remitoDao.find(id);
        if (desde != null && hasta == null)
            return remitoDao.find(id, desde);
        if (id.equals("") && desde != null && hasta != null)
            return remitoDao.find(desde, hasta);
        return remitoDao.getAll();
    }
}
