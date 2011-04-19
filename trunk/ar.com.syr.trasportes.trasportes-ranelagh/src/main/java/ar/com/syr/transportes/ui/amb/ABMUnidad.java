package ar.com.syr.transportes.ui.amb;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;

import main.Main;
import ar.com.nny.base.search.Home;
import ar.com.nny.base.ui.swing.components.AbstractBindingPanel;
import ar.com.nny.base.ui.swing.components.ActionMethodListener;
import ar.com.nny.base.ui.swing.components.abms.ABMFrame;
import ar.com.syr.transportes.bean.FormaDePago;
import ar.com.syr.transportes.bean.Unidad;
import ar.com.syr.transportes.bean.enums.ClaseDeUnidad;
import ar.com.syr.transportes.bean.enums.TipoDeUnidad;
import ar.com.syr.transportes.search.HomeUnidad;

public class ABMUnidad extends ABMFrame<Unidad>{
    private static final long serialVersionUID = 1L;
	private JComboBox tipos;
	private JComboBox unidadAsociada;
    

    public ABMUnidad(Unidad model) {
        this(model, Main.getApp());
    }
    public ABMUnidad(JFrame parent) {
        this(new Unidad(), parent);
    }
    public ABMUnidad(Unidad model, JFrame parent) {
        super(model, parent);
    }

    @Override
    protected void createForm(final AbstractBindingPanel<Unidad> edicion) {
        edicion.addBindingTextField(Unidad.PATENTE, "Patente");
        edicion.addBindingTextField(Unidad.MODELO, "modelo");
        edicion.addBindingTextField(Unidad.MARCA, "marca");
        edicion.addBindingTextField(Unidad.NUMERO_CHAZIS, "numero de chazis");
        edicion.addBindingTextField(Unidad.NUMERO_MOTOR, "numero motor");
        edicion.addBindingComboBox(Unidad.CLASE, ClaseDeUnidad.values());
        tipos = edicion.addBindingComboBox(Unidad.TIPO, TipoDeUnidad.values());
        unidadAsociada = edicion.addBindingComboBox(Unidad.UNIDAD_ASOCIADA, getHome().getAll());
    }
    
    @Override
    protected void addActions() {
    	super.addActions();
        tipos.addActionListener(new ActionMethodListener(this, "updateTipo"));
    }
    
    public void updateTipo() {
        TipoDeUnidad tipoModel = getEdicion().getModel().getTipo();
        if(tipoModel != null){
	        unidadAsociada.setVisible(tipoModel.equals(TipoDeUnidad.CHASIS) ||
	        		tipoModel.equals(TipoDeUnidad.CHASIS));
			update();
        }
    }
    
    @Override
    protected Home getHome() {
        return HomeUnidad.getInstance();
    }
    
    public static void main(String[] args) {
        new ABMUnidad(new JFrame()).setVisible(true);
    }

    
}