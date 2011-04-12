package ar.com.syr.transportes.ui.amb;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;

import ar.com.nny.base.ui.swing.components.AbstractBindingPanel;
import ar.com.nny.base.ui.swing.components.ActionMethodListener;
import ar.com.nny.base.ui.swing.components.MyJComboBox;
import ar.com.nny.base.ui.swing.components.abms.ABMFrame;
import ar.com.syr.transportes.bean.Adelanto;
import ar.com.syr.transportes.bean.Empleado;
import ar.com.syr.transportes.bean.FormaDePago;
import ar.com.syr.transportes.bean.PagoCheque;
import ar.com.syr.transportes.bean.enums.Banco;
import ar.com.syr.transportes.search.HomeEmpleado;

public class ABMAdelanto extends ABMFrame<Adelanto>{
    
    private static final long serialVersionUID = 7725064134440510100L;
    private JComboBox comboEmpleados;
    private JComboBox comboFormaDePago;
    private AbstractBindingPanel<PagoCheque> pagoCheque;
    private AbstractBindingPanel<FormaDePago> pagoEfectivo;

    public ABMAdelanto(JFrame parent) {
        this(new Adelanto(), parent);
    }
    public ABMAdelanto(Adelanto model,JFrame parent) {
        super(model, parent);
        selectionPagos();
        selectionDefaultcombos();
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
        
    }
    
    
    public void setEmpleado() {
        getEdicion().getModel().setEmpleado((Empleado) comboEmpleados.getSelectedItem());
    }
    
    @Override
    protected void addActions() {
        super.addActions();
        comboFormaDePago.addActionListener(new ActionMethodListener(this, "changeFormadePago"));
        comboEmpleados.addActionListener(new ActionMethodListener(this, "setEmpleado"));
    }
    
    public void changeFormadePago(){
        if(comboFormaDePago.getSelectedItem() != null){
            getEdicion().getModel().setFornaDePago(((AbstractBindingPanel<? extends FormaDePago>) comboFormaDePago.getSelectedItem()).getModel());
            updatePagos();
        }
    }
    public void updatePagos() {
        pagoEfectivo.setVisible(false);
        pagoCheque.setVisible(false);
        if(comboFormaDePago.getSelectedItem()!= null){
            ((JComponent) comboFormaDePago.getSelectedItem()).setVisible(true);
            update();
        }
    }
    
    public void selectionPagos() {
        pagoCheque.setModel(new PagoCheque());
        pagoEfectivo.setModel(new FormaDePago());
    }
    public void selectionDefaultcombos() {
        comboFormaDePago.setSelectedItem(getEdicion().getModel().getFornaDePago());
        comboEmpleados.setSelectedItem(getEdicion().getModel().getEmpleado());
        updatePagos();
    }
    public static void main(String[] args) {
        new ABMAdelanto(null).setVisible(true);
    }
}
