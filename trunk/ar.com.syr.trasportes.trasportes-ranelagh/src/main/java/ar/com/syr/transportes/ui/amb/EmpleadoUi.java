package ar.com.syr.transportes.ui.amb;

import ar.com.nny.base.ui.swing.components.GeneralFrame;
import ar.com.nny.base.ui.swing.components.search.SearchPanel;
import ar.com.syr.transportes.bean.Empleado;
import ar.com.syr.transportes.search.HomeEmpleado;

public class EmpleadoUi extends GeneralFrame<Empleado> {
    private static final long serialVersionUID = 7725064134440510100L;

    public EmpleadoUi() {
        super("Empleado", Empleado.class);
    }

    @Override
    protected void createHome() {
        home = HomeEmpleado.getInstance();
    }


    @Override
    protected void createSearchForm(final SearchPanel<Empleado> search) {
        search.addAutocompletetextField(Empleado.LEGAJO, "Legajo");
        search.addAutocompletetextField(Empleado.NOMBRE, "Nombre");
        search.addAutocompletetextField(Empleado.APELLIDO, "Apellido");
    }

    public static void main(String[] args) {
        new EmpleadoUi().setVisible(true);
    }


    @Override
    public Class<ABMEmpleado> abmClass() {
        return ABMEmpleado.class;
    }


}