package ar.com.syr.transportes.ui.amb;

import ar.com.nny.base.ui.swing.components.AbstractBindingPanel;
import ar.com.nny.base.ui.swing.components.GeneralFrame;
import ar.com.syr.transportes.bean.Vacacion;

public class VacacionesUI extends GeneralFrame<Vacacion> {

	
    public VacacionesUI() {
        super("Vacacion",Vacacion.class);
    }
	
	@Override
	protected void createForm(AbstractBindingPanel<Vacacion> edicion2) {
		
		edicion2.addBindingTextField(Vacacion.NOMBRE, "Nombre");
		edicion2.addBindingTextField(Vacacion.APELLIDO, "Apellido");
		edicion2.addBindingTextField(Vacacion.LEGAJO, "Legajo");
		edicion2.addBindingDateField(Vacacion.DESDE, "Desde");
		edicion2.addBindingDateField(Vacacion.HASTA, "Hasta");
   }
public static void main(final String[] args) {
		        new VacacionesUI().setVisible(true);
		        }	
}
