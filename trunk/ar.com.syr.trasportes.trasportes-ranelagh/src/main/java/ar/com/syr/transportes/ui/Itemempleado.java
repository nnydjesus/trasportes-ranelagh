package ar.com.syr.transportes.ui;

import ar.com.nny.base.common.Item;
import ar.com.syr.transportes.bean.Empleado;
import ar.com.syr.transportes.ui.amb.empleado.EmpleadoUi;

public class Itemempleado implements Item  {
	private Empleado empleado;
	private EmpleadoUi empleadoUi;
	
	public Itemempleado(Empleado empl, EmpleadoUi eplUi) {
		this.empleado =empl;
		this.empleadoUi = eplUi;
	}

	@Override
	public String toString() {
		return empleado.mostrar();
	}

	@Override
	public void mostrar() {
		empleadoUi.setModel(empleado);
		empleadoUi.mostrar();
	}


}
