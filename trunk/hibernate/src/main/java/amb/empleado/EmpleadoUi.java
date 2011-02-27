package amb.empleado;

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

import common.Item;

import model.Empleado;
import ui.GeneralTable;
import ui.MyJComboBox;
import utils.Generator;
import utils.GenericDao;
import utils.MouseClicked;
import utils.Observable;
import amb.PanelEdicion;


public class EmpleadoUi extends JFrame implements Item {

	private List<Observable> tablaList;
	private PanelEdicion<Empleado> edicion = new PanelEdicion<Empleado>("Empleado", new Empleado());
	private GeneralTable table;
	private GenericDao<Observable> dao = new GenericDao<Observable>(Empleado.class, "Empleado");
	private JTabbedPane panel = new JTabbedPane();
	private MyJComboBox comboBox;


	public EmpleadoUi() {
		tablaList = dao.getAll();
		this.table = Generator.GENERATE_TABLE(tablaList, Empleado.atributos());
		this.createForm();
		panel.addTab("General", edicion);
		panel.addTab("Tabla", table);
		this.add(panel);
		this.setSize(1024, 780);
		this.setVisible(false);
	}
	
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
			}
		}
	});
	
	comboBox.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			edicion.setModel(comboBox.getSelectedItem());
			edicion.getBotonModificar().setEnabled(true);				
		}
	});
	
	
}
	
	public void mostrar(){
		setVisible(true);
	}

public static void main(String[] args) {
//    BasicConfigurator.configure();
//    Logger.getAnonymousLogger().setLevel(Level.INFO);
	new EmpleadoUi();
}

@Override
public String toString() {
	return "Empleado";
}

}