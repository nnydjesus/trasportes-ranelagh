package ar.com.syr.transportes.search;

import org.apache.commons.collections.Predicate;

import ar.com.nny.base.search.Home;
import ar.com.syr.transportes.bean.Remito;

public class HomeRemito extends Home<Remito> {

    private final static HomeRemito INSTANCE = new HomeRemito();

    public static HomeRemito getInstance() {
        return INSTANCE;
    }

    private HomeRemito() {
        super(Remito.class);
    }

    @Override
    protected Predicate getCriterio(final Remito example) {
        return this.getCriterioTodas();
    }

    @Override
    public Remito createExample() {
        return new Remito();
    }

}
