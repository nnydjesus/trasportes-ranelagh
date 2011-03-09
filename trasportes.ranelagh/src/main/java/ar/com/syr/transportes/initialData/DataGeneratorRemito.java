package ar.com.syr.transportes.initialData;

import java.util.Date;

import ar.com.nny.base.configuration.InitialDataGenerator;
import ar.com.nny.base.dao.GenericDao;
import ar.com.nny.base.initialData.DataGeneratorMethod;
import ar.com.syr.transportes.bean.Remito;



public class DataGeneratorRemito extends InitialDataGenerator<Remito>{
	private GenericDao<Remito> dao = new GenericDao<Remito>(Remito.class, "Remito");;
	
	@DataGeneratorMethod
	public void generateRemitos(){
		for (int i = 0; i < 10; i++) {
			Remito newRemito = this.newRemito(i);
			dao.save(newRemito);
		}
	}

	private Remito newRemito(int i) {
		Remito remito = new Remito();
		remito.setChofer(i*100.0);
		remito.setFecha(new Date());
		remito.setOrigen("Origen "+i);
		remito.setDestino("Destino "+i);
		remito.setId("remito "+i*55.36);
		remito.setNroRemito2("remito "+i*55.36);
		remito.setCosto(i*55.3);
		remito.setCombustible(i*80.4); 
		remito.setLitros(i*5.6);
		remito.setLugar("Lugar "+i);
		remito.setKm(i);
		remito.setPeaje(i*3.2);
		remito.setPatente("Patente "+i);
		return remito;
	}
	
	public static void main(String[] args) {
		new DataGeneratorRemito().generateRemitos();
	}

}
