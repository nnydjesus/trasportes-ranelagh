package ar.com.syr.transportes.initialData;

import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;

import ar.com.nny.base.generator.InitialDataGenerator;
import ar.com.nny.base.generator.annotations.DataGeneratorMethod;
import ar.com.syr.transportes.bean.CostoEmpleado;
import ar.com.syr.transportes.bean.Direccion;
import ar.com.syr.transportes.bean.Empleado;
import ar.com.syr.transportes.bean.Licencia;
import ar.com.syr.transportes.bean.enums.Categoria;
import ar.com.syr.transportes.dao.CostoEmpleadoDao;
import ar.com.syr.transportes.dao.EmpleadoDao;


public class DataGeneratorEmpleado  extends InitialDataGenerator<Empleado> {
	private EmpleadoDao dao = new EmpleadoDao();
	private CostoEmpleadoDao daoCosto = new CostoEmpleadoDao();
	

	@DataGeneratorMethod
	public void generateEmpleados(){
//		List<Remito> remitos = new GenericDao<Remito>(Remito.class, "Remito").getAll();
		for (int i = 0; i < 10; i++) {
			Empleado newEmpleado = this.newEmpleado(i);
			CostoEmpleado costo= new CostoEmpleado();
//			newEmpleado.setCostoEmpleado(costo);
//			newEmpleado.addRemito(remitos.get(i));
			costo.setId(RandomStringUtils.randomAlphanumeric(4));
			newEmpleado.setCostoEmpleado(costo);
			daoCosto.save(costo);
			costo.setEmpleado(newEmpleado);
			dao.save(newEmpleado);
			daoCosto.update(costo);
		}
	}

	private Empleado newEmpleado(int i) {
		Empleado empleado = new Empleado();
		empleado.setApellido("Apellido "+i);
		empleado.setCuil(i*20+"");
		empleado.setDni(i*3522);
		empleado.setId("Legajo "+i);
		empleado.setNombre("Nombre "+i);
		empleado.setPropio(true);
		empleado.setRegistroConducir(""+2542*i);
		empleado.setCategoria(Categoria.CHOFER);
		Direccion direccion = new Direccion();
		direccion.setCodPostal(i*55);
		direccion.setCalle("Calle "+i);
		direccion.setLocalidad("Localidad "+i);
		direccion.setTelefono(i*60);
		empleado.setDireccion(direccion);
		Licencia licencia = new Licencia();
		empleado.setFechaDeNacimiento(new Date(22254));
		licencia.setCnrt(new Date());
		licencia.setLibretaSanitaria(new Date(555454));
		licencia.setRegistro(new Date(999595955));
		empleado.setLicencia(licencia);
		return empleado;
	}
	
	public static void main(String[] args) {
        new DataGeneratorEmpleado().generateEmpleados();
    }
	
}
