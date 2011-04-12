package ar.com.syr.transportes.ui;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.JTree.DynamicUtilTreeNode;

import ar.com.nny.base.common.Item;
import ar.com.nny.base.common.ItemComposite;


public class TreePanel extends JPanel{

	private static final long serialVersionUID = 3686547751000945641L;
	
	private Vector<ItemComposite> vector= new Vector<ItemComposite>();
	private JTree tree =  new JTree(vector);
	
	
	public TreePanel() {
		this.setLayout(new GridLayout(1, 1));
		addListener();
		this.add(tree); 
		this.setSize(300, 300);
	}

	private void addListener() {
		tree.addMouseListener(new MouseAdapter() {

		    
			@Override
			public void mouseClicked(MouseEvent e) {
			    if(e.getClickCount() >= 2){
    				DynamicUtilTreeNode lastSelectedPathComponent = (DynamicUtilTreeNode) tree.getLastSelectedPathComponent();
    				if(lastSelectedPathComponent!= null){
    					Item item = (Item) lastSelectedPathComponent.getUserObject();
    					item.mostrar();					
    				}
			    }
			}
		});
	}
	
	public void updateTree(ItemComposite tablasTree) {
		vector.removeAll(vector);
		vector.add(tablasTree);
		this.remove(tree);
		tree =  new JTree(vector);
		this.addListener();
		this.add(tree);
	}
	public void updateTree() {
	    this.updateTree(vector.get(0));
	}
	
	public Vector<ItemComposite> getVector() {
		return vector;
	}
	public JTree getTree() {
		return tree;
	}
	public void setTree(JTree tree) {
		this.tree = tree;
	}

	public static void main(String[] args) {
		new TreePanel();
	}

}


