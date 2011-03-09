package ar.com.syr.transportes.initialData;

import ar.com.mindset.flexy.util.HashUtils;
import ar.com.nny.base.bean.Usuario;
import ar.com.nny.base.dao.GenericDao;
import ar.com.nny.base.utils.HibernateUtil;

public class GeneralGenerator {
	
	public static void main(String[] args) {
//		HibernateUtil.getConfigurationGenerator();
//		new DataGeneratorRemito().generateRemitos();
//		new DataGeneratorEmpleado().generateEmpleados();
		new GenericDao<Usuario>(Usuario.class, "Usuario").save(new Usuario("user", HashUtils.hash("1234")));

	}

}
