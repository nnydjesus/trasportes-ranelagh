package ar.com.nny.base.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import main.PrintUtilities;
import ar.com.nny.base.common.Item;
import ar.com.nny.base.dao.GenericDao;
import ar.com.nny.base.ui.abm.PanelEdicion;
import ar.com.nny.base.utils.Generator;
import ar.com.nny.base.utils.IdentificablePersistentObject;
import ar.com.nny.base.utils.Observable;

@SuppressWarnings({ "unchecked", "serial" })
public abstract class GeneralFrame<T extends IdentificablePersistentObject> extends JFrame implements Item{
	
	private TopPanel topPanel;
	protected GeneralTable table;
	protected JTabbedPane panel = new JTabbedPane();
	protected PanelEdicion<T> edicion;
	protected GenericDao<IdentificablePersistentObject> dao;
	protected List<IdentificablePersistentObject> tablaList;
	private String nombre;
	protected MyJComboBox comboBox;
	private Class clase;
	protected Boolean tengo;




	public GeneralFrame(String name, Class clase) {
		this.clase = clase;
		this.nombre = name;
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		T newInstance;
		try {
			newInstance = (T) clase.newInstance();
			edicion = new PanelEdicion<T>(name, newInstance);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		createDao();
		tablaList = dao.getAll();
		createComboBox();
		this.topPanel = new TopPanel();
		this.table = createTable(newInstance);
		this.createForm();
		this.addPanels();
		this.addActions();
		this.add(topPanel);
		this.add(panel);
		this.setSize(1024, 780);
		this.setVisible(false);
	}


	protected GeneralTable createTable(T newInstance) {
		return Generator.GENERATE_TABLE(tablaList, newInstance.atributos());
	}


	protected void createComboBox() {
		this.comboBox = new MyJComboBox(tablaList);
	}
	

	protected void createDao() {
		dao = new GenericDao<IdentificablePersistentObject>(clase, nombre);		
	}

	protected void addPanels(){
		panel.addTab("General", edicion);
		panel.addTab("Tabla", table);
	}
	

	protected void addActions() {
		topPanel.getBtImprimir().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PrintUtilities.printComponent(table.getScroll());
				
			}
		});		
		
		topPanel.getBtCerrar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);				
			}
		});
		
		edicion.getBotonAgregar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tengo=true;
				edicionAgregar();
				tengo=false;
			}
		});
	
		edicion.getBotonModificar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				edicionModificar();
			}
		});
		edicion.getBotonCancelar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				edicionCancelar();
			}
		});
		
		table.addtableListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				tableListener(e);
			}
		});
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				comboboxListener();				
			
			}
		});
	}
	
	protected void edicionAgregar() {
		tablaList.add(edicion.getModel());
		dao.save(edicion.getModel());
		edicion.setModel(getDefaultModel());
		SwingUtilities.updateComponentTreeUI(GeneralFrame.this);
	}	
	
	protected void comboboxListener() {
		edicion.setModel(comboBox.getSelectedItem());
		edicion.getBotonModificar().setEnabled(true);
	}
	protected void tableListener(MouseEvent e) {
		if(e.getClickCount() ==2){
			Observable observable = (Observable) table.getSelected();
			edicion.setModel(observable);
			edicion.getBotonModificar().setEnabled(true);
		}
	}
	
	protected void edicionModificar() {
		IdentificablePersistentObject model = edicion.getModel();
		tablaList.remove(model);
		tablaList.add(model);
		dao.update(model);
		edicion.setModel(getDefaultModel());
		edicion.getBotonModificar().setEnabled(false);
		SwingUtilities.updateComponentTreeUI(GeneralFrame.this);
	}
	
	protected void edicionCancelar() {
		edicion.setModel(getDefaultModel());
		edicion.getBotonModificar().setEnabled(false);
	}
	protected abstract T getDefaultModel();

	@Override
	public void mostrar() {
		this.setVisible(true);	
	}
	
	@Override
	public String toString() {
		return nombre;
	}
	
	protected abstract void createForm();

}

