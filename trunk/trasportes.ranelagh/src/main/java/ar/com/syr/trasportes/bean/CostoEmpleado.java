package ar.com.syr.trasportes.bean;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import ar.com.syr.trasportes.utils.IdentificablePersistentObject;

@Entity
@Table(name = "costo_empleado")
public class CostoEmpleado extends IdentificablePersistentObject implements Serializable{
		
//		public static final String EMPLEADO = "empleado";
		public static final String COSTO_TOTAL = "costoTotal";
		
		 @Id
		private String  id;
		
//		@OneToOne(cascade = CascadeType.ALL)
//		@PrimaryKeyJoinColumn
//		private Empleado empleado;
		
		@Basic
		private Double costoTotal = 0.0;
		
//		public void setEmpleado(Empleado legajo) {
//			this.empleado = legajo;
//		}
//		public Empleado getEmpleado() {
//			return empleado;
//		}
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
		public String[] atributos(){
			return new String[]{COSTO_TOTAL};
		}

}
