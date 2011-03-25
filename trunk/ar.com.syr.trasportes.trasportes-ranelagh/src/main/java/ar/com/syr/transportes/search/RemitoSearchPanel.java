package ar.com.syr.transportes.search;

import javax.swing.SwingUtilities;

import ar.com.nny.base.ui.swing.components.search.SearchPanel;
import ar.com.syr.transportes.bean.Remito;

import com.toedter.calendar.JDateChooser;

/**
 * TODO: description
 */
public class RemitoSearchPanel extends SearchPanel<Remito> {
    private static final long serialVersionUID = 1L;

    private JDateChooser desde;

    private JDateChooser hasta;

    public RemitoSearchPanel(final Remito newInstance) {
        super("Remito", newInstance, HomeRemito.getInstance());
        desde = new JDateChooser();
        hasta = new JDateChooser();
    }
    
    public void addFields() {
        this.getPanelDeAtributos().append("desde", desde);
        this.getPanelDeAtributos().append("hasta", hasta);
    }

    @Override
    public void search() {
        result.removeAll(result);
        result.addAll(((HomeRemito) home).searchByExample(this.getModel().getId(), desde.getDate(), hasta.getDate()));
        SwingUtilities.updateComponentTreeUI(this);
    }

}
