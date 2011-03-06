package ar.com.syr.trasportes.common;

import ar.com.nny.java.base.common.ItemComposite;
import ar.com.syr.trasportes.costos.CostoEmpleadoUi;
import ar.com.syr.trasportes.ui.amb.empleado.EmpleadoUi;
import ar.com.syr.trasportes.ui.amb.remito.RemitoUI;



public class Tablas extends ItemComposite {
	private static final long serialVersionUID = -6130962545676299492L;

	@SuppressWarnings("unchecked")
	public Tablas() {
		this.add(new RemitoUI());
		this.add(new EmpleadoUi());
		this.add(new CostoEmpleadoUi());
	}

	@Override
	public String toString() {
		return "Tablas";
	}

	@Override
	public void mostrar() {
	}
	
	public static void main(String[] args) {
		new Tablas();
	}
	

}
