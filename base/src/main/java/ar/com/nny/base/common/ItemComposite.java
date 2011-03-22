package ar.com.nny.base.common;

import java.util.Vector;

@SuppressWarnings("serial")
abstract public class ItemComposite extends Vector<Object> implements Item {

	abstract public String toString() ;
	
	@Override
	public void mostrar() {		
	}

	public abstract void mostrar(Object item); 

}
