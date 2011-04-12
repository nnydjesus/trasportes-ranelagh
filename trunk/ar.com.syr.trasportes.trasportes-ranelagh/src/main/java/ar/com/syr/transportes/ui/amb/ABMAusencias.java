package ar.com.syr.transportes.ui.amb;

import javax.swing.JFrame;

import ar.com.nny.base.ui.swing.components.AbstractBindingPanel;
import ar.com.nny.base.ui.swing.components.abms.ABMFrame;
import ar.com.syr.transportes.bean.Ausencia;

public class ABMAusencias extends ABMFrame<Ausencia> {

    public ABMAusencias(JFrame parent) {
        super( new Ausencia(), parent);
    }

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
