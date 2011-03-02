package ar.com.syr.trasportes.dao;

import ar.com.syr.trasportes.bean.Empleado;


public class EmpleadoDao extends GenericDao<Empleado>  {
	
	public EmpleadoDao() {
		super(Empleado.class, "Empleado");
	}

}
