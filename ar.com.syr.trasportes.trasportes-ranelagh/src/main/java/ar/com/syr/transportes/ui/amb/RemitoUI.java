package ar.com.syr.transportes.ui.amb;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.SwingUtilities;

import ar.com.nny.base.common.Item;
import ar.com.nny.base.ui.swing.components.GeneralFrame;
import ar.com.nny.base.ui.swing.components.GeneralTable;
import ar.com.nny.base.ui.swing.components.Generator;
import ar.com.nny.base.ui.swing.components.search.SearchPanel;
import ar.com.syr.transportes.bean.Remito;
import ar.com.syr.transportes.search.HomeEmpleado;
import ar.com.syr.transportes.search.HomeRemito;
import ar.com.syr.transportes.search.RemitoSearchPanel;

public class RemitoUI extends GeneralFrame<Remito> implements Item {
    private static final long serialVersionUID = -4654971392742688838L;

    private List<Remito> remitos;


    public RemitoUI() {
        super("Remito", Remito.class);
        super.addActions();
    }


    @Override
    protected void addActions() {
        super.addActions();
        
       getSearch().getTable().getTabla().addMouseListener(new MouseAdapter() {
               @Override
            public void mousePressed(MouseEvent e) {
               if(e.getClickCount() == 2){
                   Remito selected = (Remito)  getSearch().getTable().getSelected();
                   selected.setPago(!selected.getPago());
                   home.update(selected);
                   SwingUtilities.updateComponentTreeUI(getSearch().getTable());
               }
        }
    });

    }

    @Override
    protected void createHome() {
        home = HomeRemito.getInstance();
    }


    @Override
    public GeneralTable createTable(final Remito newInstance) {
        return Generator.GENERATE_TABLE(remitos, newInstance.atributos());
    }

    @Override
    protected SearchPanel<Remito> createSearchPanel(final Remito newInstance) {
        return new RemitoSearchPanel(newInstance, this);
    }

    @Override
    protected void createSearchForm(final SearchPanel<Remito> search) {
        search.addAutocompletetextField(Remito.ID, "Numero de Remito");
        search.addBindingComboBox(Remito.EMPLEADO, HomeEmpleado.getInstance().getAll());
        ((RemitoSearchPanel) search).addFields();
        search.getTable().getTabla().setEditingColumn(6);
    }
    
    @Override
    public Class abmClass() {
        return ABMRemito.class;
    }
    
    public static void main(String[] args) {
        new RemitoUI().setVisible(true);
    }

}
