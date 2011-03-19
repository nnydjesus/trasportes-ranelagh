package ar.com.syr.transportes.bean;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.Hibernate;

import ar.com.nny.base.persistence.SerializationStrategy;
import ar.com.nny.base.persistence.Through;
import ar.com.nny.base.utils.HibernateUtil;
import ar.com.nny.base.utils.IdentificablePersistentObject;

@Entity
@Table(name = "costo_empleado")
public class CostoEmpleado extends IdentificablePersistentObject implements Serializable{
		
	private static final long serialVersionUID = -7880912781384257512L;

//		public static final String EMPLEADO = "empleado";
		public static final String COSTO_TOTAL = "costoTotal";
		
		 @Id
		private String  id;
		
		@OneToOne(fetch = FetchType.LAZY)
//		@org.hibernate.annotations.LazyToOne (org.hibernate.annotations.LazyToOneOption.NO_PROXY))
		@SerializationStrategy(access = Through.TRANSIENT)
		private Empleado empleado;
		
		@Basic
		private Double costoTotal = 0.0;
		
		public void setEmpleado(Empleado legajo) {
			this.empleado = legajo;
		}
		public Empleado getEmpleado() {
			return empleado;
		}
		public void setCostoTotal(Double costoTotal) {
			this.costoTotal = costoTotal;
		}
		public Double getCostoTotal() {
			return costoTotal;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getId() {
			return id;
		}
		
		@Override
		public String mostrar() {
			if(empleado == null)
				return "";
			if(!Hibernate.isInitialized(empleado)){
				Hibernate.initialize(empleado);
			}
			return empleado.mostrar();
		}
		
		@Override
		public String[] atributos(){
			return new String[]{COSTO_TOTAL};
		}

}
