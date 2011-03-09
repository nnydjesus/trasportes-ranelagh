package ar.com.nny.base.configuration;

public interface CVSLineCallback {

    public abstract Class[] getParameterTypes();


    public abstract void invoke(Object[] parameters);

}