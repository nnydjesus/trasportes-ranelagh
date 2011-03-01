package ar.com.syr.trasportes.bean;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import ar.com.syr.trasportes.utils.IdentificablePersistentObject;
import ar.com.syr.trasportes.utils.Observable;

@Entity
public class CostoEmpleado extends IdentificablePersistentObject implements Serializable{
		
		public static final String LEGAJO = "legajo";
		public static final String COSTO_TOTAL = "costoTotal";
		
		@Id
		@GeneratedValue
		private String id;
		
		@Basic
		private String legajo;
		
		@Basic
		private Double costoTotal;
		
		public void setLegajo(String legajo) {
			this.legajo = legajo;
		}
		public String getLegajo() {
			return legajo;
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
		public String[] atributos(){
			return new String[]{LEGAJO,COSTO_TOTAL};
		}

}
