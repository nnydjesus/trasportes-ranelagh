package utils;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Remito;

import org.apache.log4j.BasicConfigurator;
import org.hibernate.Session;


public class GenericDao<T> {

	private Class t;
	private String className;

	public GenericDao(Class t, String className) {
        BasicConfigurator.configure();
        Logger.getAnonymousLogger().setLevel(Level.INFO);
		this.t = t;
		this.className =className;
	}

	public void save(T object) {
		Session s = HibernateUtil.getSession();
		s.beginTransaction();
		s.save(object);
		s.getTransaction().commit();
	}

	public void update(T object) {
		Session s = HibernateUtil.getSession();
		s.beginTransaction();
		s.update(object);
		s.getTransaction().commit();
	}

	public List<T> getAll() {
		Session s = HibernateUtil.getSession();
		List<T> vuelos = s.createQuery("from "+this.className).list();
		return vuelos;
	}

}
