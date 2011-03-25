package ar.com.syr.transportes.search;

import org.apache.commons.collections.Predicate;

import ar.com.nny.base.search.Home;
import ar.com.syr.transportes.bean.CostoEmpleado;

public class HomeCostoempleado extends Home<CostoEmpleado> {

    private final static HomeCostoempleado INSTANCE = new HomeCostoempleado();

    public static HomeCostoempleado getInstance() {
        return INSTANCE;
    }

    private HomeCostoempleado() {
        super(CostoEmpleado.class);
    }

    @Override
    protected Predicate getCriterio(final CostoEmpleado example) {
        return this.getCriterioTodas();
    }

    @Override
    public CostoEmpleado createExample() {
        return new CostoEmpleado();
    }

}
