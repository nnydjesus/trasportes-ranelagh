package main;

import java.util.ArrayList;
import java.util.List;

import ar.com.syr.transportes.bean.Empleado;

public class TransportesAppModel {
    
    private  List<Empleado> empleados = new ArrayList<Empleado>();
    private  List<Empleado> remitos = new ArrayList<Empleado>();
    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
    public List<Empleado> getEmpleados() {
        return empleados;
    }

}
