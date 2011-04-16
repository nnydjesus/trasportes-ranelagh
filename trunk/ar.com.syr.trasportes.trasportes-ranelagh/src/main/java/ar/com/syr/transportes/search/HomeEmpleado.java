package ar.com.syr.transportes.search;

import java.util.List;

import org.apache.commons.collections.Predicate;

import ar.com.nny.base.search.Home;
import ar.com.syr.transportes.bean.Empleado;
import ar.com.syr.transportes.dao.EmpleadoDao;

public class HomeEmpleado extends Home<Empleado> {

    private static HomeEmpleado INSTANCE;

    public synchronized static HomeEmpleado getInstance() {
        if(INSTANCE == null){
            INSTANCE = new HomeEmpleado();
        }
        return INSTANCE;
    }

    private HomeEmpleado() {
        super(new EmpleadoDao());
    }

    @Override
    public List<Empleado> searchByExample(final Empleado example) {
        return ((EmpleadoDao) dao).find(example);
    }

    @Override
    protected Predicate getCriteria(final Empleado example) {
        return this.getCriterioTodas();
    }

    @Override
    public Empleado createExample() {
        return new Empleado();
    }

}
