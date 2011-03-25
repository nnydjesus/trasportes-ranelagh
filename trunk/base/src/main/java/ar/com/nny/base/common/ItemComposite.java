package ar.com.nny.base.common;

import java.util.Vector;

abstract public class ItemComposite extends Vector<Object> implements Item {

    private static final long serialVersionUID = 1L;

    @Override
    abstract public String toString();

    @Override
    public void mostrar() {
    }

    public abstract void mostrar(Object item);

}
