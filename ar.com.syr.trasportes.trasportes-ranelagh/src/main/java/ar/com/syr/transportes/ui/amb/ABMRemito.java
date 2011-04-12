package ar.com.syr.transportes.ui.amb;

import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.text.NumberFormatter;

import main.Main;

import ar.com.nny.base.search.Home;
import ar.com.nny.base.ui.swing.components.AbstractBindingPanel;
import ar.com.nny.base.ui.swing.components.ActionMethodListener;
import ar.com.nny.base.ui.swing.components.MyJComboBox;
import ar.com.nny.base.ui.swing.components.abms.ABMFrame;
import ar.com.syr.transportes.bean.CostoEmpleado;
import ar.com.syr.transportes.bean.Empleado;
import ar.com.syr.transportes.bean.Remito;
import ar.com.syr.transportes.search.HomeEmpleado;
import ar.com.syr.transportes.search.HomeRemito;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

public class ABMRemito extends ABMFrame<Remito>{
    private static final long serialVersionUID = 1L;

    private MyJComboBox<Empleado> cbEmpleados;

    private JFormattedTextField costoChofer;

    private JSpinner porcentageSpinner;

    private JFormattedTextField costoTextField;

    private Double montoViejo;

    public ABMRemito() {
        this(Main.getApp());
    }
    public ABMRemito(Remito model) {
        this(model,Main.getApp());
    }
    public ABMRemito(JFrame parent) {
        this(new Remito(), parent);
    }
    public ABMRemito(Remito model, JFrame parent) {
        super(model, parent);
        cbEmpleados.setSelectedItem(getEdicion().getModel().getEmpleado());
    }

    @Override
    protected void createForm(final AbstractBindingPanel<Remito> edicion) {
        NumberFormatter decimalFormat = new NumberFormatter();
        decimalFormat.setValueClass(Double.class);
        decimalFormat.setFormat(new DecimalFormat("#.##"));
        costoChofer = new JFormattedTextField(decimalFormat);
        Bindings.bind(costoChofer, edicion.getBuilder().getBeandAdapter().getValueModel(Remito.COSTO_CHOFER));
        porcentageSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 50, 5));
        JPanel panelCostoChofer = new JPanel(new GridLayout(1, 2));
        DefaultFormBuilder defBuilder = new DefaultFormBuilder(new FormLayout("p, 2dlu, p:g"));
        defBuilder.append("Porcentage", porcentageSpinner);
        panelCostoChofer.add(costoChofer);
        panelCostoChofer.add(defBuilder.getPanel());
        cbEmpleados = new MyJComboBox<Empleado>(HomeEmpleado.getInstance().getAll());
        cbEmpleados.addDefaultValue(new Empleado());
        edicion.addComponent("Empleado", cbEmpleados);
        edicion.addBindingDateField(Remito.FECHA, "Fecha");
        edicion.addBindingTextField(Remito.ORIGEN, "Origen");
        edicion.addBindingTextField(Remito.DESTINO, "Destino");
        edicion.addBindingTextField(Remito.ID, "Remito");
        costoTextField = edicion.addBindingDoubleField(Remito.COSTO, "Costo");
        edicion.addComponent("Costo Chofer", panelCostoChofer);
        edicion.addBindingDoubleField(Remito.COMBUSTIBLE, "Combustible");
        edicion.addBindingDoubleField(Remito.LITROS, "Litros");
        edicion.addBindingIntegerField(Remito.KM, "Kilometros");
        edicion.addBindingTextField(Remito.PATENTE, "Patente");

        edicion.bind(porcentageSpinner, "value", Remito.PORCENTAGE);
        
    }
    
    @Override
    protected void addActions() {
        super.addActions();
        cbEmpleados.addActionListener(new ActionMethodListener(this, "setEmpleado"));
        porcentageSpinner.addChangeListener(new ActionMethodListener(this, "updateCosto"));
        ActionMethodListener listenerCosto = new ActionMethodListener(this, "updateCostoChofer");
        costoTextField.addKeyListener(listenerCosto);
        costoTextField.addFocusListener(listenerCosto);
        costoChofer.addFocusListener(listenerCosto);
    }
    
    @Override
    protected Home getHome() {
        return  HomeRemito.getInstance();
    }
    
    public void edicionModificar() {
        Remito model = getEdicion().getModel();
        if (!model.getId().equals("")) {
            CostoEmpleado costo = model.getEmpleado().getCostoEmpleado();
            if(montoViejo != null)
                costo.aumentarCosto(model.getCostoChofer() - montoViejo);
            getHome().update(model);
            getEdicion().setModel(getHome().createExample());
            SwingUtilities.updateComponentTreeUI(this);
        }

    }
    
    @Override
    public void setModel(Remito observable) {
        super.setModel(observable);
        if(cbEmpleados.getSelectedItem().getId().equals("")){
            cbEmpleados.setSelectedItem(observable.getEmpleado());
        }
    }
    public void setEmpleado() {
       getEdicion().getModel().setEmpleado(cbEmpleados.getSelectedItem());
    }


    public void updateCosto() {
        Integer value = (Integer) porcentageSpinner.getValue();
        getEdicion().getModel().setPorcentage(value);
        costoChofer.setValue(getEdicion().getModel().getCostoChofer());
    }

    public void updateCostoChofer() {
        Double value = (Double) costoTextField.getValue();
        getEdicion().getModel().setCosto(value == null ? 0.0 :value );
        costoChofer.setValue(getEdicion().getModel().getCostoChofer());
    }
    
    @Override
    public void mostrar() {
        cbEmpleados.updateList(HomeEmpleado.getInstance().getAll());
        super.mostrar();
    }


    public static void main(String[] args) {
        new ABMRemito().setVisible(true);
    }

}
