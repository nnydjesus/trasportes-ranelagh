package ar.com.syr.transportes.search;

import java.util.HashMap;
import java.util.Map;

import ar.com.nny.base.search.Home;
import ar.com.nny.base.search.HomeLocator;
import ar.com.syr.transportes.bean.CostoEmpleado;
import ar.com.syr.transportes.bean.Empleado;
import ar.com.syr.transportes.bean.Remito;

public class HomeTransportesLocator implements HomeLocator{
   private Map<Class, Home> homes = new HashMap<Class, Home>();
   private static final HomeTransportesLocator instance = new HomeTransportesLocator();
   
   private HomeTransportesLocator() {
       this.homes.put(Remito.class, HomeRemito.getInstance());
       this.homes.put(Empleado.class, HomeEmpleado.getInstance());
       this.homes.put(CostoEmpleado.class, HomeCostoempleado.getInstance());
   }
   
   public Home getHome(Class clazz) {
       return homes.get(clazz);
   }

    public static HomeTransportesLocator getInstance() {
        return instance;
    }
   
}
