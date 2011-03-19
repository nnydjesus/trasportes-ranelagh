package ar.com.syr.transportes.ui.amb.empleado;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTabbedPane;

import ar.com.nny.base.common.Observable;
import ar.com.nny.base.ui.swing.components.GeneralFrame;
import ar.com.nny.base.ui.swing.components.Generator;
import ar.com.nny.base.ui.swing.components.abms.PanelEdicion;
import ar.com.syr.transportes.bean.Direccion;
import ar.com.syr.transportes.bean.Empleado;
import ar.com.syr.transportes.bean.Licencia;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.beans.BeanAdapter;


public class EmpleadoUi extends GeneralFrame<Empleado> {
	private static final long serialVersionUID = 7725064134440510100L;

	protected PanelEdicion<Observable> direccion;
	protected PanelEdicion<Observable> licencia;
	private List<Observable> listLicencias; 
	private List<Observable> listDireccion ;


	public EmpleadoUi() {
		super("Empleado", Empleado.class);
		super.addActions();
	}
	
	@Override
	protected void addPanels() {
		listLicencias = new ArrayList<Observable>();
		listDireccion = new ArrayList<Observable>();
		for (Observable empleado : tablaList) {
			listLicencias.add(((Empleado) empleado).getLicencia());
			listDireccion.add(((Empleado) empleado).getDireccion());
		}
		JTabbedPane panelEmpleado = new JTabbedPane();
		JTabbedPane panelTablas = new JTabbedPane();
		panelEmpleado.addTab("Empleado", edicion);
		panelEmpleado.addTab("Direccion", direccion);
		panelEmpleado.addTab("Licencia", licencia);
		panelTablas.addTab("Empleado", table);
		panelTablas.addTab("Direccion", Generator.GENERATE_TABLE(listDireccion, new Direccion().atributos()));
		panelTablas.addTab("Licencias", Generator.GENERATE_TABLE(listLicencias, new Licencia().atributos()));
		panel.addTab("General", panelEmpleado);
		panel.addTab("Tabla", panelTablas);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void createForm() {
		direccion = new PanelEdicion<Observable>("Direccion", new Direccion());
		licencia = new PanelEdicion<Observable>("Licencia", new Licencia());
		BeanAdapter beanDireccion = new BeanAdapter(edicion.getModel());
		Bindings.bind(direccion, "model", beanDireccion.getValueModel("direccion"));
		Bindings.bind(licencia, "model", beanDireccion.getValueModel("licencia"));
		edicion.addComponent("Seleccione El Legajo", comboBox);
		edicion.addBindingTextField(Empleado.NOMBRE, "Nombre");
		edicion.addBindingTextField(Empleado.APELLIDO, "Apellido");
		edicion.addBindingIntegerField(Empleado.DNI, "Dni");
		edicion.addBindingTextField(Empleado.LEGAJO, "Legajo");
		edicion.addBindingTextField(Empleado.CUIL,"Cuil");
		edicion.addBindingCheckBox(Empleado.PROPIO, "Propio");
		edicion.addBindingIntegerField(Empleado.REGISTRO, "Registro");
		licencia.addBindingDateField(Licencia.CATEGORIA, "Categoria");
		licencia.addBindingDateField(Licencia.CNRT, "Cnrt");
		licencia.addBindingDateField(Licencia.LIBRETA_SANITARIA, "LibretaSanitaria");
	    licencia.addBindingDateField(Licencia.REGISTRO, "Registro");
		direccion.addBindingTextField(Direccion.DIRECCION, "Direccion");
		direccion.addBindingTextField(Direccion.LOCALIDAD, "Localidad");
//		direccion.addBindingIntegerField(Direccion.TELEFONO, "Telefono");
//		direccion.addBindingIntegerField(Direccion.CODPOSTAL, "CodPostal");
		direccion.getBotonAgregar().setVisible(false);
		direccion.getBotonCancelar().setVisible(false);
		direccion.getBotonModificar().setVisible(false);
		licencia.getBotonAgregar().setVisible(false);
		licencia.getBotonModificar().setVisible(false);
		licencia.getBotonCancelar().setVisible(false);

	}
	
	@Override
	public void comboboxListener() {
		super.comboboxListener();
		direccion.setModel(((Empleado)comboBox.getSelectedItem()).getDireccion());
		direccion.getBotonModificar().setEnabled(true);
		licencia.setModel(((Empleado)comboBox.getSelectedItem()).getLicencia());
		licencia.getBotonModificar().setEnabled(true);
	}
	
	@Override
	protected void tableListener(MouseEvent e) {
		super.tableListener(e);
		Empleado empleado = (Empleado) table.getSelected();
		direccion.setModel(empleado.getDireccion());
		direccion.getBotonModificar().setEnabled(true);
		licencia.setModel(empleado.getLicencia());
		licencia.getBotonModificar().setEnabled(true);
	}

	@Override
	protected Empleado getDefaultModel() {
		return new Empleado();
	}
	
public static void main(String[] args) {
	new EmpleadoUi();
}



}