package ar.com.syr.transportes.ui.amb;

import ar.com.nny.base.ui.swing.components.GeneralFrame;
import ar.com.syr.transportes.bean.Ausencia;

public class AusenciaUI extends GeneralFrame<Ausencia> {

	public AusenciaUI() {
		super("Ausencia",Ausencia.class);
	}

    @Override
    public Class abmClass() {
        return ABMAusencias.class;
    }

}
