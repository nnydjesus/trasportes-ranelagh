package ar.com.nny.base.ui.swing.components;


import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;



/**
 * Panel con toda la parte visual del ejemplo. Crea un JScrollPane con el JTable
 * en su interior
 * 
 * @author Ronny
 */
@SuppressWarnings({ "unchecked", "serial" })
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
//         tabla.setDefaultRenderer(Object.class,new MyRenderer());
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
	
	
	public void addtableListener(MouseListener actionListener){
		this.tabla.addMouseListener(actionListener);
	}
	
	
//	static class MyRenderer extends DefaultTableCellRenderer{
//		
//		@Override
//		public Component getTableCellRendererComponent(JTable table, Object value,
//                boolean isSelected, boolean hasFocus, int row, int column){
////			if(value instanceof Boolean){
////				return new Button("asdf sf"+ (Boolean) value);
////			}else{
//				Component component = new JLabel(String.valueOf(value));
//				if(isSelected)
//					component.setBackground(Color.GRAY);
//				if(hasFocus)
//					component.setBackground(Color.DARK_GRAY);
//				return component;
////			}
//		}
//	}
	
	
     
}

