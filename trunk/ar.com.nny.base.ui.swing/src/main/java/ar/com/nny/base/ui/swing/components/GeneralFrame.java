package ar.com.nny.base.ui.swing.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
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

    protected JTabbedPane panel = new JTabbedPane();

    protected PanelEdicion<T> edicion;

    protected Home home;

    private String nombre;

    protected MyJComboBox comboBox;

    protected Boolean tengo;

    private SearchPanel<T> search;

    public GeneralFrame(final String name, final Class clase) {
        this.nombre = name;
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        T newInstance;
        this.createHome();

        newInstance = (T) ReflectionUtils.instanciate(clase);
        edicion = new PanelEdicion<T>(name, newInstance);
        search = createSearchPanel(name, newInstance);

        this.createComboBox();
        comboBox.addDefaultValue(home.createExample());
        this.topPanel = new TopPanel();
        this.table = this.createTable(newInstance);
        this.createForm(edicion);
        this.createSearchForm(search);
        this.addPanels();
        this.addActions();
        this.add(topPanel);
        this.add(panel);
        this.setSize(1024, 780);
        this.setVisible(false);
    }

    protected SearchPanel<T> createSearchPanel(final String name, T newInstance) {
        return new SearchPanel<T>(name, newInstance, home);
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
        return Generator.GENERATE_TABLE(home.buscarTodos(), newInstance.atributos());
    }

    protected void createComboBox() {
        this.comboBox = new MyJComboBox(home.buscarTodos());
    }

    protected void createHome() {
        // home = new GenericDao<IdentificablePersistentObject>(clase, nombre);
    }

    protected void addPanels() {
        panel.addTab("General", null, edicion, "Edicion");
        panel.addTab("Tabla", null, table, "tabla");
        panel.addTab("Filtro", null, search, "Filtro");
    }

    protected void addActions() {
        topPanel.getBtImprimir().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                PrintUtilities.printComponent(table.getScroll());

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
            }
        });
        edicion.getBotonCancelar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                GeneralFrame.this.edicionCancelar();
            }
        });

        table.addtableListener(new MouseAdapter() {

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
        home.agregar(edicion.getModel());
        edicion.setModel(home.createExample());
        SwingUtilities.updateComponentTreeUI(GeneralFrame.this);
    }

    public void comboboxListener() {
        this.setModel((T) comboBox.getSelectedItem());
    }

    protected void tableListener(final MouseEvent e) {
        this.setModel((T) table.getSelected());
    }

    public void setModel(final T observable) {
        edicion.setModel(observable);
        edicion.getBotonModificar().setEnabled(true);
    }

    protected void edicionModificar() {
        T model = edicion.getModel();
        if (model.getId() != null) {
            home.actualizar(model);
            edicion.setModel(home.createExample());
            edicion.getBotonModificar().setEnabled(false);
            SwingUtilities.updateComponentTreeUI(GeneralFrame.this);
        }
    }

    protected void edicionCancelar() {
        panel.setSelectedComponent(edicion);
        edicion.setModel(home.createExample());
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
        return home.buscarTodos();
    }

}
