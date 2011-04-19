package ar.com.syr.transportes.categoria;

import javax.swing.JFrame;

import ar.com.nny.base.common.Item;
import ar.com.nny.base.common.ItemComposite;

public class Operaciones extends ItemComposite {
	private static final long serialVersionUID = -6130962545676299492L;

	public Operaciones(JFrame parent) {
	    this.add(new Cocacola());
	    this.add(new LomaNegra(parent));
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
		new Operaciones(null);
	}

    @Override
    public void updateTree() {
    }

	

}
