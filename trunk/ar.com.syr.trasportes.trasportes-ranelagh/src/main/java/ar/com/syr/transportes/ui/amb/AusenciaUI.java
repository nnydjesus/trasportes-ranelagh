package ar.com.syr.transportes.ui.amb;

import java.util.ArrayList;
import java.util.List;

import ar.com.nny.base.ui.swing.components.AbstractBindingPanel;
import ar.com.nny.base.ui.swing.components.GeneralFrame;
import ar.com.nny.base.ui.swing.components.GeneralTable;
import ar.com.nny.base.ui.swing.components.Generator;
import ar.com.syr.transportes.bean.Ausencia;
import ar.com.syr.transportes.bean.Sancion;

public class AusenciaUI extends GeneralFrame<Ausencia> {

	public AusenciaUI() {
		super("Ausencia",Ausencia.class);
	}
	private List<Ausencia> ausencias;
	private GeneralTable tablaAusencias;
	@Override
	protected void createForm(AbstractBindingPanel<Ausencia> edicion2) {
		ausencias = new ArrayList<Ausencia>();
		edicion.addBindingTextField(Ausencia.LEGAJO, "Legajo");
        edicion.addBindingDateField(Ausencia.APELLIDO,"Apellido");
        edicion.addBindingTextField(Ausencia.NOMBRE, "Nombre");
        edicion.addBindingTextField(Ausencia.MOTIVO, "Motivo");
        edicion.addBindingTextField(Ausencia.AVISO, "Aviso");
        edicion.addBindingTextField(Ausencia.FECHA, "Fecha");
        edicion.addBindingTextField(Ausencia.FECHAREINICIO, "FechaReInicio");
        edicion.addBindingTextField(Ausencia.COSTO, "Costo");
        tablaAusencias = Generator.GENERATE_TABLE(ausencias,new Ausencia().atributos()); 

	}

}
