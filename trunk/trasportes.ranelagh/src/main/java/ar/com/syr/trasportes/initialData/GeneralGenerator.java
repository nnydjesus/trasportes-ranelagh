package ar.com.syr.trasportes.initialData;

import ar.com.nny.java.base.bean.Usuario;
import ar.com.nny.java.base.dao.GenericDao;

public class GeneralGenerator {
	
	public static void main(String[] args) {
		new DataGeneratorRemito().generateRemitos();
		new DataGeneratorEmpleado().generateEmpleados();
		new GenericDao<Usuario>(Usuario.class, "Usuario").save(new Usuario("ronny", "1234"));

	}

}
