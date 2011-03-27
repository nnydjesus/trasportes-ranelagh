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
import ar.com.syr.transportes.categoria.Nomina;
import ar.com.syr.transportes.categoria.Operaciones;
import ar.com.syr.transportes.ui.Tree;

import com.jgoodies.forms.builder.ButtonStackBuilder;

public class TransportesRanelagh extends FrameLook {

    private static final long serialVersionUID = 1L;

    private ButtonStackBuilder panelBotones;

    private JPanel panelTree;

    private JButton Operaciones;

    private JButton administracion;

    private JButton Personal;

    private JButton controlDeUnidades;

    private JButton viajes;

    private JButton bultos;

    private JPanel panelDerecho = new JPanel();

    private GeneralPanel topPanel = new GeneralPanel(new BorderLayout());

    private Tree tree = new Tree();

    private Registrar registro = new Registrar();

    private Operaciones tablasTree = new Operaciones();

    private Nomina personalTree = new Nomina();

    public TransportesRanelagh(final Preloader loader) {
        this();
        loader.setJframe(this);
        super.setLoader(loader);
    }

    public TransportesRanelagh() {
        super();
        topPanel.add(new JLabel(new ImageIcon(Path.path() + "Images/logo.jpg")), BorderLayout.WEST);
        JLabel subTitulo = new JLabel("TRASPORTES RANELAGH", JLabel.CENTER);
        Font dejavuFont = new Font("DejaVu Sans", 1, 30);
        subTitulo.setFont(dejavuFont);
        topPanel.add(subTitulo, BorderLayout.CENTER);
        this.setTitle("Transportes Ranelagh");
        panelBotones = new ButtonStackBuilder();
        panelTree = new JPanel();
        Operaciones = new JButton(new ImageIcon(Path.path() + "Images/resources.jpg"));
        Operaciones.setText("Operaciones");
        administracion = new JButton("Administracion");
        Personal = new JButton("Personal");
        controlDeUnidades = new JButton("ControlDeUnidades");
        panelBotones.addGridded(Operaciones);
        panelBotones.addRelatedGap();
        panelBotones.addRelatedGap();
        panelBotones.addGridded(administracion);
        panelBotones.addRelatedGap();
        panelBotones.addRelatedGap();
        panelBotones.addGridded(Personal);
        panelBotones.addRelatedGap();
        panelBotones.addRelatedGap();
        panelBotones.addGridded(controlDeUnidades);
        JMenuItem jMenuItem = new JMenuItem("Agregar usuario");
        JMenuItem jMenuItemGenerator = new JMenuItem("Generar Base");
        jMenuItem.addActionListener(new ActionMethodListener(this, "Mostrarregistro"));
        jMenuItemGenerator.addActionListener(new ActionMethodListener(this, "generateBase"));
        ((JMenu) this.getJMenuBar().getComponent(0)).add(jMenuItem);
        ((JMenu) this.getJMenuBar().getComponent(0)).add(jMenuItemGenerator);
        this.addChilds();
        this.addActions();
        this.setSize(1024, 780);
        this.setVisible(false);

    }

    public void Mostrarregistro() {
        registro.setVisible(true);
    }

    public void generateBase() {
        new DDLGenerator().main(null);
    }

    public void addActions() {

        Personal.addActionListener(new ListenerButtonPanel(personalTree));
        Operaciones.addActionListener(new ListenerButtonPanel(tablasTree));
        administracion.addActionListener(new ListenerButtonPanel(null));
        controlDeUnidades.addActionListener(new ListenerButtonPanel(null));

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent arg0) {
                // HibernateUtil.getSession().close();
                System.exit(0);
            }
        });
    }

    protected void addChilds() {
        GroupLayout layout = new GroupLayout(this.getContentPane());
        layout.setHorizontalGroup(layout
                .createParallelGroup()
                .addComponent(topPanel, 0, 780, 780)
                .addGroup(
                        layout.createSequentialGroup().addComponent(panelBotones.getContainer(), -1, 100, 200)
                                .addComponent(tree, -1, 700, 700).addComponent(panelDerecho, -1, 50, 50)));
        layout.setVerticalGroup(layout
                .createSequentialGroup()
                .addComponent(topPanel, 0, 100, 100)
                .addGroup(
                        layout.createParallelGroup().addComponent(panelBotones.getContainer(), -1, 100, 100)
                                .addComponent(tree, -1, 700, 700).addComponent(panelDerecho, -1, 100, 100)));
        this.getContentPane().setLayout(layout);

    }

    @Override
    protected void updateUI() throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(look);
        SwingUtilities.updateComponentTreeUI(TransportesRanelagh.this);
        SwingUtilities.updateComponentTreeUI(registro);
        for (Object component : tablasTree) {
            SwingUtilities.updateComponentTreeUI((Component) component);
        }
    }

    class ListenerButtonPanel implements ActionListener {
        private Item item;

        public ListenerButtonPanel(final Item item) {
            this.item = item;
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            tree.updateTree(item);
            SwingUtilities.updateComponentTreeUI(TransportesRanelagh.this);
        }
    }

}