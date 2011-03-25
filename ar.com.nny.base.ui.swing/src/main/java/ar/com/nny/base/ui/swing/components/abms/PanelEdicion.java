package ar.com.nny.base.ui.swing.components.abms;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import ar.com.nny.base.ui.swing.components.AbstractBindingPanel;
import ar.com.nny.base.utils.Path;

public class PanelEdicion<T> extends AbstractBindingPanel<T> {
    private static final long serialVersionUID = -4916057975139862174L;

    private JButton botonAgregar;

    private JButton botonModificar;

    private JButton botonCancelar;

    public PanelEdicion(final String claseAEditar, final T model) {
        super(claseAEditar, model);
        this.setTitulo("Editar " + claseAEditar);

    }

    @Override
    protected void addButtons() {
        this.setBotonAgregar(new JButton(new ImageIcon(Path.path() + "Images/add.jpg")));
        this.getBotonAgregar().setText("Agregar");
        this.setBotonModificar(new JButton(new ImageIcon(Path.path() + "Images/check.jpg")));
        this.getBotonModificar().setText("Modificar");
        this.getBotonModificar().setEnabled(false);
        this.setBotonCancelar(new JButton(new ImageIcon(Path.path() + "Images/cancel.jpg")));
        this.getBotonCancelar().setText("Limpiar");

        this.getPanelDeBotones().addGridded(this.getBotonAgregar());
        this.getPanelDeBotones().addGridded(this.getBotonModificar());
        this.getPanelDeBotones().addGridded(this.getBotonCancelar());
    }

    public void setBotonCancelar(final JButton botonCancelar) {
        this.botonCancelar = botonCancelar;
    }

    public JButton getBotonCancelar() {
        return botonCancelar;
    }

    public void setBotonModificar(final JButton botonModificar) {
        this.botonModificar = botonModificar;
    }

    public JButton getBotonModificar() {
        return botonModificar;
    }

    public void setBotonAgregar(final JButton botonAgregar) {
        this.botonAgregar = botonAgregar;
    }

    public JButton getBotonAgregar() {
        return botonAgregar;
    }

}
