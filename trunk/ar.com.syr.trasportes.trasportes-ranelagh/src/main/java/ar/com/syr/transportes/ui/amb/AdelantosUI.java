package ar.com.syr.transportes.ui.amb;

import ar.com.nny.base.ui.swing.components.GeneralFrame;
import ar.com.nny.base.ui.swing.components.search.SearchPanel;
import ar.com.syr.transportes.bean.Adelanto;

public class AdelantosUI extends GeneralFrame<Adelanto> {
    private static final long serialVersionUID = 7725064134440510100L;

    public AdelantosUI() {
        super("Adelanto", Adelanto.class);
    }

    @Override
    protected void createSearchForm(final SearchPanel<Adelanto> search) {
//        search.addAutocompletetextField(Empleado.LEGAJO, "Legajo");
//        search.addAutocompletetextField(Empleado.NOMBRE, "Nombre");
//        search.addAutocompletetextField(Empleado.APELLIDO, "Apellido");
    }

    @Override
    public Class abmClass() {
        return ABMAdelanto.class;
    }
    
    public static void main(String[] args) {
        new AdelantosUI().setVisible(true);
    }

}