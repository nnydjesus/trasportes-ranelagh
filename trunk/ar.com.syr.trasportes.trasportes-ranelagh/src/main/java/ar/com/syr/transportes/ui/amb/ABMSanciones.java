package ar.com.syr.transportes.ui.amb;

import javax.swing.JFrame;

import ar.com.nny.base.ui.swing.components.AbstractBindingPanel;
import ar.com.nny.base.ui.swing.components.abms.ABMFrame;
import ar.com.syr.transportes.bean.Sancion;
import ar.com.syr.transportes.search.HomeEmpleado;

public class ABMSanciones extends ABMFrame<Sancion> {

	public ABMSanciones(JFrame parent) {
		super(new Sancion(), parent);
	
	}
	
	@Override
	protected void createForm(AbstractBindingPanel<Sancion> edicion2) {
		edicion2.addBindingIntegerField(Sancion.CANT_DE_DIAS, "CantDeDias");
		edicion2.addBindingDateField(Sancion.FECHA, "Fecha");
		edicion2.addBindingComboBox(Sancion.EMPLEADO, HomeEmpleado.getInstance().getAll());

	}

}
