package ar.com.syr.trasportes.ui.amb.empleado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import ar.com.syr.trasportes.bean.Direccion;
import ar.com.syr.trasportes.bean.Empleado;
import ar.com.syr.trasportes.bean.Licencia;
import ar.com.syr.trasportes.ui.GeneralFrame;
import ar.com.syr.trasportes.ui.MyJComboBox;
import ar.com.syr.trasportes.ui.amb.PanelEdicion;
import ar.com.syr.trasportes.utils.Generator;
import ar.com.syr.trasportes.utils.MouseClicked;
import ar.com.syr.trasportes.utils.Observable;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.beans.BeanAdapter;


public class EmpleadoUi extends GeneralFrame<Empleado> {

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
	
	@Override
	protected void createForm() {
		direccion = new PanelEdicion<Observable>("Direccion", new Direccion());
		licencia = new PanelEdicion<Observable>("Licencia", new Licencia());
		this.comboBox = new MyJComboBox(tablaList);
		BeanAdapter beanDireccion = new BeanAdapter(edicion.getModel());
		Bindings.bind(direccion, "model", beanDireccion.getValueModel("direccion"));
		BeanAdapter beanLicencia = new BeanAdapter(edicion.getModel());
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
		
		edicion.getBotonAgregar().addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				tablaList.add(edicion.getModel());
				dao.save(edicion.getModel());
				edicion.setModel(new Empleado());
				SwingUtilities.updateComponentTreeUI(EmpleadoUi.this);
			}	
		});
	
		edicion.getBotonModificar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Empleado model = edicion.getModel();
				tablaList.remove(model);
				tablaList.add(model);
				dao.update(model);
				edicion.setModel(new Empleado());
				edicion.getBotonModificar().setEnabled(false);
				SwingUtilities.updateComponentTreeUI(EmpleadoUi.this);
			}
		});
		edicion.getBotonCancelar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				edicion.setModel(new Empleado());
				edicion.getBotonModificar().setEnabled(false);
			}
		});
		
		table.addtableListener(new MouseClicked() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() ==2){
					Empleado empleado = (Empleado) table.getSelected();
					edicion.setModel(empleado);
					edicion.getBotonModificar().setEnabled(true);
					direccion.setModel(empleado.getDireccion());
					direccion.getBotonModificar().setEnabled(true);
					licencia.setModel(empleado.getLicencia());
					licencia.getBotonModificar().setEnabled(true);
				}
			}
		});
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				edicion.setModel(comboBox.getSelectedItem());
				edicion.getBotonModificar().setEnabled(true);				
				direccion.setModel(((Empleado)comboBox.getSelectedItem()).getDireccion());
				direccion.getBotonModificar().setEnabled(true);
				licencia.setModel(((Empleado)comboBox.getSelectedItem()).getLicencia());
				licencia.getBotonModificar().setEnabled(true);
			
			}
		});
//		direccion.getBotonAgregar().addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				tablaList.add(direccion.getModel());
//				dao.save(direccion.getModel());
//				direccion.setModel(new Empleado());
//				SwingUtilities.updateComponentTreeUI(EmpleadoUi.this);
//			}	
//		});
//	
//		direccion.getBotonModificar().addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				Observable model = direccion.getModel();
//				tablaList.remove(model);
//				tablaList.add(model);
//				dao.update(model);
//				direccion.setModel(new Empleado());
//				direccion.getBotonModificar().setEnabled(false);
//				SwingUtilities.updateComponentTreeUI(EmpleadoUi.this);
//			}
//		});
//		direccion.getBotonCancelar().addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				direccion.setModel(new Empleado());
//				direccion.getBotonModificar().setEnabled(false);
//			}
//		});
//		licencia.getBotonAgregar().addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				tablaList.add(licencia.getModel());
//				dao.save(licencia.getModel());
//				licencia.setModel(new Empleado());
//				SwingUtilities.updateComponentTreeUI(EmpleadoUi.this);
//			}	
//		});
//	
//		licencia.getBotonModificar().addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				Observable model = licencia.getModel();
//				tablaList.remove(model);
//				tablaList.add(model);
//				dao.update(model);
//				licencia.setModel(new Empleado());
//				licencia.getBotonModificar().setEnabled(false);
//				SwingUtilities.updateComponentTreeUI(EmpleadoUi.this);
//			}
//		});
//		licencia.getBotonCancelar().addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				licencia.setModel(new Empleado());
//				licencia.getBotonModificar().setEnabled(false);
//			}
//		});

	}

public static void main(String[] args) {
//    BasicConfigurator.configure();
//    Logger.getAnonymousLogger().setLevel(Level.INFO);
	new EmpleadoUi();
}


}