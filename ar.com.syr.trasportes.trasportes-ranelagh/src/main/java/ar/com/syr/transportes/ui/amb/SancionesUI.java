package ar.com.syr.transportes.ui.amb;

import ar.com.nny.base.ui.swing.components.GeneralFrame;
import ar.com.syr.transportes.bean.Sancion;

public class SancionesUI extends GeneralFrame<Sancion> {

	public SancionesUI() {
		super("Sancion",Sancion.class);
	
	}

    @Override
    public Class abmClass() {
        return ABMSanciones.class;
    }
    public static void main(String[] args) {
        new SancionesUI().setVisible(true);
    }
}
