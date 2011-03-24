package ar.com.syr.transportes.serach;

import org.apache.commons.collections.Predicate;

import ar.com.nny.base.search.Home;
import ar.com.syr.transportes.bean.CostoEmpleado;

public class HomeCostoempleado extends Home<CostoEmpleado> {

    public HomeCostoempleado() {
        super(CostoEmpleado.class);
    }

    @Override
    protected Predicate getCriterio(CostoEmpleado example) {
        return getCriterioTodas();
    }

    @Override
    public CostoEmpleado createExample() {
        return new CostoEmpleado();
    }

}
