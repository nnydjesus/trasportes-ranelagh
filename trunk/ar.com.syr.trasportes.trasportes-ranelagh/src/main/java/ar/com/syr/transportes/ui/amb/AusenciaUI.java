package ar.com.syr.transportes.ui.amb;

import ar.com.nny.base.ui.swing.components.AbstractBindingPanel;
import ar.com.nny.base.ui.swing.components.GeneralFrame;
import ar.com.syr.transportes.bean.Ausencia;

public class AusenciaUI extends GeneralFrame<Ausencia> {

	public AusenciaUI() {
		super("Ausencia",Ausencia.class);
	}
	@Override
	protected void createForm(AbstractBindingPanel<Ausencia> edicion) {
		edicion.addBindingTextField(Ausencia.LEGAJO, "Legajo");
        edicion.addBindingDateField(Ausencia.APELLIDO,"Apellido");
        edicion.addBindingTextField(Ausencia.NOMBRE, "Nombre");
        edicion.addBindingTextField(Ausencia.MOTIVO, "Motivo");
        edicion.addBindingCheckBox(Ausencia.AVISO, "Aviso");
        edicion.addBindingDateField(Ausencia.FECHA, "Fecha");
        edicion.addBindingDateField(Ausencia.FECHAREINICIO, "FechaReInicio");
        edicion.addBindingDoubleField(Ausencia.COSTO, "Costo");

	}

}
