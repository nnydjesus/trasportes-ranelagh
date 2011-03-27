package ar.com.nny.base.common;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import ar.com.nny.base.bean.Beans;

/**
 * @author Ronny
 * 
 */
public abstract class Observable implements Beans {
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(propertyName, listener);
    }

    protected <T> void firePropertyChange(final String propertyName, final T oldValue, final T newValue) {
        changeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    public void setProperty(final String property, final Object value) {
        try {
            Object originalValue = this.getProperty(property);
            Field field = this.getSetter(property).getDeclaringClass().getDeclaredField(property);
            field.setAccessible(true);
            field.set(this, value);
            this.firePropertyChange(property, originalValue, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object getProperty(final String property) {
        try {
            return this.getGetter(property).invoke(this, new Object[] {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected Method getGetter(final String property) {
        try {
            return this.getClass().getMethod("get" + this.capitalize(property), new Class[] {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected Method getSetter(final String property) {
        try {
            String methodName = "set" + this.capitalize(property);
            for (Method method : this.getClass().getMethods()) {
                if (method.getName().equals(methodName) && method.getParameterTypes().length == 1
                        && method.getParameterTypes()[0].equals(this.getGetter(property).getReturnType()))
                    return method;
            }

            throw new RuntimeException("no se encuentra el setter para la property " + property);
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        }
    }

    protected String capitalize(final String property) {
        if (property == null || property.equals(""))
            return property;
        if (property.length() == 1)
            return property.toUpperCase();

        return property.substring(0, 1).toUpperCase() + property.substring(1, property.length());
    }

    public String getId() {
        return "";
    }

    public String mostrar() {
        return this.getId();
    }

    @Override
    public String toString() {
        return this.mostrar();
    }

    public abstract String[] atributos();

}
