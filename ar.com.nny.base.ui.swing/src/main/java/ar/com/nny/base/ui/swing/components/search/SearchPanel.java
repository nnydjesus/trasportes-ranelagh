package ar.com.nny.base.ui.swing.components.search;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import ar.com.nny.base.search.Home;
import ar.com.nny.base.ui.swing.components.ActionMethodListener;
import ar.com.nny.base.ui.swing.components.GeneralTable;
import ar.com.nny.base.ui.swing.components.Generator;
import ar.com.nny.base.ui.swing.components.abms.PanelEdicion;
import ar.com.nny.base.utils.IdentificablePersistentObject;

public class SearchPanel<T extends IdentificablePersistentObject> extends PanelEdicion<T>{

    private Home<T> home;
    private GeneralTable table;
    private List<T> result = new ArrayList<T>();

    public SearchPanel(String claseAEditar, T model, Home<T> home) {
        super(claseAEditar, model);
        this.home = home;
        table = createTable(home.createExample());
        this.addActions();
        this.add(table);
        this.getBotonAgregar().setText("Buscar");
        this.getBotonModificar().setVisible(false);
        this.getBotonCancelar().setText("Limpiar");
    }

    private static final long serialVersionUID = 1L;
    
    protected GeneralTable createTable(T newInstance) {
        return Generator.GENERATE_TABLE(result, newInstance.atributos());
    }
    
    private void addActions() {
        this.getBotonAgregar().addActionListener(new ActionMethodListener(this, "buscar"));
        this.getBotonCancelar().addActionListener(new ActionMethodListener(this, "clear"));
    }
    
    public void clear(){
        result.removeAll(result);
        SwingUtilities.updateComponentTreeUI(this);
    }
    
    public void buscar() {
       result.removeAll(result);
       result.addAll(home.searchByExample(getModel()));
       SwingUtilities.updateComponentTreeUI(this);
    }
    

}
