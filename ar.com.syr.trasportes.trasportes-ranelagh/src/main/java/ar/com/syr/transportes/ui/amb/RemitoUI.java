package ar.com.syr.transportes.ui.amb;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.text.NumberFormatter;

import ar.com.nny.base.common.Item;
import ar.com.nny.base.ui.swing.components.AbstractBindingPanel;
import ar.com.nny.base.ui.swing.components.ActionMethodListener;
import ar.com.nny.base.ui.swing.components.GeneralFrame;
import ar.com.nny.base.ui.swing.components.GeneralTable;
import ar.com.nny.base.ui.swing.components.Generator;
import ar.com.nny.base.ui.swing.components.MyJComboBox;
import ar.com.nny.base.ui.swing.components.search.SearchPanel;
import ar.com.syr.transportes.bean.CostoEmpleado;
import ar.com.syr.transportes.bean.Empleado;
import ar.com.syr.transportes.bean.Remito;
import ar.com.syr.transportes.search.HomeEmpleado;
import ar.com.syr.transportes.search.HomeRemito;
import ar.com.syr.transportes.search.RemitoSearchPanel;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

public class RemitoUI extends GeneralFrame<Remito> implements Item {
    private static final long serialVersionUID = -4654971392742688838L;

    private MyJComboBox<Empleado> cbEmpleados;

    private List<Remito> remitos;

    private JFormattedTextField costoChofer;

    private JSpinner porcentageSpinner;

    private JFormattedTextField costoTextField;

    private Double montoViejo;

    public RemitoUI() {
        super("Remito", Remito.class);
        super.addActions();
    }

    @Override
    protected void createForm(final AbstractBindingPanel<Remito> edicion) {
        NumberFormatter decimalFormat = new NumberFormatter();
        decimalFormat.setValueClass(Double.class);
        decimalFormat.setFormat(new DecimalFormat("#.##"));
        costoChofer = new JFormattedTextField(decimalFormat);
        Bindings.bind(costoChofer, edicion.getBeandAdapter().getValueModel(Remito.COSTO_CHOFER));
        porcentageSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 50, 5));
        JPanel panelCostoChofer = new JPanel(new GridLayout(1, 2));
        DefaultFormBuilder defBuilder = new DefaultFormBuilder(new FormLayout("p, 2dlu, p:g"));
        defBuilder.append("Porcentage", porcentageSpinner);
        panelCostoChofer.add(costoChofer);
        panelCostoChofer.add(defBuilder.getPanel());
        cbEmpleados = new MyJComboBox<Empleado>(HomeEmpleado.getInstance().getAll());
        cbEmpleados.addDefaultValue(new Empleado());
        edicion.addComponent("Seleccione El Empleado", cbEmpleados);
        edicion.addComponent("Seleccione El Remito", comboBox);
        edicion.addBindingDateField(Remito.FECHA, "Fecha");
        edicion.addBindingTextField(Remito.ORIGEN, "Origen");
        edicion.addBindingTextField(Remito.DESTINO, "Destino");
        edicion.addBindingTextField(Remito.ID, "Remito");
        costoTextField = edicion.addBindingDoubleField(Remito.COSTO, "Costo");
        edicion.addComponent("Costo Chofer", panelCostoChofer);
//        edicion.addBindingCheckBox(Remito.PAGO, "Pagado");
        // edicion.addBindingDoubleField(Remito.PORCENTAGE, "Costo Chofer");
        edicion.addBindingDoubleField(Remito.COMBUSTIBLE, "Combustible");
        edicion.addBindingDoubleField(Remito.LITROS, "Litros");
        edicion.addBindingIntegerField(Remito.KM, "Kilometros");
        edicion.addBindingTextField(Remito.PATENTE, "Patente");

        edicion.bind(porcentageSpinner, "value", Remito.PORCENTAGE);
        
    }

    @Override
    protected void addActions() {
        super.addActions();
        cbEmpleados.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                RemitoUI.this.updateCombo();
                comboBox.selectedDefault();
            }
        });
        
       search.getTable().getTabla().addMouseListener(new MouseAdapter() {
               @Override
            public void mousePressed(MouseEvent e) {
               if(e.getClickCount() == 3){
                   Remito selected = (Remito)  search.getTable().getSelected();
                   selected.setPago(!selected.getPago());
                   home.update(selected);
               }
        }
    });

        porcentageSpinner.addChangeListener(new ActionMethodListener(this, "updateCosto"));
        ActionMethodListener listenerCosto = new ActionMethodListener(this, "updateCostoChofer");
        costoTextField.addKeyListener(listenerCosto);
        costoTextField.addFocusListener(listenerCosto);
        costoChofer.addFocusListener(listenerCosto);

    }

    @Override
    protected void createHome() {
        home = HomeRemito.getInstance();
    }

    @Override
    public void comboboxListener() {
        if(!comboBox.getSelectedItem().getId().equals("")){
            montoViejo = ((Remito) comboBox.getSelectedItem()).getCosto();
            super.comboboxListener();
        }
    }

    @Override
    protected GeneralTable createTable(final Remito newInstance) {
        return Generator.GENERATE_TABLE(remitos, newInstance.atributos());
    }

    @Override
    protected void edicionAgregar() {
        Remito model = edicion.getModel();
        if (tengo && !model.getId().equals("")) {
            model.setEmpleado(cbEmpleados.getSelectedItem());
            home.save(model);
            edicion.setModel(home.createExample());
            clearCombos();
            this.updateCombo();
        }
    }

    @Override
    protected void edicionModificar() {
        Remito model = edicion.getModel();
        if (!model.getId().equals("")) {
            CostoEmpleado costo = model.getEmpleado().getCostoEmpleado();
            if(montoViejo != null)
                costo.aumentarCosto(model.getCostoChofer() - montoViejo);
            home.update(model);
            edicion.setModel(home.createExample());
            edicion.getBotonModificar().setEnabled(false);
            clearCombos();
            SwingUtilities.updateComponentTreeUI(this);
        }

    }
    
    @Override
    public void setModel(Remito observable) {
        super.setModel(observable);
        if(cbEmpleados.getSelectedItem().getId().equals("")){
            cbEmpleados.setSelectedItem(observable.getEmpleado());
        }
        comboBox.setSelectedItem(observable);
    }

    @Override
    protected void createComboBox() {
        remitos = new ArrayList<Remito>();
        comboBox = new MyJComboBox<Remito>(remitos);
    }

    private void updateCombo() {
        if(!cbEmpleados.getSelectedItem().equals("")){
            remitos.removeAll(remitos);
            remitos.addAll(((Empleado) cbEmpleados.getSelectedItem()).getRemitos());
            comboBox.update(remitos);
            comboBox.addDefaultValue(home.createExample());
            SwingUtilities.updateComponentTreeUI(RemitoUI.this);
        }
    }

    public void updateCosto() {
        Integer value = (Integer) porcentageSpinner.getValue();
        edicion.getModel().setPorcentage(value);
        costoChofer.setValue(edicion.getModel().getCostoChofer());
    }

    public void updateCostoChofer() {
        Double value = (Double) costoTextField.getValue();
        edicion.getModel().setCosto(value == null ? 0.0 :value );
        costoChofer.setValue(edicion.getModel().getCostoChofer());
    }

    @Override
    protected SearchPanel<Remito> createSearchPanel(final Remito newInstance) {
        return new RemitoSearchPanel(newInstance);
    }

    @Override
    protected void createSearchForm(final SearchPanel<Remito> search) {
        search.addAutocompletetextField(Remito.ID, "Numero de Remito");
        ((RemitoSearchPanel) search).addFields();
        search.getTable().getTabla().setEditingColumn(6);
    }
    
       @Override
    protected void edicionCancelar() {
        super.edicionCancelar();
        clearCombos();
    }

    protected void clearCombos() {
        remitos.removeAll(remitos);
        cbEmpleados.setDefaultValue();
    }

}
