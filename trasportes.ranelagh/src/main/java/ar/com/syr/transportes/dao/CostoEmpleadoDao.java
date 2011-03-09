package ar.com.syr.transportes.dao;

import java.util.List;

import org.hibernate.Session;

import ar.com.nny.base.dao.GenericDao;
import ar.com.syr.transportes.bean.CostoEmpleado;
import ar.com.syr.transportes.bean.Empleado;

@SuppressWarnings("unchecked")
public class CostoEmpleadoDao<IdentificablePersistentObject> extends GenericDao<CostoEmpleado>{

	public CostoEmpleadoDao() {
		super(CostoEmpleado.class, "CostoEmpleado");
	}
	
	public CostoEmpleado getByEmpleado(Empleado empleado) {
//		QueryStatement<CostoEmpleado> queryStatement = new QueryStatement<CostoEmpleado>("from "+this.getPersistentClass() +" this where this.empleado = ?");
//		queryStatement.addParameter(empleado);
		Session s = session();
		CostoEmpleado costo = (CostoEmpleado) s
		.createQuery("from "+this.getClassName() +" this where this.empleado = :em")
		.setEntity("em", empleado).uniqueResult();
		return costo	;
		
//		return (CostoEmpleado) queryStatement.find();
	}
	
	@Override
	public List<CostoEmpleado> getAll() {
		Session s = session();
		List<CostoEmpleado> vuelos = s.createQuery("from "+this.getClassName()).list();
		return vuelos;
	}
	
	public List<String> getAllIdsEmpleados() {
		Session s =session();
		List<String> ids = s.createQuery("select id from Empleado").list();
		return ids;
	}

	public static void main(String[] args) {
//		CostoEmpleadoDao costoEmpleadoDao = new CostoEmpleadoDao();
//		List<CostoEmpleado> all = costoEmpleadoDao.getAll();
//		//Empleado empleado = new EmpleadoDao().getAll().get(0);
//		System.out.println(costoEmpleadoDao.getByEmpleado(all.get(0).getEmpleado()).getEmpleado().getId());
	}
	

}
