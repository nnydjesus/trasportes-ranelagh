package ar.com.syr.transportes.ui.amb;

import ar.com.nny.base.ui.swing.components.GeneralFrame;
import ar.com.syr.transportes.bean.Vacacion;

public class VacacionesUI extends GeneralFrame<Vacacion> {

	
    public VacacionesUI() {
        super("Vacacion",Vacacion.class);
    }

    @Override
    public Class abmClass() {
        return ABMVacaciones.class;
    }
    
    public static void main(String[] args) {
        new VacacionesUI().setVisible(true);
    }
	
}
