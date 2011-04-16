package ar.com.syr.transportes.categoria;

import java.util.Collection;

import javax.swing.JFrame;

import ar.com.nny.base.common.ItemComposite;
import ar.com.syr.transportes.bean.Empleado;
import ar.com.syr.transportes.search.HomeEmpleado;
import ar.com.syr.transportes.ui.Itemempleado;
import ar.com.syr.transportes.ui.amb.ABMEmpleado;

public class Nomina extends ItemComposite {
    private static final long serialVersionUID = 1L;

    ABMEmpleado empleadoUi;

    public Nomina(JFrame parent) {
        empleadoUi = new ABMEmpleado(parent);
        this.addAll(HomeEmpleado.getInstance().getAll());
    }
    
    @Override
    public synchronized boolean addAll(final Collection<? extends Object> c) {
        for (Object object : c) {
            addItem(object);
        }
        return true;
    }

    public void addItem(Object object) {
        Itemempleado item = new Itemempleado((Empleado) object, empleadoUi);
        this.remove(item);
        this.add(item);
    }

    @Override
    public String toString() {
        return "Nomina";
    }

    @Override
    public void mostrar() {
    }

    @Override
    public void mostrar(final Object item) {
        empleadoUi.setModel((Empleado) item, this);
    }

    public static void main(final String[] args) {
        new Operaciones(null);
    }

}
