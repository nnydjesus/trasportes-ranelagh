package ar.com.syr.trasportes.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import ar.com.nny.java.base.dao.GenericDao;
import ar.com.syr.trasportes.bean.CostoEmpleado;
import ar.com.syr.trasportes.bean.Empleado;


@SuppressWarnings("unchecked")
public class EmpleadoDao extends GenericDao<Empleado>  {
	
	public EmpleadoDao() {
		super(Empleado.class, "Empleado");
	}
	
	@Override
	public List<Empleado> getAll() {
		List<Empleado> all = super.getAll();
		for (Empleado empleado : all) {
			Hibernate.initialize(empleado.getRemitos());
		}
		return all;
	}
	
	public List<CostoEmpleado> getAllCostoEmpleado() {
		Session s = session();
		return (List<CostoEmpleado>) s
		 .createQuery("select this.costo from "+this.getClassName() ).list();
	}
	
	

}
