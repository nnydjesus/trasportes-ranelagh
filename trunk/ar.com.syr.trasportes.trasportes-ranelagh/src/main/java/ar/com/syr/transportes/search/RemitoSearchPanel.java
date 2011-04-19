package ar.com.syr.transportes.search;

import javax.swing.SwingUtilities;

import ar.com.nny.base.ui.swing.components.WindowsSearch;
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

    public RemitoSearchPanel(final Remito newInstance, WindowsSearch parent) {
        super(newInstance, HomeRemito.getInstance(), parent);
        desde = new JDateChooser();
        hasta = new JDateChooser();
    }

    public void addFields() {
        this.getBuilder().append("desde", desde);
        this.getBuilder().append("hasta", hasta);
        
        this.addTextTotal(Remito.COSTO, "ganancia total");
        this.addTextTotal(Remito.COSTO_CHOFER, "costo total");
        this.addTextTotal(Remito.COMBUSTIBLE, "combustible total");
        this.addTextTotal(Remito.PEAJE, "gasto de peaje totales");
    }
    

    @Override
    public void search() {
        result.removeAll(result);
        result.addAll(((HomeRemito) home).searchByExample(this.getModel(), desde.getDate(), hasta.getDate()));
        updateTotals();
        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public void clear() {
        desde.setDate(null);
        hasta.setDate(null);
        super.clear();
    }

}
