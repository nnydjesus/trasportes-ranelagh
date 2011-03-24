package ar.com.syr.transportes.serach;

import org.apache.commons.collections.Predicate;

import ar.com.nny.base.search.Home;
import ar.com.syr.transportes.bean.Empleado;

public class HomeEmpleado extends Home<Empleado>{

    public HomeEmpleado() {
        super(Empleado.class);
    }

    @Override
    protected Predicate getCriterio(Empleado example) {
        String id = example.getId();
        String apellido = example.getApellido();
        String nombre = example.getNombre();
        
        if(!id.equals("") && !nombre.equals("") && !apellido.equals(""))
            return getCriterioPorIdNombreYApellido(example);
        if(!id.equals("") && apellido.equals("") && nombre.equals(""))
            return getCriterioPorId(id);
        if(!id.equals("") && !apellido.equals("") && nombre.equals(""))
            return getCriterioPorIdYApellido(example);
        if(!id.equals("") && apellido.equals("") && !nombre.equals(""))
            return getCriterioPorIdYNombre(example);
        if(id.equals("") && !apellido.equals("") && !nombre.equals(""))
            return getCriterioPorNombreYApellido(example);
        if(!apellido.equals(""))
            return getCriterioPorApellido(example);
        if(!nombre.equals(""))
            return getCriterioPorNombre(example);
        return getCriterioTodas();
    }
    
    protected Predicate getCriterioPorNombre(final Empleado empleado) {
        return new Predicate() {
            public boolean evaluate(Object arg) {
                return mismoNombre(empleado, (Empleado) arg);
            }
        };
    }
    protected Predicate getCriterioPorApellido(final Empleado empleado) {
        return new Predicate() {
            public boolean evaluate(Object arg) {
                return mismoApellido(empleado, (Empleado) arg);
            }
        };
    }
    protected Predicate getCriterioPorNombreYApellido(final Empleado empleado) {
        return new Predicate() {
            public boolean evaluate(Object arg) {
                Empleado unaEntidad = (Empleado) arg;
                return mismoNombre(empleado, unaEntidad) &&
                        mismoApellido(empleado, unaEntidad);
            }
        };
    }
    protected Predicate getCriterioPorIdNombreYApellido(final Empleado empleado) {
        return new Predicate() {
            public boolean evaluate(Object arg) {
                Empleado unaEntidad = (Empleado) arg;
                return mismoId(empleado, unaEntidad) && mismoNombre(empleado, unaEntidad) &&
                                mismoApellido(empleado, unaEntidad);
            }

        };
    }
    protected Predicate getCriterioPorIdYNombre(final Empleado empleado) {
        return new Predicate() {
            public boolean evaluate(Object arg) {
                Empleado unaEntidad = (Empleado) arg;
                return mismoId(empleado, unaEntidad) && mismoNombre(empleado, unaEntidad);
            }
            
        };
    }
    protected Predicate getCriterioPorIdYApellido(final Empleado empleado) {
        return new Predicate() {
            public boolean evaluate(Object arg) {
                Empleado unaEntidad = (Empleado) arg;
                return mismoId(empleado, unaEntidad) && mismoApellido(empleado, unaEntidad);
            }
            
        };
    }

    
    protected boolean mismoId(final Empleado empleado, Empleado unaEntidad) {
        return unaEntidad.getId().toLowerCase().contains(empleado.getId().toLowerCase());
    }
    protected boolean mismoNombre(final Empleado empleado, Empleado unaEntidad) {
        return unaEntidad.getNombre().toLowerCase().contains(empleado.getNombre().toLowerCase());
    }
    
    protected boolean mismoApellido(final Empleado empleado, Empleado unaEntidad) {
        return unaEntidad.getApellido().toLowerCase().contains(empleado.getApellido().toLowerCase());
    }
    
    @Override
    public Empleado createExample() {
        return new Empleado();
    }


}
