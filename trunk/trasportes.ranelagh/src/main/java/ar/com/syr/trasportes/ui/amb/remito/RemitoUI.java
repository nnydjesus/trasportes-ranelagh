package ar.com.syr.trasportes.ui.amb.remito;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import ar.com.syr.trasportes.bean.Remito;
import ar.com.syr.trasportes.common.Item;
import ar.com.syr.trasportes.dao.GenericDao;
import ar.com.syr.trasportes.dao.RemitoDao;
import ar.com.syr.trasportes.ui.GeneralFrame;
import ar.com.syr.trasportes.ui.GeneralTable;
import ar.com.syr.trasportes.ui.MyJComboBox;
import ar.com.syr.trasportes.ui.amb.PanelEdicion;
import ar.com.syr.trasportes.utils.Generator;
import ar.com.syr.trasportes.utils.MouseClicked;
import ar.com.syr.trasportes.utils.Observable;



public class RemitoUI extends GeneralFrame<Remito> implements Item{
	private static final long serialVersionUID = -4654971392742688838L;
	


	public RemitoUI() {
		super("Remito", Remito.class);
		super.addActions();
	}
	
	@Override
	protected void createForm() {
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
	
}
