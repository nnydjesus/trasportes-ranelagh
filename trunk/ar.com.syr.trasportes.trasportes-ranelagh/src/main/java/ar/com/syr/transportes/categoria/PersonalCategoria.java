package ar.com.syr.transportes.categoria;

import javax.swing.JFrame;

import ar.com.nny.base.common.Item;
import ar.com.nny.base.common.ItemComposite;
import ar.com.syr.transportes.ui.amb.ABMEmpleado;
import ar.com.syr.transportes.ui.amb.ABMUnidad;
import ar.com.syr.transportes.ui.principales.EmpleadoUi;
import ar.com.syr.transportes.ui.principales.UnidadUI;

public class PersonalCategoria extends ItemComposite {
    private static final long serialVersionUID = -6130962545676299492L;
    private Nomina nomina;
    private ABMEmpleado abmEmpleado;
    private EmpleadoUi empleadoUI;

    public Nomina getNomina() {
        return nomina;
    }

    public void setNomina(Nomina nomina) {
        this.nomina = nomina;
    }

    public ABMEmpleado getAbmEmpleado() {
        return abmEmpleado;
    }

    public void setAbmEmpleado(ABMEmpleado abmEmpleado) {
        this.abmEmpleado = abmEmpleado;
    }

    public EmpleadoUi getEmpleadoUI() {
        return empleadoUI;
    }

    public void setEmpleadoUI(EmpleadoUi empleadoUI) {
        this.empleadoUI = empleadoUI;
    }

    public PersonalCategoria(JFrame parent) {
        this.nomina = new Nomina(parent);
        this.add(nomina);
        this.abmEmpleado = new ABMEmpleado(parent);
        this.add(abmEmpleado.setNombre("Nuevo Empleado"));
        this.empleadoUI = new EmpleadoUi();
        this.add(empleadoUI);
        this.add(new UnidadUI());
        this.add( new ABMUnidad(parent).setNombre("Nueva Unidad"));
//        this.add(new AdelantosUI());
//        this.add(new SancionesUI());
//        this.add(new AusenciaUI());
//        this.add(new VacacionesUI());
//        this.add(new CostoEmpleadoUi());
        
    }

    @Override
    public String toString() {
        return "Personal";
    }

    
    @Override
    public void mostrar(Object item) {
        ((Item)item).mostrar();     
    }

    @Override
    public void updateTree() {
        nomina.updateTree();
        super.update();
    }
    
    
}
