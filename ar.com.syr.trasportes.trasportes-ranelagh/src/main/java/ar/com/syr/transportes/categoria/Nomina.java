package ar.com.syr.transportes.categoria;

import java.util.Collection;

import ar.com.nny.base.common.Item;
import ar.com.nny.base.common.ItemComposite;
import ar.com.syr.transportes.bean.Empleado;
import ar.com.syr.transportes.ui.Itemempleado;
import ar.com.syr.transportes.ui.amb.empleado.EmpleadoUi;



@SuppressWarnings("serial")
public class Nomina extends ItemComposite {
	EmpleadoUi empleadoUi = new EmpleadoUi();

	public Nomina()  {
		this.addAll(empleadoUi.getObjects());
	}
	
	
	@Override
	public synchronized boolean addAll(Collection<? extends Object> c) {
		for (Object object : c) {
			this.add(new Itemempleado((Empleado) object, empleadoUi));
		}
		return true;
	}

	@Override
	public String toString() {
		return "Nomina";
	}

	@Override
	public void mostrar() {
	}
	
	@Override
	public void mostrar(Object item) {
		empleadoUi.setModel((Empleado) item);
	}
	
	public static void main(String[] args) {
		new Operaciones();
	}

	

}
