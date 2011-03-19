package ar.com.syr.transportes.ui.amb.remito;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import ar.com.nny.base.common.Item;
import ar.com.nny.base.dao.GenericDao;
import ar.com.nny.base.ui.swing.components.GeneralFrame;
import ar.com.nny.base.ui.swing.components.GeneralTable;
import ar.com.nny.base.ui.swing.components.Generator;
import ar.com.nny.base.ui.swing.components.MyJComboBox;
import ar.com.nny.base.utils.IdentificablePersistentObject;
import ar.com.syr.transportes.bean.Empleado;
import ar.com.syr.transportes.bean.Remito;




public class RemitoUI extends GeneralFrame<Remito> implements Item{
	private static final long serialVersionUID = -4654971392742688838L;
	private MyJComboBox cbEmpleados;
	private List<Remito> remitos;
	

	public RemitoUI() {
		super("Remito", Remito.class);
		super.addActions();
	}
	
	@Override
	protected void createForm() {
		cbEmpleados= new MyJComboBox(tablaList);
		cbEmpleados.addDefaultValue(new Empleado());
		edicion.addComponent("Seleccione El Empleado", cbEmpleados);
		edicion.addComponent("Seleccione El Remito", comboBox);
		edicion.addBindingDateField(Remito.FECHA, "Fecha");
		edicion.addBindingTextField(Remito.ORIGEN, "Origen");
		edicion.addBindingTextField(Remito.DESTINO, "Destino");
		edicion.addBindingTextField(Remito.ID, "Remito");
		edicion.addBindingDoubleField(Remito.COSTO, "Costo");
		edicion.addBindingDoubleField(Remito.CHOFER, "Chofer");
		edicion.addBindingDoubleField(Remito.COMBUSTIBLE, "Combustible");
		edicion.addBindingDoubleField(Remito.LITROS, "Litros");
		edicion.addBindingTextField(Remito.NRO_REMITO_2, "Remito");
		edicion.addBindingTextField(Remito.LUGAR, "Lugar");
		edicion.addBindingIntegerField(Remito.KM, "Kilometros");
		edicion.addBindingTextField(Remito.PATENTE, "Patente");
		
		
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
//			empleado.getCostoEmpleado().setCostoTotal(empleado.getCostoEmpleado().getCostoTotal()+model.getChofer());
//			System.out.println(empleado.getCostoEmpleado().getCostoTotal());
			tablaList.add(model);
			dao.save(model);
//			dao.update(empleado.getCostoEmpleado());
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

	
}
