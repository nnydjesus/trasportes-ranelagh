package ar.com.syr.transportes.ui;

import javax.swing.SwingUtilities;

import ar.com.nny.base.common.Item;
import ar.com.syr.transportes.bean.Empleado;
import ar.com.syr.transportes.ui.amb.ABMEmpleado;

public class Itemempleado implements Item  {
	private Empleado empleado;
	private ABMEmpleado empleadoUi;
	
	public Itemempleado(Empleado empl, ABMEmpleado eplUi) {
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

    @Override
    public void update() {
        SwingUtilities.updateComponentTreeUI(empleadoUi);
        
    }


}
