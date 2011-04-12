package ar.com.nny.base.ui.swing.components.search;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import ar.com.nny.base.common.Item;
import ar.com.nny.base.search.Home;
import ar.com.nny.base.search.HomeLocator;
import ar.com.nny.base.utils.IdentificablePersistentObject;

public class WindowsSearch<T extends IdentificablePersistentObject> extends JFrame implements Item, ar.com.nny.base.ui.swing.components.WindowsSearch<T>{
    private SearchPanel<T> search;
    
    
    public WindowsSearch(T model, Home<T> home) {
         search = new SearchPanel<T>(model, home, this);
         this.add(search);
         this.setSize(1024, 768);
    }
    public WindowsSearch(SearchPanel<T> search) {
        this.search = search;
        this.add(search);
        this.setSize(1024, 768);
    }
    
    @Override
    public String toString() {
        return "Totalidad remitos";
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

}
