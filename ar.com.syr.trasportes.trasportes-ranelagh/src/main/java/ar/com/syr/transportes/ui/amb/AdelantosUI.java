package ar.com.syr.transportes.ui.amb;

import javax.swing.JComboBox;
import javax.swing.JTabbedPane;

import ar.com.nny.base.ui.swing.components.AbstractBindingPanel;
import ar.com.nny.base.ui.swing.components.GeneralFrame;
import ar.com.nny.base.ui.swing.components.search.SearchPanel;
import ar.com.syr.transportes.bean.Adelanto;
import ar.com.syr.transportes.bean.Empleado;
import ar.com.syr.transportes.bean.enums.FormaDePagoType;
import ar.com.syr.transportes.search.HomeEmpleado;

public class AdelantosUI extends GeneralFrame<Adelanto> {
    private static final long serialVersionUID = 7725064134440510100L;
    private JComboBox comboEmpleados;
    private JComboBox comboFormaDePago;


    public AdelantosUI() {
        super("Adelanto", Adelanto.class);
    }

    @Override
    protected void createForm(final AbstractBindingPanel<Adelanto> edicion) {
        edicion.addBindingDateField(Adelanto.FECHA, "Fecha");
        comboEmpleados = edicion.addBindingComboBox(Adelanto.EMPLEADO, HomeEmpleado.getInstance().getAll());
        edicion.addBindingTextField(Adelanto.COMENTARIO, "Comentario");
        edicion.addBindingDoubleField(Adelanto.MONTO, "Monto");
//        edicion.addBindingTextField(Adelanto.NUMERO_DE_ORDEN, "Numero de Orden");
        comboFormaDePago = edicion.addBindingComboBox(Adelanto.FORMA_DE_PAGO, FormaDePagoType.values());       

    }

    @Override
    protected void addPanels(final JTabbedPane panel) {
        super.addPanels(panel);
    }
    
    @Override
    protected void edicionAgregar() {
        addCopleteDatos();
        super.edicionAgregar();
    }
    
    @Override
    protected void edicionModificar() {
        addCopleteDatos();
        super.edicionModificar();
    }

    protected void addCopleteDatos() {
        edicion.getModel().setEmpleado((Empleado) comboEmpleados.getSelectedItem());
        comboEmpleados.setSelectedItem(null);
        edicion.getModel().setFornaDePago((FormaDePagoType) comboFormaDePago.getSelectedItem());
        comboFormaDePago.setSelectedItem(null);
    }

    @Override
    protected void addActions() {
        super.addActions();
    }

    @Override
    protected void createSearchForm(final SearchPanel<Adelanto> search) {
//        search.addAutocompletetextField(Empleado.LEGAJO, "Legajo");
//        search.addAutocompletetextField(Empleado.NOMBRE, "Nombre");
//        search.addAutocompletetextField(Empleado.APELLIDO, "Apellido");
    }

}