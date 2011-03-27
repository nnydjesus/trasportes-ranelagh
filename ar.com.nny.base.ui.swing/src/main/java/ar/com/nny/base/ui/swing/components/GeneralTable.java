package ar.com.nny.base.ui.swing.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableModel;

import org.jvnet.substance.api.renderers.SubstanceDefaultTableCellRenderer.BooleanRenderer;

/**
 * Panel con toda la parte visual del ejemplo. Crea un JScrollPane con el JTable
 * en su interior
 * 
 * @author Ronny
 */
public class GeneralTable extends GeneralPanel {

    private static final long serialVersionUID = 1L;

    /** Modelo de la tabla */
    private ModelBinding modelo = null;

    /** Para modificar el modelo */
    private Control control = null;

    private JScrollPane scroll = new JScrollPane();

    private JTable tabla;

    /**
     * Constructor que recibe el modelo de la tabla y el control. Guarda ambos y
     * llama al metodo construyeVentana() que se encarga de crear los
     * componentes.
     */
    public GeneralTable(final ModelBinding modelo, final Control control) {
        this(modelo, control, new JTable());
    }

    public GeneralTable(final ModelBinding modelo, final Control control, final JTable tabla) {
        super(new GridBagLayout());
        this.tabla = tabla;
        this.setModelo(modelo);
        this.setControl(control);
        this.construyeVentana();

    }

    /**
     * Crea los componentes de este panel. Un JScrollPane, el JTable que va
     * dentro y dos JButton para a�adir y quitar elementos del JTable.
     */
    private void construyeVentana() {
        // Para poner las restricciones a los componentes (posicioes dentro
        // del panel)
        GridBagConstraints restricciones = new GridBagConstraints();

        // Un JScrollPane con el JTable dentro.
        // Las restricciones...
        restricciones.gridx = 1;
        restricciones.gridy = 1;
        restricciones.gridwidth = GridBagConstraints.REMAINDER;
        restricciones.fill = GridBagConstraints.BOTH;
        restricciones.weightx = 2.0;
        restricciones.weighty = 2.0;
        // restricciones.anchor = GridBagConstraints.EAST;

        // Se crea el JScrollPane, el JTable y se pone la cabecera...

        scroll.setOpaque(false);
        tabla.setModel((TableModel) getModelo());
//         tabla.setDefaultRenderer(Boolean.class,new MyRenderer());
        scroll.setViewportView(tabla);
        scroll.setColumnHeaderView(tabla.getTableHeader());

        // ... y se a�ade todo al panel
        this.add(scroll, restricciones);
    }

    public JScrollPane getScroll() {
        return scroll;
    }

    public void setControl(final Control control) {
        this.control = control;
    }

    public Control getControl() {
        return control;
    }

    public JTable getTabla() {
        return tabla;
    }

    public Object getSelected() {
        return getModelo().getSelected(tabla.getSelectedRow());
    }

    public void addtableListener(final MouseListener actionListener) {
        tabla.addMouseListener(actionListener);
    }

    public void setModelo(ModelBinding modelo) {
        this.modelo = modelo;
    }

    public ModelBinding getModelo() {
        return modelo;
    }

//    static class MyRenderer extends BooleanRenderer {
//        //
//        @Override
//        public Component getTableCellRendererComponent(final JTable table,  final Object value, boolean isSelected,
//                boolean hasFocus, int row, int column) {
//            final JCheckBox check = (JCheckBox) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//            check.addChangeListener(new ChangeListener() {
//                
//                @Override
//                public void stateChanged(ChangeEvent e) {
//                   table.getAlignmentX();       
//                }
//            });
//            return check;
//        }
//    }

}
