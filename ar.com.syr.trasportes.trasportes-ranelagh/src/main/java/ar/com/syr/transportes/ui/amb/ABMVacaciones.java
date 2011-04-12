package ar.com.syr.transportes.ui.amb;

import javax.swing.JFrame;

import ar.com.nny.base.ui.swing.components.AbstractBindingPanel;
import ar.com.nny.base.ui.swing.components.abms.ABMFrame;
import ar.com.syr.transportes.bean.Vacacion;

public class ABMVacaciones extends ABMFrame<Vacacion> {

	
    public ABMVacaciones(JFrame parent) {
        super(new Vacacion(), parent);
    }
	
	@Override
	protected void createForm(AbstractBindingPanel<Vacacion> edicion2) {
		
		edicion2.addBindingTextField(Vacacion.NOMBRE, "Nombre");
		edicion2.addBindingTextField(Vacacion.APELLIDO, "Apellido");
		edicion2.addBindingTextField(Vacacion.LEGAJO, "Legajo");
		edicion2.addBindingDateField(Vacacion.DESDE, "Desde");
		edicion2.addBindingDateField(Vacacion.HASTA, "Hasta");
   }
}
