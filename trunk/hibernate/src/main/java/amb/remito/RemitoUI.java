package amb.remito;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import model.Remito;
import ui.GeneralTable;
import ui.MyJComboBox;
import utils.Generator;
import utils.GenericDao;
import utils.MouseClicked;
import utils.Observable;
import amb.PanelEdicion;

import common.Item;


public class RemitoUI extends JFrame implements Item{
	
	private List<Observable> tablaList;
	private PanelEdicion<Remito> edicion = new PanelEdicion<Remito>("Remito", new Remito());
	private GeneralTable table;
	private GenericDao<Observable> dao = new GenericDao<Observable>(Remito.class, "Remito");
	private JTabbedPane panel = new JTabbedPane();
	private MyJComboBox comboBox;


	public RemitoUI() {
		tablaList = dao.getAll();
		this.table = Generator.GENERATE_TABLE(tablaList, Remito.atributos());
		this.createForm();
		panel.addTab("General", edicion);
		panel.addTab("Tabla", table);
		this.add(panel);
		this.setSize(1024, 780);
		this.setVisible(false);
	}
	
	protected void createForm() {
		this.comboBox = new MyJComboBox(tablaList);
		edicion.addComponent("Seleccione El Remito", comboBox);
		edicion.addBindingDateField(Remito.FECHA, "Fecha");
		edicion.addBindingTextField(Remito.ORIGEN, "Origen");
		edicion.addBindingTextField(Remito.DESTINO, "Destino");
		edicion.addBindingTextField(Remito.NRO_REMITO_1, "Remito");
		edicion.addBindingDoubleField(Remito.COSTO, "Costo");
		edicion.addBindingDoubleField(Remito.CHOFER, "Chofer");
		edicion.addBindingDoubleField(Remito.COMBUSTIBLE, "Combustible");
		edicion.addBindingDoubleField(Remito.LITROS, "Litros");
		edicion.addBindingTextField(Remito.NRO_REMITO_2, "Remito");
		edicion.addBindingTextField(Remito.LUGAR, "Lugar");
		edicion.addBindingIntegerField(Remito.KM, "Kilometros");
		edicion.addBindingTextField(Remito.PATENTE, "Patente");
		
		edicion.getBotonAgregar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tablaList.add(edicion.getModel());
				dao.save(edicion.getModel());
				edicion.setModel(new Remito());
				SwingUtilities.updateComponentTreeUI(RemitoUI.this);
			}
		});
		
		edicion.getBotonModificar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Remito model = edicion.getModel();
				tablaList.remove(model);
				tablaList.add(model);
				dao.update(model);
				edicion.setModel(new Remito());
				edicion.getBotonModificar().setEnabled(false);
				SwingUtilities.updateComponentTreeUI(RemitoUI.this);
			}
		});
		edicion.getBotonCancelar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				edicion.setModel(new Remito());
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

	public static void main(String[] args) {
//        BasicConfigurator.configure();
//        Logger.getAnonymousLogger().setLevel(Level.INFO);
		new RemitoUI();
	}
	
	@Override
	public String toString() {
		return "Remito";
	}

	@Override
	public void mostrar() {
		setVisible(true);		
	}
	
}
