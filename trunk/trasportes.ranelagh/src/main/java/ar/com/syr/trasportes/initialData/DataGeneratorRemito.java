package ar.com.syr.trasportes.initialData;

import java.util.Date;
import java.util.List;

import ar.com.syr.trasportes.bean.Empleado;
import ar.com.syr.trasportes.bean.Remito;
import ar.com.syr.trasportes.dao.EmpleadoDao;
import ar.com.syr.trasportes.dao.RemitoDao;



public class DataGeneratorRemito{
	private RemitoDao dao = new RemitoDao();;
	
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
