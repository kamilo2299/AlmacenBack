package com.almacen.wrapper;

import java.util.ArrayList;
import java.util.List;

public class WrapperEditarFactura {
	
	Long idFactura;
	List<Long> productosNuevos;
	List<Long> productosBorrados;

	WrapperEditarFactura(){
		this.productosNuevos = new ArrayList<Long>(); 
		this.productosBorrados = new ArrayList<Long>();
	}

	public Long getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}

	public List<Long> getProductosNuevos() {
		return productosNuevos;
	}

	public void setProductosNuevos(List<Long> productosNuevos) {
		this.productosNuevos = productosNuevos;
	}

	public List<Long> getProductosBorrados() {
		return productosBorrados;
	}

	public void setProductosBorrados(List<Long> productosBorrados) {
		this.productosBorrados = productosBorrados;
	}
	
	
	
	
	
	
}
