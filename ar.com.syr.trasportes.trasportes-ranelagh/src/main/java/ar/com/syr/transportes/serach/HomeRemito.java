package ar.com.syr.transportes.serach;

import org.apache.commons.collections.Predicate;

import ar.com.nny.base.search.Home;
import ar.com.syr.transportes.bean.Remito;

public class HomeRemito extends Home<Remito>{


    public HomeRemito() {
        super(Remito.class);
    }

    @Override
    protected Predicate getCriterio(Remito example) {
        return getCriterioTodas();
    }

    @Override
    public Remito createExample() {
        return new Remito();
    }

    
}
