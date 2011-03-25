package ar.com.syr.transportes.search;

import org.apache.commons.collections.Predicate;

import ar.com.nny.base.search.Home;
import ar.com.syr.transportes.bean.Empleado;

public class HomeEmpleado extends Home<Empleado> {

    private final static HomeEmpleado INSTANCE = new HomeEmpleado();

    public static HomeEmpleado getInstance() {
        return INSTANCE;
    }

    private HomeEmpleado() {
        super(Empleado.class);
    }

    @Override
    protected Predicate getCriterio(final Empleado example) {
        String id = example.getId();
        String apellido = example.getApellido();
        String nombre = example.getNombre();

        if (!id.equals("") && !nombre.equals("") && !apellido.equals(""))
            return this.getCriterioPorIdNombreYApellido(example);
        if (!id.equals("") && apellido.equals("") && nombre.equals(""))
            return this.getCriterioPorId(id);
        if (!id.equals("") && !apellido.equals("") && nombre.equals(""))
            return this.getCriterioPorIdYApellido(example);
        if (!id.equals("") && apellido.equals("") && !nombre.equals(""))
            return this.getCriterioPorIdYNombre(example);
        if (id.equals("") && !apellido.equals("") && !nombre.equals(""))
            return this.getCriterioPorNombreYApellido(example);
        if (!apellido.equals(""))
            return this.getCriterioPorApellido(example);
        if (!nombre.equals(""))
            return this.getCriterioPorNombre(example);
        return this.getCriterioTodas();
    }

    protected Predicate getCriterioPorNombre(final Empleado empleado) {
        return new Predicate() {
            public boolean evaluate(final Object arg) {
                return HomeEmpleado.this.mismoNombre(empleado, (Empleado) arg);
            }
        };
    }

    protected Predicate getCriterioPorApellido(final Empleado empleado) {
        return new Predicate() {
            public boolean evaluate(final Object arg) {
                return HomeEmpleado.this.mismoApellido(empleado, (Empleado) arg);
            }
        };
    }

    protected Predicate getCriterioPorNombreYApellido(final Empleado empleado) {
        return new Predicate() {
            public boolean evaluate(final Object arg) {
                Empleado unaEntidad = (Empleado) arg;
                return HomeEmpleado.this.mismoNombre(empleado, unaEntidad)
                        && HomeEmpleado.this.mismoApellido(empleado, unaEntidad);
            }
        };
    }

    protected Predicate getCriterioPorIdNombreYApellido(final Empleado empleado) {
        return new Predicate() {
            public boolean evaluate(final Object arg) {
                Empleado unaEntidad = (Empleado) arg;
                return HomeEmpleado.this.mismoId(empleado, unaEntidad)
                        && HomeEmpleado.this.mismoNombre(empleado, unaEntidad)
                        && HomeEmpleado.this.mismoApellido(empleado, unaEntidad);
            }

        };
    }

    protected Predicate getCriterioPorIdYNombre(final Empleado empleado) {
        return new Predicate() {
            public boolean evaluate(final Object arg) {
                Empleado unaEntidad = (Empleado) arg;
                return HomeEmpleado.this.mismoId(empleado, unaEntidad)
                        && HomeEmpleado.this.mismoNombre(empleado, unaEntidad);
            }

        };
    }

    protected Predicate getCriterioPorIdYApellido(final Empleado empleado) {
        return new Predicate() {
            public boolean evaluate(final Object arg) {
                Empleado unaEntidad = (Empleado) arg;
                return HomeEmpleado.this.mismoId(empleado, unaEntidad)
                        && HomeEmpleado.this.mismoApellido(empleado, unaEntidad);
            }

        };
    }

    protected boolean mismoId(final Empleado empleado, final Empleado unaEntidad) {
        return unaEntidad.getId().toLowerCase().contains(empleado.getId().toLowerCase());
    }

    protected boolean mismoNombre(final Empleado empleado, final Empleado unaEntidad) {
        return unaEntidad.getNombre().toLowerCase().contains(empleado.getNombre().toLowerCase());
    }

    protected boolean mismoApellido(final Empleado empleado, final Empleado unaEntidad) {
        return unaEntidad.getApellido().toLowerCase().contains(empleado.getApellido().toLowerCase());
    }

    @Override
    public Empleado createExample() {
        return new Empleado();
    }

}
