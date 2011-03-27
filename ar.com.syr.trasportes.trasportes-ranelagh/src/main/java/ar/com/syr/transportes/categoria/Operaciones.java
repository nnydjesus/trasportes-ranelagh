package ar.com.syr.transportes.categoria;

import ar.com.nny.base.common.Item;
import ar.com.nny.base.common.ItemComposite;
import ar.com.syr.transportes.costos.CostoEmpleadoUi;
import ar.com.syr.transportes.ui.amb.RemitoUI;

public class Operaciones extends ItemComposite {
	private static final long serialVersionUID = -6130962545676299492L;

	public Operaciones() {
		this.add(new RemitoUI());
		this.add(new CostoEmpleadoUi());
	}

	@Override
	public String toString() {
		return "Operaciones";
	}

	
	@Override
	public void mostrar(Object item) {
		((Item)item).mostrar();		
	}
	
	
	public static void main(String[] args) {
		new Operaciones();
	}

	

}
