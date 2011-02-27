package ui;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import utils.MouseClicked;


/**
 * Panel con toda la parte visual del ejemplo. Crea un JScrollPane con el JTable
 * en su interior
 * 
 * @author Ronny
 */
@SuppressWarnings("serial")
public class GeneralTable extends GeneralPanel
 {
	
	/** Modelo de la tabla */
    private Model modelo = null;
    
    /** Para modificar el modelo */
    private Control control = null;
    
    private JScrollPane scroll = new JScrollPane();
    private JTable tabla;
	
	/**
      * Constructor que recibe el modelo de la tabla y el control. Guarda ambos
      * y llama al metodo construyeVentana() que se encarga de crear los
      * componentes.
      */
     public GeneralTable(Model modelo, Control control)
     {
         this(modelo, control, new JTable());
     }
     
     public GeneralTable(Model modelo, Control control, JTable tabla)
     {
         super (new GridBagLayout());
         this.tabla = tabla;
         this.modelo = modelo;
         this.setControl(control);
         construyeVentana();
         
     }
     
     /**
      * Crea los componentes de este panel.
      * Un JScrollPane, el JTable que va dentro y dos JButton para a�adir y
      * quitar elementos del JTable.
      */
     private void construyeVentana()
     {
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
         //restricciones.anchor = GridBagConstraints.EAST;
         
         
         // Se crea el JScrollPane, el JTable y se pone la cabecera...
         
         scroll.setOpaque(false);
         tabla.setModel((TableModel) modelo);
         scroll.setViewportView(tabla);
         scroll.setColumnHeaderView (tabla.getTableHeader());
         
         // ... y se a�ade todo al panel
         this.add(scroll, restricciones);         
     }

	public JScrollPane getScroll() {
		return scroll;
	}

	public void setControl(Control control) {
		this.control = control;
	}

	public Control getControl() {
		return control;
	}
	
	public JTable getTabla(){
		return tabla;
	}

	public Object getSelected(){
		return this.modelo.getSelected(tabla.getSelectedRow());
	}
	
	
	public void addtableListener(MouseClicked actionListener){
		this.tabla.addMouseListener(actionListener);
	}
	

     
     
}

