package ar.com.nny.base.ui.swing.components.search;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import ar.com.nny.base.common.Item;
import ar.com.nny.base.search.Home;
import ar.com.nny.base.utils.IdentificablePersistentObject;

public class WindowsSearchImpl<T extends IdentificablePersistentObject> extends JFrame implements Item, ar.com.nny.base.ui.swing.components.WindowsSearch<T>{
    private SearchPanel<T> search;
    
    
    public WindowsSearchImpl(T model, Home<T> home) {
         search = new SearchPanel<T>(model, home, this);
         this.add(search);
         this.setSize(1024, 768);
    }
    public WindowsSearchImpl(SearchPanel<T> search) {
        this.search = search;
        this.add(search);
        this.setSize(1024, 768);
    }
    
    @Override
    public String toString() {
        return "Totalidad";
    }

    @Override
    public void mostrar() {
        search.search();
        this.setVisible(true);
    }
    @Override
    public void editSelected(T selected) {
    }
    @Override
    public void update() {
        SwingUtilities.updateComponentTreeUI(search);        
    }
    @Override
    public void deleteObject(Object selected) {
    }

}
