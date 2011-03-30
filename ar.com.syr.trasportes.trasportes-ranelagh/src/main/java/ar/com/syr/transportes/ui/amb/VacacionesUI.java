package ar.com.syr.transportes.ui.amb;

import java.util.ArrayList;
import java.util.List;

import ar.com.nny.base.ui.swing.components.AbstractBindingPanel;
import ar.com.nny.base.ui.swing.components.GeneralFrame;
import ar.com.nny.base.ui.swing.components.GeneralTable;
import ar.com.nny.base.ui.swing.components.Generator;
import ar.com.syr.transportes.bean.Vacacion;

public class VacacionesUI extends GeneralFrame<Vacacion> {
		
	public VacacionesUI() {
		super("Vacacion",Vacacion.class);
	}
// Lo del legajo lo dejo asi, pero.. en realidad para mi,
// dependiendo de en que lugar se quiera colocar esta ui,
// el legajo va ingresarse por medio de un textField o 
// se selecciona en un comboBox
	
	private List<Vacacion> vacaciones;
	private GeneralTable tablaVacaciones;
	@Override
	protected void createForm(AbstractBindingPanel<Vacacion> edicion2) {
		vacaciones = new ArrayList<Vacacion>();
		
		edicion.addBindingTextField(Vacacion.NOMBRE, "Nombre");
        edicion.addBindingTextField(Vacacion.APELLIDO, "Apellido");
        edicion.addBindingTextField(Vacacion.LEGAJO, "Legajo");
        edicion.addBindingDateField(Vacacion.DESDE, "Desde");
        edicion.addBindingDateField(Vacacion.HASTA, "Hasta");
        tablaVacaciones = Generator.GENERATE_TABLE(vacaciones, new Vacacion().atributos()); 
   }
public static void main(final String[] args) {
		        new VacacionesUI();
		        }	
}
