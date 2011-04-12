package ar.com.syr.transportes.search;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import ar.com.nny.base.ui.swing.components.ActionMethodListener;
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
    }
    

    @Override
    public void search() {
        result.removeAll(result);
        result.addAll(((HomeRemito) home).searchByExample(this.getModel(), desde.getDate(), hasta.getDate()));
        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public void clear() {
        desde.setDate(null);
        hasta.setDate(null);
        super.clear();
    }

}
