package ar.com.syr.transportes.costos;

import ar.com.nny.base.common.Item;
import ar.com.nny.base.ui.GeneralFrame;
import ar.com.syr.transportes.bean.CostoEmpleado;
import ar.com.syr.transportes.dao.CostoEmpleadoDao;

@SuppressWarnings("serial")
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

	@SuppressWarnings("unchecked")
	@Override
	protected void createDao() {
		dao = new CostoEmpleadoDao();		
	}

	@Override
	protected CostoEmpleado getDefaultModel() {
		return new CostoEmpleado();
	}
	
}
