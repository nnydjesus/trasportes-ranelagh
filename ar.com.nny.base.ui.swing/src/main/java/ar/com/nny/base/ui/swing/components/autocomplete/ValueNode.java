package ar.com.nny.base.ui.swing.components.autocomplete;

import ar.com.nny.base.common.Observable;

public class ValueNode {
    private Observable object;
    private String property;
    
    public ValueNode(Observable object, String property) {
        this.setObject(object);
        this.setProperty(property);
        
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }

    public void setObject(Observable object) {
        this.object = object;
    }

    public Observable getObject() {
        return object;
    }
    
    @Override
    public String toString() {
        return object.getProperty(property).toString();
    }

}
