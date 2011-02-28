package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.JTree.DynamicUtilTreeNode;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import ar.com.syr.trasportes.common.Item;
import ar.com.syr.trasportes.common.Tablas;
import ar.com.syr.trasportes.utils.HibernateUtil;


public class Tree extends JFrame{
	
	
	private JTree tree;
	
	public Tree() {
		Vector<Item> vector =  new Vector<Item>();
//		Item remito = new RemitoUI();
		vector.add(new Tablas());
		tree = new JTree(vector);
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
		this.addActions();
		//this.add(tree);
		this.add(tree);
		this.pack();
		this.setVisible(true);
		this.setSize(600, 600);
	}
	
	private void addActions() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				HibernateUtil.getSession().close();
				System.exit(0);
			}
		});
	}
	

	public static void main(String[] args) {
		new Tree();
	}
	
	

}


