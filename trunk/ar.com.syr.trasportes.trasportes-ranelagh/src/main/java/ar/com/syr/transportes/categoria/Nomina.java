package ar.com.syr.transportes.categoria;

import java.util.Collection;

import ar.com.nny.base.common.ItemComposite;
import ar.com.syr.transportes.bean.Empleado;
import ar.com.syr.transportes.ui.Itemempleado;
import ar.com.syr.transportes.ui.amb.EmpleadoUi;

public class Nomina extends ItemComposite {
    private static final long serialVersionUID = 1L;

    EmpleadoUi empleadoUi = new EmpleadoUi();

    public Nomina() {
        this.addAll(empleadoUi.getObjects());
    }

    @Override
    public synchronized boolean addAll(final Collection<? extends Object> c) {
        for (Object object : c) {
            this.add(new Itemempleado((Empleado) object, empleadoUi));
        }
        return true;
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
        empleadoUi.setModel((Empleado) item);
    }

    public static void main(final String[] args) {
        new Operaciones();
    }

}
