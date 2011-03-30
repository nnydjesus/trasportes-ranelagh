package ar.com.syr.transportes.ui.amb;

import java.util.ArrayList;
import java.util.List;

import ar.com.nny.base.ui.swing.components.AbstractBindingPanel;
import ar.com.nny.base.ui.swing.components.GeneralFrame;
import ar.com.nny.base.ui.swing.components.GeneralTable;
import ar.com.nny.base.ui.swing.components.Generator;
import ar.com.syr.transportes.bean.Sancion;

public class SancionesUI extends GeneralFrame<Sancion> {

	public SancionesUI() {
		super("Sancion",Sancion.class);
	
	}
	private List<Sancion> sanciones;
	private GeneralTable tablaSanciones;
	
	@Override
	protected void createForm(AbstractBindingPanel<Sancion> edicion2) {
		sanciones = new ArrayList<Sancion>();
		edicion.addBindingTextField(Sancion.CANTDEDIAS, "CantDeDias");
        edicion.addBindingDateField(Sancion.FECHA, "Fecha");
        tablaSanciones = Generator.GENERATE_TABLE(sanciones, new Sancion().atributos()); 

	}

}
