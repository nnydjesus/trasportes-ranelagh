package ar.com.syr.trasportes.costos;

import ar.com.nny.java.base.common.Item;
import ar.com.nny.java.base.ui.GeneralFrame;
import ar.com.syr.trasportes.bean.CostoEmpleado;
import ar.com.syr.trasportes.dao.CostoEmpleadoDao;

public class CostoEmpleadoUi extends GeneralFrame<CostoEmpleado> implements Item{

	public CostoEmpleadoUi() {
		super("CostoEmpleado", CostoEmpleado.class);
		super.addActions();
	}	

	@Override
	protected void createForm() {
//		comboBox = new MyJComboBox(((CostoEmpleadoDao) dao).getAllIdsEmpleados());
		edicion.addComponent("Seleccione El Empleado", comboBox);
//		edicion.addBindingTextField(CostoEmpleado.EMPLEADO, "Legajo");
		edicion.addBindingDoubleField(CostoEmpleado.COSTO_TOTAL, "Costo");
		
	}

	@Override
	protected void createDao() {
		dao = new CostoEmpleadoDao();		
	}

	@Override
	protected CostoEmpleado getDefaultModel() {
		return new CostoEmpleado();
	}
	
}
