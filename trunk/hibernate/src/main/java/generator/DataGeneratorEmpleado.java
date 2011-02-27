package generator;

import java.util.Date;

import model.Direccion;
import model.Empleado;
import model.Licencia;
import utils.GenericDao;

public class DataGeneratorEmpleado {
private GenericDao<Empleado> dao = new GenericDao<Empleado>(Empleado.class, "Empleado");
	
	void generateEmpleados(){
		for (int i = 0; i < 10; i++) {
			Empleado newEmpleado = this.newEmpleado(i);
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
