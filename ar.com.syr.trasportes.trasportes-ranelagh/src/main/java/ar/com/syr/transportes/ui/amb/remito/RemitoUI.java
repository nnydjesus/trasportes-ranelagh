package ar.com.syr.transportes.ui.amb.remito;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import ar.com.nny.base.dao.GenericDao;
import ar.com.nny.base.ui.swing.components.ActionMethodListener;
import ar.com.nny.base.ui.swing.components.GeneralFrame;
import ar.com.nny.base.ui.swing.components.GeneralTable;
import ar.com.nny.base.ui.swing.components.Generator;
import ar.com.nny.base.ui.swing.components.MyJComboBox;
import ar.com.nny.base.utils.IdentificablePersistentObject;
import ar.com.syr.transportes.bean.Empleado;
import ar.com.syr.transportes.bean.Remito;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;




public class RemitoUI extends GeneralFrame<Remito> implements Item{
	private static final long serialVersionUID = -4654971392742688838L;
	
	private MyJComboBox cbEmpleados;
	private List<Remito> remitos;
	private JFormattedTextField costoChofer;

	private JSpinner porcentageSpinner ;

    private JFormattedTextField costoTextField;
	

	public RemitoUI() {
		super("Remito", Remito.class);
		super.addActions();
	}
	
	@Override
	protected void createForm() {
		 NumberFormatter decimalFormat = new NumberFormatter();
		 decimalFormat.setValueClass(Double.class);
		 decimalFormat.setFormat(new DecimalFormat("#.##"));
		costoChofer = new JFormattedTextField(decimalFormat);
		 Bindings.bind(costoChofer, edicion.getBeandAdapter().getValueModel(Remito.COSTO_CHOFER));
		porcentageSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 50, 5));
		JPanel panelCostoChofer = new JPanel(new GridLayout(1,2));
		DefaultFormBuilder defBuilder = new DefaultFormBuilder(new FormLayout("p, 2dlu, p:g"));
		defBuilder.append("Porcentage", porcentageSpinner);
		panelCostoChofer.add(costoChofer);
		panelCostoChofer.add(defBuilder.getPanel());
		cbEmpleados= new MyJComboBox(tablaList);
		cbEmpleados.addDefaultValue(new Empleado());
		edicion.addComponent("Seleccione El Empleado", cbEmpleados);
		edicion.addComponent("Seleccione El Remito", comboBox);
		edicion.addBindingDateField(Remito.FECHA, "Fecha");
		edicion.addBindingTextField(Remito.ORIGEN, "Origen");
		edicion.addBindingTextField(Remito.DESTINO, "Destino");
		edicion.addBindingTextField(Remito.ID, "Remito");
		costoTextField = edicion.addBindingDoubleField(Remito.COSTO, "Costo");
		edicion.addComponent("Costo Chofer", panelCostoChofer);
		edicion.addBindingCheckBox(Remito.PAGO, "Pagado");		
//		edicion.addBindingDoubleField(Remito.PORCENTAGE, "Costo Chofer");
		edicion.addBindingDoubleField(Remito.COMBUSTIBLE, "Combustible");
		edicion.addBindingDoubleField(Remito.LITROS, "Litros");
		edicion.addBindingIntegerField(Remito.KM, "Kilometros");
		edicion.addBindingTextField(Remito.PATENTE, "Patente");
		
		edicion.bind(porcentageSpinner, "value", Remito.PORCENTAGE );
		
		
	}

	@Override
	protected void addActions() {
		super.addActions();
		cbEmpleados.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateCombo();
				comboBox.selectedDefault();
			}
		});
		
		porcentageSpinner.addChangeListener(new ActionMethodListener(this, "updateCosto"));
		ActionMethodListener listenerCosto = new ActionMethodListener(this, "updateCostoChofer");
        costoTextField.addKeyListener(listenerCosto);
		costoTextField.addFocusListener(listenerCosto);
		costoChofer.addFocusListener(listenerCosto);
		
	}
	

	@Override
	protected void createDao() {
		dao = new GenericDao<IdentificablePersistentObject>(Empleado.class, "Empleado");
	}
	@Override
	public void comboboxListener() {
		super.comboboxListener();
	}
	
	@Override
	protected Remito getDefaultModel() {
		return new Remito();
	}
	
	@Override
	protected GeneralTable createTable(Remito newInstance) {
		return Generator.GENERATE_TABLE(remitos, newInstance.atributos());
	}
	
	@Override
	protected void edicionAgregar() {
		Remito model = edicion.getModel();
		if(tengo && model.getId() != null ){
			Empleado empleado = (Empleado) cbEmpleados.getSelectedItem();
			tablaList.add(model);
			dao.save(model);
			empleado.addRemito(model);
			dao.update(empleado);
			edicion.setModel(getDefaultModel());
			updateCombo();
		}
	}

	@Override
	protected void createComboBox() {
		remitos = new ArrayList<Remito>();
		comboBox = new MyJComboBox(remitos);
	}
	
	private void updateCombo() {
		remitos.removeAll(remitos);
		remitos.addAll(((Empleado) cbEmpleados.getSelectedItem()).getRemitos());
		comboBox.update(remitos);
		comboBox.addDefaultValue(getDefaultModel());
		SwingUtilities.updateComponentTreeUI(RemitoUI.this);
	}

    public void updateCosto() {
        Integer value = (Integer) porcentageSpinner.getValue();
        edicion.getModel().setPorcentage(value);
        costoChofer.setValue(edicion.getModel().getCostoChofer());
    }

    public void updateCostoChofer() {
        edicion.getModel().setCosto((Double)costoTextField.getValue());
        costoChofer.setValue(edicion.getModel().getCostoChofer());
    }

	
}
