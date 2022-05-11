package com.proyecto.modelo.dto;
import java.io.Serializable;


public class IngredienteEnRecetaDTO implements Serializable{
		
		/**
	 * 
	 */
		private static final long serialVersionUID = 1L;
		
		private int idIngrediente;
		private float cantidad;
		private String unidad;
		
		public IngredienteEnRecetaDTO() {
		}


		public int getIdIngrediente() {
			return idIngrediente;
		}


		public void setIdIngrediente(int idIngrediente) {
			this.idIngrediente = idIngrediente;
		}


		public float getCantidad() {
			return cantidad;
		}


		public void setCantidad(float cantidad) {
			this.cantidad = cantidad;
		}


		public String getUnidad() {
			return unidad;
		}


		public void setUnidad(String unidad) {
			this.unidad = unidad;
		}
		
	

		


}
