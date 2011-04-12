package ar.com.nny.base.ui.swing.components.abms;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import ar.com.nny.base.ui.swing.components.AbstractBindingPanel;
import ar.com.nny.base.utils.Nombrable;
import ar.com.nny.base.utils.Path;

public class PanelEdicion<T extends Nombrable> extends AbstractBindingPanel<T> {
    private static final long serialVersionUID = -4916057975139862174L;

    private JButton botonAgregar;

    private JButton botonCancelar;

    public PanelEdicion(final T model) {
        super(model);
        this.setTitulo("Editar " + model.getName());

    }

    @Override
    protected void addButtons() {
        this.setBotonAgregar(new JButton(new ImageIcon(Path.path() + "Images/add.jpg")));
        this.getBotonAceptar().setText("Aceptar");
        this.setBotonCancelar(new JButton(new ImageIcon(Path.path() + "Images/cancel.jpg")));
        this.getBotonCancelar().setText("Cancelar");

        this.getPanelDeBotones().addButton(this.getBotonAceptar());
        this.getPanelDeBotones().addRelatedGap();        
        this.getPanelDeBotones().addButton(this.getBotonCancelar());
    }

    public void setBotonCancelar(final JButton botonCancelar) {
        this.botonCancelar = botonCancelar;
    }

    public JButton getBotonCancelar() {
        return botonCancelar;
    }

//    public void setBotonModificar(final JButton botonModificar) {
//        this.botonModificar = botonModificar;
//    }

//    public JButton getBotonModificar() {
//        return botonModificar;
//    }

    public void setBotonAgregar(final JButton botonAgregar) {
        this.botonAgregar = botonAgregar;
    }

    public JButton getBotonAceptar() {
        return botonAgregar;
    }

}
