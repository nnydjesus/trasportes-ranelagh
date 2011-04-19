package ar.com.nny.base.ui.swing.components.abms;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ar.com.nny.base.common.Item;
import ar.com.nny.base.common.ItemComposite;
import ar.com.nny.base.search.Home;
import ar.com.nny.base.ui.swing.components.AbstractBindingPanel;
import ar.com.nny.base.ui.swing.components.ActionMethodListener;
import ar.com.nny.base.utils.IdentificablePersistentObject;

public abstract class ABMFrame<T extends IdentificablePersistentObject> extends JFrame implements WindowsEdition, Item  {

    private static final long serialVersionUID = 1L;

    private ABMEdition<T> edicion;

    private String nombre;
    
    private ItemComposite item;
    
    private JFrame parent;
    
    private ActionMethodListener onSaveListener;

    public ABMFrame(T model,  JFrame parent) {
        this.parent = parent;
        this.setNombre(model.getName());
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        edicion = new ABMEdition<T>( model, getHome(), this);

        this.createForm(edicion);
        this.addPanels(edicion);
        this.addActions();
        this.setSize(1024, 780);
        this.setVisible(false);
    }

    protected void addActions() {
        
    }

    protected Home<T> getHome() {
        return  new Home<T>((Class<T>) edicion.getModel().getClass());
    }

    protected void addPanels(final JPanel panel) {
        this.add(panel);
    }



    public void setModel(final T observable, ItemComposite item) {
        this.setModel(observable);
        this.item = item;
    }
    public void setModel(final T observable) {
        setEditionModel(observable);
        update();
    }

    protected void setEditionModel(final T observable) {
        edicion.setModel(observable);
    }

    public void edicionCancelar() {
        setVisible(false);
    }
    
    public void edicionAceptar(Object object) {
        update();
        if(onSaveListener!= null)
            onSaveListener.execute();
        edicionCancelar();   
    }

    @Override
    public void mostrar() {
        this.setVisible(true);
    }

    @Override
    public String toString() {
        return getNombre();
    }

    protected abstract void createForm(AbstractBindingPanel<T> edicion2);

    public List<T> getObjects() {
        return getHome().getAll();
    }
    
    public void update(){
        SwingUtilities.updateComponentTreeUI(this);
    }

    public ABMEdition<T> getEdicion() {
        return edicion;
    }

    public ABMFrame<T> setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public void setParent(JFrame parent) {
        this.parent = parent;
    }

    public JFrame getParent() {
        return parent;
    }

    public void onSave(ActionMethodListener actionMethodListener) {
        onSaveListener = actionMethodListener;
    }

}
