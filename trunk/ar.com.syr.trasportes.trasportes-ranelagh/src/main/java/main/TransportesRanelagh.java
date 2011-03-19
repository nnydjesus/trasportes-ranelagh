package main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ar.com.nny.base.common.Item;
import ar.com.nny.base.generator.DDLGenerator;
import ar.com.nny.base.ui.swing.components.ActionMethodListener;
import ar.com.nny.base.ui.swing.components.FrameLook;
import ar.com.nny.base.ui.swing.components.GeneralPanel;
import ar.com.nny.base.ui.swing.components.Preloader;
import ar.com.nny.base.ui.swing.components.Registrar;
import ar.com.nny.base.utils.Path;
import ar.com.syr.transportes.common.Tablas;

import com.jgoodies.forms.builder.ButtonStackBuilder;

@SuppressWarnings("serial")
public class TransportesRanelagh extends FrameLook{
	
	private ButtonStackBuilder panelBotones;
	private JPanel panelTree;
	private JButton tablas;
	private JButton administracion;
	private JButton operaciones;
	private JButton controlDeUnidades;
	private JButton viajes;
	private JButton bultos;
	private JPanel panelDerecho = new JPanel();
	private GeneralPanel topPanel = new GeneralPanel(new BorderLayout());
	private Tree tree = new Tree();
	private Registrar registro = new Registrar();
	
	private Tablas tablasTree= new Tablas();
	
	public TransportesRanelagh(Preloader loader) {
		this();
		loader.setJframe(this);
		super.setLoader(loader);
	}
	
	public TransportesRanelagh() {
		super();
		this.topPanel.add(new JLabel(new ImageIcon(Path.path()+"Images/logo.jpg")), BorderLayout.WEST);
		JLabel subTitulo = new JLabel("TRASPORTES RANELAGH", JLabel.CENTER);
		Font dejavuFont = new Font("DejaVu Sans", 1, 30);
		subTitulo.setFont(dejavuFont);
		this.topPanel.add(subTitulo, BorderLayout.CENTER);
		this.setTitle("Transportes Ranelagh");
		this.panelBotones = new ButtonStackBuilder();
		this.panelTree = new JPanel();
		this.tablas = new JButton(new ImageIcon(Path.path()+"Images/resources.jpg"));
		this.tablas.setText("Tablas");
		this.administracion = new JButton("Administracion");
		this.operaciones = new JButton("Operaciones");
		this.controlDeUnidades = new JButton("ControlDeUnidades");
		panelBotones.addGridded(tablas);
		panelBotones.addRelatedGap();
		panelBotones.addRelatedGap();
		panelBotones.addGridded(administracion);
		panelBotones.addRelatedGap();
		panelBotones.addRelatedGap();
		panelBotones.addGridded(operaciones);
		panelBotones.addRelatedGap();
		panelBotones.addRelatedGap();
		panelBotones.addGridded(controlDeUnidades);	
		JMenuItem jMenuItem = new JMenuItem("Agregar usuario");
		JMenuItem jMenuItemGenerator = new JMenuItem("Generar Base");
		jMenuItem.addActionListener(new ActionMethodListener(this, "Mostrarregistro"));
		jMenuItemGenerator.addActionListener(new ActionMethodListener(this, "generateBase"));
		((JMenu)this.getJMenuBar().getComponent(0)).add(jMenuItem);
		((JMenu)this.getJMenuBar().getComponent(0)).add(jMenuItemGenerator);
		this.addChilds();
		this.addActions();
		this.setSize(1024,780);
		this.setVisible(false);

	}

	public void Mostrarregistro() {
		registro.setVisible(true);
	}
	public void generateBase() {
		new DDLGenerator().main(null);
	}


	public void addActions(){
		
		tablas.addActionListener(new ListenerButtonPanel(tablasTree));
		administracion.addActionListener(new ListenerButtonPanel(null));
		operaciones.addActionListener(new ListenerButtonPanel(null));
		controlDeUnidades.addActionListener(new ListenerButtonPanel(null));
	
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
//				HibernateUtil.getSession().close();
				System.exit(0);
			}
		});
	}
	
	protected void addChilds(){
		GroupLayout layout = new GroupLayout(this.getContentPane());
		layout
			.setHorizontalGroup(layout.
					createParallelGroup()
					.addComponent(topPanel,0,780,780)
					.addGroup(layout.createSequentialGroup()
						.addComponent(panelBotones.getContainer(), -1, 100, 200)
						.addComponent(tree, -1,700,700)
						.addComponent(panelDerecho,-1,50,50)));
		layout
			.setVerticalGroup(layout
					.createSequentialGroup()
					.addComponent(topPanel,0,100,100)
					.addGroup(layout.createParallelGroup()
						.addComponent(panelBotones.getContainer(), -1, 100, 100)
						.addComponent(tree, -1,700,700)
						.addComponent(panelDerecho,-1,100,100)));
		this.getContentPane().setLayout(layout);
		
	}
	
	@Override
	protected void updateUI() throws UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(this.look);
		SwingUtilities.updateComponentTreeUI(TransportesRanelagh.this);
		SwingUtilities.updateComponentTreeUI(registro);
		for (Component component : tablasTree) {
			SwingUtilities.updateComponentTreeUI(component);						
		}
	}
	
	
	class ListenerButtonPanel implements ActionListener {
		private Item item;
		public ListenerButtonPanel(Item item) {
			this.item = item;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			tree.updateTree(item);
			SwingUtilities.updateComponentTreeUI(TransportesRanelagh.this);
		}
	}


}