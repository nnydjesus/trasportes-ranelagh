package ar.com.syr.trasportes.initialData;

import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;

import ar.com.mindset.flexy.util.RandomUtils;
import ar.com.syr.trasportes.bean.CostoEmpleado;
import ar.com.syr.trasportes.bean.Direccion;
import ar.com.syr.trasportes.bean.Empleado;
import ar.com.syr.trasportes.bean.Licencia;
import ar.com.syr.trasportes.dao.CostoEmpleadoDao;
import ar.com.syr.trasportes.dao.EmpleadoDao;
import ar.com.syr.trasportes.dao.GenericDao;


public class DataGeneratorEmpleado {
private EmpleadoDao dao = new EmpleadoDao();
private CostoEmpleadoDao daoCosto = new CostoEmpleadoDao();
	
	void generateEmpleados(){
		for (int i = 0; i < 10; i++) {
			Empleado newEmpleado = this.newEmpleado(i);
			CostoEmpleado costo= new CostoEmpleado();
			newEmpleado.setCostoEmpleado(costo);
//			costo.setEmpleado(newEmpleado);
			costo.setId(RandomStringUtils.randomAlphanumeric(4));
			daoCosto.save(costo);
			dao.save(newEmpleado);
		}
	}

	private Empleado newEmpleado(int i) {
		Empleado empleado = new Empleado();
		empleado.setApellido("Apellido "+i);
		empleado.setCuil(i*20+"");
		empleado.setDni(i*3522);
		empleado.setLegajo("Legajo "+i);
		empleado.setNombre("Nombre "+i);
		empleado.setPropio(true);
		empleado.setRegistro(2542*i);
		Direccion direccion = new Direccion();
		direccion.setCodPostal(i*55);
		direccion.setDireccion("Calle "+i);
		direccion.setLocalidad("Localidad "+i);
		direccion.setTelefono(i*60);
		empleado.setDireccion(direccion);
		Licencia licencia = new Licencia();
		licencia.setCategoria(new Date(22254));
		licencia.setCnrt(new Date());
		licencia.setLibretaSanitaria(new Date(555454));
		licencia.setRegistro(new Date(999595955));
		empleado.setLicencia(licencia);
		return empleado;
	}
	
}
