package ar.com.nny.base.ui.swing.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTable.PrintMode;
import javax.swing.SwingUtilities;

import ar.com.nny.base.common.Item;
import ar.com.nny.base.search.Home;
import ar.com.nny.base.ui.swing.components.abms.PanelEdicion;
import ar.com.nny.base.ui.swing.components.search.SearchPanel;
import ar.com.nny.base.utils.IdentificablePersistentObject;
import ar.com.nny.base.utils.PrintUtilities;
import ar.com.nny.base.utils.ReflectionUtils;

@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class GeneralFrame<T extends IdentificablePersistentObject> extends JFrame implements Item {

    private static final long serialVersionUID = 1L;

    private TopPanel topPanel;

    protected GeneralTable table;

    private JTabbedPane panel = new JTabbedPane();

    protected PanelEdicion<T> edicion;

    protected Home<T> home;

    private String nombre;

    protected MyJComboBox<T> comboBox;

    protected Boolean tengo;

    protected SearchPanel<T> search;

    private Class<T> clazz;

    public GeneralFrame(final String name, final Class clase) {
        this.clazz = clase;
        this.nombre = name;
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        T newInstance;
        this.createHome();
        

        newInstance = (T) ReflectionUtils.instanciate(clase);
        edicion = new PanelEdicion<T>(name, newInstance);
        search = this.createSearchPanel(newInstance);

        this.createComboBox();
        comboBox.addDefaultValue(home.createExample());
        this.topPanel = new TopPanel();
        this.table = this.createTable(newInstance);
        this.createForm(edicion);
        this.createSearchForm(search);
        this.addPanels(panel);
        this.addActions();
        this.add(topPanel);
        this.add(panel);
        this.setSize(1024, 780);
        this.setVisible(false);
    }

    protected SearchPanel<T> createSearchPanel(final T newInstance) {
        return new SearchPanel<T>( newInstance, home);
    }

    protected void setLayout() {
        GroupLayout layout = new GroupLayout(this.getContentPane());
        layout.setHorizontalGroup(layout
                .createParallelGroup()
                .addComponent(topPanel, 0, 780, 780)
                .addGroup(Alignment.CENTER,
                        layout.createSequentialGroup().addComponent(panel, -1, panel.getWidth(), panel.getWidth())));
        layout.setVerticalGroup(layout.createSequentialGroup().addComponent(topPanel, 0, 100, 100)
                .addGroup(layout.createParallelGroup().addComponent(panel, -1, panel.getHeight(), panel.getHeight())));

        this.getContentPane().setLayout(layout);

    }

    protected GeneralTable createTable(final T newInstance) {
        return Generator.GENERATE_TABLE(home.getAll(), newInstance.atributos());
    }

    protected void createComboBox() {
        this.comboBox = new MyJComboBox(home.getAll());
    }

    protected void createHome() {
        home = new Home<T>(clazz);
    }

    protected void addPanels(final JTabbedPane panel) {
        panel.addTab("General", null, edicion, "Edicion");
        // panel.addTab("Tabla", null, table, "tabla");
        panel.addTab("Filtro", null, search, "Filtro");
    }

    protected void addActions() {
        topPanel.getBtImprimir().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                PrintUtilities.printComponent(search.getTable().getScroll());
            }
        });

        topPanel.getBtCerrar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                GeneralFrame.this.setVisible(false);
            }
        });

        edicion.getBotonAgregar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                tengo = true;
                GeneralFrame.this.edicionAgregar();
                tengo = false;
            }
        });

        edicion.getBotonModificar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                GeneralFrame.this.edicionModificar();
                setEditionModel(home.createExample());
            }
        });
        edicion.getBotonCancelar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                GeneralFrame.this.edicionCancelar();
                setEditionModel(home.createExample());
            }
        });

        search.getTable().addtableListener(new MouseAdapter() {

            @Override
            public void mouseClicked(final MouseEvent e) {
                if (e.getClickCount() == 2) {
                    GeneralFrame.this.tableListener(e);
                }

            }
        });

        comboBox.addActionListener(new ActionMethodListener(this, "comboboxListener"));
    }

    protected void edicionAgregar() {
        home.save(edicion.getModel());
        edicion.setModel(home.createExample());
        SwingUtilities.updateComponentTreeUI(GeneralFrame.this);
    }

    public void comboboxListener() {
        this.setModel((T) comboBox.getSelectedItem());
    }

    protected void tableListener(final MouseEvent e) {
        this.setModel((T) search.getTable().getSelected());
    }

    public void setModel(final T observable) {
        setEditionModel(observable);
        edicion.getBotonModificar().setEnabled(true);
        SwingUtilities.updateComponentTreeUI(GeneralFrame.this);
    }

    protected void setEditionModel(final T observable) {
        edicion.setModel(observable);
    }

    protected void edicionModificar() {
        T model = edicion.getModel();
        if (model.getId() != null) {
            home.update(model);
            edicion.getBotonModificar().setEnabled(false);
            SwingUtilities.updateComponentTreeUI(GeneralFrame.this);
        }
    }

    protected void edicionCancelar() {
        panel.setSelectedComponent(edicion);
        edicion.getBotonModificar().setEnabled(false);
    }

    @Override
    public void mostrar() {
        this.setVisible(true);
    }

    @Override
    public String toString() {
        return nombre;
    }

    protected abstract void createForm(AbstractBindingPanel<T> edicion2);

    protected void createSearchForm(final SearchPanel<T> panel) {
        this.createForm(search);
    }

    public List<T> getObjects() {
        return home.getAll();
    }
    
    public void update(){
        SwingUtilities.updateComponentTreeUI(this);
    }

}
