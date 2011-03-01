package ar.com.syr.trasportes.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import main.PrintUtilities;
import ar.com.syr.trasportes.bean.Beans;
import ar.com.syr.trasportes.common.Item;
import ar.com.syr.trasportes.dao.GenericDao;
import ar.com.syr.trasportes.ui.amb.PanelEdicion;
import ar.com.syr.trasportes.utils.Generator;
import ar.com.syr.trasportes.utils.IdentificablePersistentObject;
import ar.com.syr.trasportes.utils.Observable;

public abstract class GeneralFrame<T extends IdentificablePersistentObject> extends JFrame implements Item{
	
	private TopPanel topPanel;
	protected GeneralTable table;
	protected JTabbedPane panel = new JTabbedPane();
	protected PanelEdicion<T> edicion;
	protected GenericDao<IdentificablePersistentObject> dao;
	protected List<IdentificablePersistentObject> tablaList;
	private String nombre;



	public GeneralFrame(String name, Class clase) {
		this.nombre = name;
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		T newInstance;
		try {
			newInstance = (T) clase.newInstance();
			edicion = new PanelEdicion<T>(name, newInstance);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		dao = new GenericDao<IdentificablePersistentObject>(clase, name);
		tablaList = dao.getAll();
		this.topPanel = new TopPanel();
		this.table = Generator.GENERATE_TABLE(tablaList, newInstance.atributos());
		this.createForm();
		this.addPanels();
		this.add(topPanel);
		this.add(panel);
		this.setSize(1024, 780);
		this.setVisible(false);
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
	}
	
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

