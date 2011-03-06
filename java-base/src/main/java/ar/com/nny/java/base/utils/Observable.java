package ar.com.nny.java.base.utils;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import ar.com.nny.java.base.bean.Beans;


/**
 * @author Ronny
 *
 */
public abstract class Observable implements Beans{
	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		this.changeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		this.changeSupport.removePropertyChangeListener(propertyName, listener);
	}

	protected <T> void firePropertyChange(String propertyName, T oldValue, T newValue) {
		this.changeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}

	public void setProperty(String property, Object value) {
		try {
			Object originalValue = this.getProperty(property);
			Field field = this.getSetter(property).getDeclaringClass().getDeclaredField(property);
			field.setAccessible(true);
			field.set(this, value);
			this.firePropertyChange(property, originalValue, value);
		}
		catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		}
		catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		catch (SecurityException e) {
			throw new RuntimeException(e);
		}
		catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
	}

	public Object getProperty(String property) {
		try {
			return this.getGetter(property).invoke(this, new Object[] {});
		}
		catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		}
		catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	protected Method getGetter(String property) {
		try {
			return this.getClass().getMethod("get" + this.capitalize(property), new Class[] {});
		}
		catch (SecurityException e) {
			throw new RuntimeException(e);
		}
		catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	protected Method getSetter(String property) {
		try {
			String methodName = "set" + this.capitalize(property);
			for (Method method : this.getClass().getMethods()) {
				if (method.getName().equals(methodName) && method.getParameterTypes().length == 1
					&& method.getParameterTypes()[0].equals(this.getGetter(property).getReturnType())) {
					return method;
				}
			}

			throw new RuntimeException("no se encuentra el setter para la property " + property);
		}
		catch (SecurityException e) {
			throw new RuntimeException(e);
		}
	}

	protected String capitalize(String property) {
		if (property == null || property.equals("")) {
			return property;
		}
		if (property.length() == 1) {
			return property.toUpperCase();
		}

		return property.substring(0, 1).toUpperCase() + property.substring(1, property.length());
	}

	public String getId(){
		return "";
	}
	
	public String mostrar(){
		return getId();
	}
	
	public abstract String[] atributos();

}
