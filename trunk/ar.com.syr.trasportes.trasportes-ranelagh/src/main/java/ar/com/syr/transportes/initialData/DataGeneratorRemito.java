package ar.com.syr.transportes.initialData;

import java.util.Date;
import java.util.List;

import ar.com.nny.base.dao.GenericDao;
import ar.com.nny.base.generator.InitialDataGenerator;
import ar.com.nny.base.generator.annotations.DataGeneratorMethod;
import ar.com.syr.transportes.bean.Empleado;
import ar.com.syr.transportes.bean.Remito;
import ar.com.syr.transportes.dao.EmpleadoDao;



public class DataGeneratorRemito extends InitialDataGenerator<Remito>{
	private GenericDao<Remito> dao = new GenericDao<Remito>(Remito.class, "Remito");
	private EmpleadoDao empleadoDao = new EmpleadoDao();

	
	@DataGeneratorMethod
	public void generateRemitos(){
		List<Empleado> empleados = empleadoDao.getAll();
		Empleado empleado = empleados.get(0);

		for (int i = 0; i < 10; i++) {
			Remito newRemito = this.newRemito(i);
			dao.save(newRemito);
			empleado.addRemito(newRemito);
		}
		empleadoDao.update(empleado);
	}

	private Remito newRemito(int i) {
		Remito remito = new Remito();
		remito.setPorcentage(i*3);
		remito.setFecha(new Date());
		remito.setOrigen("Origen "+i);
		remito.setDestino("Destino "+i);
		remito.setId("remito "+i*55.36);
		remito.setCosto(i*55.3);
		remito.setCombustible(i*80.4); 
		remito.setLitros(i*5.6);
		remito.setKm(i);
		remito.setPeaje(i*3.2);
		remito.setPatente("Patente "+i);
		return remito;
	}
	
	public static void main(String[] args) {
		new DataGeneratorRemito().generateRemitos();
	}

}
