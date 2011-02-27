package common;

import amb.empleado.EmpleadoUi;
import amb.remito.RemitoUI;
import model.Remito;


public class Tablas extends ItemComposite {
	
	public Tablas() {
		this.add(new RemitoUI());
		this.add(new EmpleadoUi());
	}

	@Override
	public String toString() {
		return "Tablas";
	}

	@Override
	public void mostrar() {
	}

}
