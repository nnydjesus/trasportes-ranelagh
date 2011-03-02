package ar.com.syr.trasportes.dao;

import java.util.List;

import org.hibernate.Session;

import ar.com.syr.trasportes.bean.CostoEmpleado;
import ar.com.syr.trasportes.bean.Empleado;
import ar.com.syr.trasportes.utils.HibernateUtil;
import ar.com.syr.trasportes.utils.QueryStatement;

public class CostoEmpleadoDao<IdentificablePersistentObject> extends GenericDao<CostoEmpleado>{

	public CostoEmpleadoDao() {
		super(CostoEmpleado.class, "CostoEmpleado");
	}
	
	public CostoEmpleado getByEmpleado(Empleado empleado) {
		return (CostoEmpleado) new QueryStatement<CostoEmpleado>("from "+this.getPersistentClass() +" this where this.empleado = ?", empleado).find();
	}
	
	@Override
	public List<CostoEmpleado> getAll() {
		Session s = HibernateUtil.getSession();
		List<CostoEmpleado> vuelos = s.createQuery("from "+this.getClassName()).list();
		return vuelos;
	}
	
	public List<String> getAllIdsEmpleados() {
		Session s = HibernateUtil.getSession();
		List<String> ids = s.createQuery("select id from Empleado").list();
		return ids;
	}

	public static void main(String[] args) {
		CostoEmpleadoDao costoEmpleadoDao = new CostoEmpleadoDao();
		List<CostoEmpleado> all = costoEmpleadoDao.getAll();
		//Empleado empleado = new EmpleadoDao().getAll().get(0);
		//System.out.println(costoEmpleadoDao.getByEmpleado(empleado).getIdd());
	}
	

}
