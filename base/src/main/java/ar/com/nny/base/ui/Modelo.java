package ar.com.nny.base.ui;

import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/** 
 * @author Ronny
 */
@SuppressWarnings({ "unchecked", "serial" })
public abstract class Modelo<T>  extends AbstractTableModel implements Model
{
	
	
	/** Lista con los datos. Cada elemento de la lista es una instancia de
     */
	private LinkedList datos = new LinkedList();
    
    /** Lista de suscriptores. El JTable será un suscriptor de este modelo de
     * datos */
    private LinkedList listeners = new LinkedList();
    
    //private List<T> datasource;

	
    /** Returns the number of columns in the model. A
     * <code>JTable</code> uses this method to determine how many columns it
     * should create and display by default.
     *
     * @return the number of columns in the model
     * @see #getRowCount
     *
     */
    public abstract int getColumnCount();
     
    
    /** Returns the number of rows in the model. A
     * <code>JTable</code> uses this method to determine how many rows it
     * should display.  This method should be quick, as it
     * is called frequently during rendering.
     *
     * @return the number of rows in the model
     * @see #getColumnCount
     *
     */
    public int getRowCount() {
        return datos.size();
    }
    
    /** Returns the value for the cell at <code>columnIndex</code> and
     * <code>rowIndex</code>.
     *
     * @param	rowIndex	the row whose value is to be queried
     * @param	columnIndex 	the column whose value is to be queried
     * @return	the value Object at the specified cell
     *
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
    	return null;
    }
    
    public LinkedList getDatos() {
		return datos;
	}

	

	protected LinkedList getListeners() {
		return listeners;
	}

	
	/**
     * Borra del modelo la persona en la fila indicada 
     */
    public void removeRow (int fila)
    {
        // Se borra la fila 
        datos.remove(fila);
        
        // Y se avisa a los suscriptores, creando un TableModelEvent...
        TableModelEvent evento = new TableModelEvent (this, fila, fila, 
            TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
        
        // ... y pasándoselo a los suscriptores
        avisaSuscriptores (evento);
    }
    
	/**
     * Borra del modelo el objeto en la fila indicada 
     */
    public void removeAllRow ()
    {
        // Se borra la fila 
        datos.removeAll(datos);
        
        // Y se avisa a los suscriptores, creando un TableModelEvent...
        TableModelEvent evento = new TableModelEvent (this, TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
        
        // ... y pasándoselo a los suscriptores
        avisaSuscriptores (evento);
    }
    
    public void delete(Object obj){
    	
    	// Y se avisa a los suscriptores, creando un TableModelEvent...
        TableModelEvent evento = new TableModelEvent (this, datos.indexOf(obj), datos.indexOf(obj));
        
        // ... y pasándoselo a los suscriptores
        avisaSuscriptores (evento);
    	
        datos.remove(obj);
    }
    
    /**
     * Añade una Objeto al final de la tabla
     */
    public void addRow (Object ob)
    {
        // Añade la persona al modelo 
        datos.add (ob);
        
        // Avisa a los suscriptores creando un TableModelEvent...
        TableModelEvent evento;
        evento = new TableModelEvent (this, this.getRowCount()-1,
            this.getRowCount()-1, TableModelEvent.ALL_COLUMNS,
            TableModelEvent.INSERT);

        // ... y avisando a los suscriptores
        avisaSuscriptores (evento);
    }
    
    /** Adds a listener to the list that is notified each time a change
     * to the data model occurs.
     *
     * @param	l		the TableModelListener
     *
     */
    
    @Override
    public void addTableModelListener(TableModelListener l) {
        // Añade el suscriptor a la lista de suscriptores
        listeners.add (l);
    }
    

        
    
    /** Returns true if the cell at <code>rowIndex</code> and
     * <code>columnIndex</code>
     * is editable.  Otherwise, <code>setValueAt</code> on the cell will not
     * change the value of that cell.
     *
     * @param	rowIndex	the row whose value to be queried
     * @param	columnIndex	the column whose value to be queried
     * @return	true if the cell is editable
     * @see #setValueAt
     *
     */
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Permite que la celda sea editable.
        return false;
    }
    
    /** Removes a listener from the list that is notified each time a
     * change to the data model occurs.
     *
     * @param	l		the TableModelListener
     *
     */
    
    @Override
    public void removeTableModelListener(TableModelListener l) {
        // Elimina los suscriptores.
        listeners.remove(l);
    }
    
   
    /**
     * Pasa a los suscriptores el evento.
     */
    
    protected void avisaSuscriptores (TableModelEvent evento)
    {
        int i;
        
        // Bucle para todos los suscriptores en la lista, se llama al metodo
        // tableChanged() de los mismos, pasándole el evento.
        for (i=0; i<listeners.size(); i++)
            ((TableModelListener)listeners.get(i)).tableChanged(evento);
    }
    
    public Object getSelected(int i){
    	return this.getDatos().get(i);
    }

}
