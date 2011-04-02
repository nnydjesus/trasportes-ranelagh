package ar.com.syr.transportes.ui.amb;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;

import ar.com.nny.base.ui.swing.components.AbstractBindingPanel;
import ar.com.nny.base.ui.swing.components.ActionMethodListener;
import ar.com.nny.base.ui.swing.components.GeneralFrame;
import ar.com.nny.base.ui.swing.components.MyJComboBox;
import ar.com.nny.base.ui.swing.components.search.SearchPanel;
import ar.com.syr.transportes.bean.Adelanto;
import ar.com.syr.transportes.bean.Empleado;
import ar.com.syr.transportes.bean.FormaDePago;
import ar.com.syr.transportes.bean.PagoCheque;
import ar.com.syr.transportes.bean.enums.Banco;
import ar.com.syr.transportes.search.HomeEmpleado;

public class AdelantosUI extends GeneralFrame<Adelanto> {
    private static final long serialVersionUID = 7725064134440510100L;
    private JComboBox comboEmpleados;
    private JComboBox comboFormaDePago;
    private AbstractBindingPanel<PagoCheque> pagoCheque;
    private AbstractBindingPanel<FormaDePago> pagoEfectivo;


    public AdelantosUI() {
        super("Adelanto", Adelanto.class);
    }

    @Override
    protected void createForm(final AbstractBindingPanel<Adelanto> edicion) {
        pagoCheque = new AbstractBindingPanel<PagoCheque>(new PagoCheque());
        pagoEfectivo = new AbstractBindingPanel<FormaDePago>(new FormaDePago());
        
        edicion.addBindingDateField(Adelanto.FECHA, "Fecha");
        comboEmpleados = edicion.addBindingComboBox(Adelanto.EMPLEADO, HomeEmpleado.getInstance().getAll());
        edicion.addBindingTextField(Adelanto.COMENTARIO, "Comentario");
        
        List<AbstractBindingPanel<? extends FormaDePago>> list = new ArrayList<AbstractBindingPanel<? extends FormaDePago>>();
        list.add(pagoEfectivo);list.add(pagoCheque);
        comboFormaDePago = new MyJComboBox<AbstractBindingPanel<? extends FormaDePago>>(list);
        edicion.addComponent("Forma De Pago", comboFormaDePago);       

        pagoCheque.addBindingDoubleField(FormaDePago.MONTO, "Monto");
        pagoCheque.addBindingComboBox(PagoCheque.BANCO, Banco.values());
        pagoCheque.addBindingTextField(PagoCheque.NUM_CHEQUE, "Numero de cheque");
        
        pagoEfectivo.addBindingDoubleField(FormaDePago.MONTO, "Monto");
        
        edicion.addComponent("", pagoEfectivo);
        edicion.addComponent("", pagoCheque);
        
        pagoEfectivo.setVisible(false);
        pagoCheque.setVisible(false);
        
        
//        edicion.addBindingTextField(Adelanto.NUMERO_DE_ORDEN, "Numero de Orden");

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

    @SuppressWarnings("unchecked")
    protected void addCopleteDatos() {
        edicion.getModel().setEmpleado((Empleado) comboEmpleados.getSelectedItem());
        comboEmpleados.setSelectedItem(null);
        edicion.getModel().setFornaDePago(((AbstractBindingPanel<? extends FormaDePago>) comboFormaDePago.getSelectedItem()).getModel());
        comboFormaDePago.setSelectedItem(null);
    }

    @Override
    protected void addActions() {
        super.addActions();
        comboFormaDePago.addActionListener(new ActionMethodListener(this, "changeFormadePago"));
    }
    
    public void changeFormadePago(){
        pagoEfectivo.setVisible(false);
        pagoCheque.setVisible(false);
        if(comboFormaDePago.getSelectedItem()!= null){
            ((JComponent) comboFormaDePago.getSelectedItem()).setVisible(true);
            update();
        }
    }
    
    @Override
    public void setModel(Adelanto observable) {
        pagoCheque.setModel(new PagoCheque());
        pagoEfectivo.setModel(new FormaDePago());
        super.setModel(observable);
    }

    @Override
    protected void createSearchForm(final SearchPanel<Adelanto> search) {
//        search.addAutocompletetextField(Empleado.LEGAJO, "Legajo");
//        search.addAutocompletetextField(Empleado.NOMBRE, "Nombre");
//        search.addAutocompletetextField(Empleado.APELLIDO, "Apellido");
    }

}