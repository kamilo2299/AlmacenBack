package com.almacen.wrapper;

import java.util.ArrayList;
import java.util.List;

public class WrapperCompra {
	
	String cedula;
	String direccion;
	List<Long> productos;

	WrapperCompra(){
		this.productos = new ArrayList<Long>(); 
	}
	
	public List<Long> getProductos() {
		return productos;
	}

	public void setProductos(List<Long> productos) {
		this.productos = productos;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
	
	
}
