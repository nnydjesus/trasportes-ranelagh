package ar.com.syr.trasportes.ui.amb.empleado;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;


import ar.com.syr.trasportes.bean.Empleado;
import ar.com.syr.trasportes.common.Item;
import ar.com.syr.trasportes.dao.GenericDao;
import ar.com.syr.trasportes.ui.GeneralFrame;
import ar.com.syr.trasportes.ui.GeneralTable;
import ar.com.syr.trasportes.ui.MyJComboBox;
import ar.com.syr.trasportes.ui.amb.PanelEdicion;
import ar.com.syr.trasportes.utils.Generator;
import ar.com.syr.trasportes.utils.MouseClicked;
import ar.com.syr.trasportes.utils.Observable;


public class EmpleadoUi extends GeneralFrame<Empleado> {

	private MyJComboBox comboBox;


	public EmpleadoUi() {
		super("Empleado", Empleado.class);
		tablaList = dao.getAll();
		super.addActions();
	}
	
	@Override
	protected void createForm() {
		this.comboBox = new MyJComboBox(tablaList);
		edicion.addComponent("Seleccione El Legajo", comboBox);
		edicion.addBindingTextField(Empleado.NOMBRE, "Nombre");
		edicion.addBindingTextField(Empleado.APELLIDO, "Apellido");
		edicion.addBindingIntegerField(Empleado.DNI, "Dni");
		edicion.addBindingTextField(Empleado.LEGAJO, "Legajo");
		edicion.addBindingTextField(Empleado.CUIL,"Cuil");
		edicion.addBindingCheckBox(Empleado.PROPIO, "Propio");
		edicion.addBindingIntegerField(Empleado.REGISTRO, "Registro");
//		licencia.addBindingDateField(Empleado.this.getLicencia().CATEGORIA, "Categoria");
//		licencia.addBindingDateField(Empleado.this.getLicencia().CNRT, "Cnrt");
//		licencia.addBindingDateField(Empleado.this.getLicencia().LIBRETA_SANITARIA, "LibretaSanitaria");
//	    licencia.addBindingDateField(Empleado.this.getLicencia().REGISTRO, "Registro");
//		direccion.addBindingTextField(Empleado.this.getDireccion().DIRECCION, "Direccion");
//		direccion.addBindingTextField(Empleado.this.getDireccion().LOCALIDAD, "Localidad");
//		direccion.addBindingIntegerField(Empleado.this.getDireccion().TELEFONO, "Telefono");
//		direccion.addBindingIntegerField(Empleado.this.getDireccion().COD_POSTAL, "CodPostal");
//		ACA ES EN DONDE ME TIRA ERROR CUANDO DIGO Empleado.this ... :(
		
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
					edicion.setModel(table.getSelected());
					edicion.getBotonModificar().setEnabled(true);
					direccion.setModel(table.getSelected());
					direccion.getBotonModificar().setEnabled(true);
					licencia.setModel(table.getSelected());
					licencia.getBotonModificar().setEnabled(true);
				}
			}
		});
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				edicion.setModel(comboBox.getSelectedItem());
				edicion.getBotonModificar().setEnabled(true);				
				direccion.setModel(comboBox.getSelectedItem());
				direccion.getBotonModificar().setEnabled(true);
				licencia.setModel(table.getSelected());
				licencia.getBotonModificar().setEnabled(true);
			
			}
		});
		direccion.getBotonAgregar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tablaList.add(direccion.getModel());
				dao.save(direccion.getModel());
				direccion.setModel(new Empleado());
				SwingUtilities.updateComponentTreeUI(EmpleadoUi.this);
			}	
		});
	
		direccion.getBotonModificar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Empleado model = direccion.getModel();
				tablaList.remove(model);
				tablaList.add(model);
				dao.update(model);
				direccion.setModel(new Empleado());
				direccion.getBotonModificar().setEnabled(false);
				SwingUtilities.updateComponentTreeUI(EmpleadoUi.this);
			}
		});
		direccion.getBotonCancelar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				direccion.setModel(new Empleado());
				direccion.getBotonModificar().setEnabled(false);
			}
		});
		licencia.getBotonAgregar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tablaList.add(licencia.getModel());
				dao.save(licencia.getModel());
				licencia.setModel(new Empleado());
				SwingUtilities.updateComponentTreeUI(EmpleadoUi.this);
			}	
		});
	
		licencia.getBotonModificar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Empleado model = licencia.getModel();
				tablaList.remove(model);
				tablaList.add(model);
				dao.update(model);
				licencia.setModel(new Empleado());
				licencia.getBotonModificar().setEnabled(false);
				SwingUtilities.updateComponentTreeUI(EmpleadoUi.this);
			}
		});
		licencia.getBotonCancelar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				licencia.setModel(new Empleado());
				licencia.getBotonModificar().setEnabled(false);
			}
		});

	}

public static void main(String[] args) {
//    BasicConfigurator.configure();
//    Logger.getAnonymousLogger().setLevel(Level.INFO);
	new EmpleadoUi();
}


}