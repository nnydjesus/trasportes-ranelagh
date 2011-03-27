package ar.com.nny.base.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.log4j.BasicConfigurator;
import org.hibernate.Session;

import ar.com.nny.base.persistence.PersistentObject;



@SuppressWarnings("unchecked")
public class GenericDao<T extends PersistentObject> extends GenericFlexyDAO<T>{

	private Class t;
	private String className;

	public GenericDao() {
	}
	
	public GenericDao(Class t) {
		this(t,"");
	}
	public GenericDao(Class t, String className) {
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
//		Session s = session();
//		s.beginTransaction();
//		s.update(object);
//		s.getTransaction().commit();
		super.update(object);
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
