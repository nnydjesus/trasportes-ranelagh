package ar.com.nny.base.initialData;

import ar.com.nny.base.bean.Usuario;
import ar.com.nny.base.configuration.InitialDataGenerator;
import ar.com.nny.base.dao.GenericDao;

public class DataGeneratorUser   extends InitialDataGenerator<Usuario> {
	private GenericDao<Usuario> dao = new GenericDao<Usuario>(Usuario.class, "Usuario");
	
	@DataGeneratorMethod
	public void generateEmpleados(){
		dao.save(new Usuario("user", "1234"));
		
	}

}
