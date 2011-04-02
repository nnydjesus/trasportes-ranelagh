package ar.com.syr.transportes.ui.amb;

import ar.com.nny.base.ui.swing.components.AbstractBindingPanel;
import ar.com.nny.base.ui.swing.components.GeneralFrame;
import ar.com.syr.transportes.bean.Sancion;

public class SancionesUI extends GeneralFrame<Sancion> {

	public SancionesUI() {
		super("Sancion",Sancion.class);
	
	}
	
	@Override
	protected void createForm(AbstractBindingPanel<Sancion> edicion2) {
		edicion2.addBindingIntegerField(Sancion.CANT_DE_DIAS, "CantDeDias");
		edicion2.addBindingDateField(Sancion.FECHA, "Fecha");

	}

}
