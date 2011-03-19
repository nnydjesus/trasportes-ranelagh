package main;

import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.JTree.DynamicUtilTreeNode;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import ar.com.nny.base.common.Item;


@SuppressWarnings("serial")
public class Tree extends JPanel{
	
	
	private Vector<Item> vector= new Vector<Item>();
	private JTree tree =  new JTree(vector);
	
	
	public Tree() {
		this.setLayout(new GridLayout(1, 1));
		addListener();
		this.add(tree);
		this.setSize(300, 300);
	}

	private void addListener() {
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DynamicUtilTreeNode lastSelectedPathComponent = (DynamicUtilTreeNode) tree.getLastSelectedPathComponent();
				if(lastSelectedPathComponent!= null){
					Item item = (Item) lastSelectedPathComponent.getUserObject();
					item.mostrar();					
				}
			}
		});
	}
	
	public void updateTree(Item tablasTree) {
		vector.removeAll(vector);
		vector.add(tablasTree);
		this.remove(tree);
		tree =  new JTree(vector);
		this.addListener();
		this.add(tree);
	}
	
	

	public void setVector(Vector<Item> vector) {
		this.vector = vector;
	}

	public Vector<Item> getVector() {
		return vector;
	}
	public JTree getTree() {
		return tree;
	}
	public void setTree(JTree tree) {
		this.tree = tree;
	}

	public static void main(String[] args) {
		new Tree();
	}

}


