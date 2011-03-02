package ar.com.syr.trasportes.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.log4j.BasicConfigurator;
import org.hibernate.Session;

import ar.com.syr.trasportes.utils.HibernateUtil;
import ar.com.syr.trasportes.utils.IdentificablePersistentObject;
import ar.com.syr.trasportes.utils.Observable;



public class GenericDao<T extends IdentificablePersistentObject> extends GenericFlexyDAO<T>{

	private Class t;
	private String className;

	public GenericDao(Class t, String className) {
        BasicConfigurator.configure();
        Logger.getAnonymousLogger().setLevel(Level.INFO);
		this.t = t;
		this.setClassName(className);
	}

	public void save(T object) {
//		Session s = HibernateUtil.getSession();
//		s.beginTransaction();
//		s.save(object);
//		s.getTransaction().commit();
		super.save(object);
	}

	public void update(T object) {
		Session s = HibernateUtil.getSession();
		s.beginTransaction();
		s.update(object);
		s.getTransaction().commit();
		//super.update(object);
	}

	public List<T> getAll() {
//		Session s = HibernateUtil.getSession();
//		List<T> vuelos = s.createQuery("from "+this.className).list();
//		return vuelos;
		return super.getAll();
	}

	@Override
	public Class<T> getPersistentClass() {
		return t;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

//	@Override
//	public Class<T> getPersistentClass() {
//		return t;
//	}

}
