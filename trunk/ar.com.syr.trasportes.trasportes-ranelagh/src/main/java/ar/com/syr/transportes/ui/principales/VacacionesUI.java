package ar.com.syr.transportes.ui.principales;

import ar.com.nny.base.ui.swing.components.GeneralFrame;
import ar.com.syr.transportes.bean.Vacacion;
import ar.com.syr.transportes.ui.amb.ABMVacaciones;

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
