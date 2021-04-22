package com.almacen.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Usuarios")
public class Usuario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	long id;
	
	String cedula;
	
	String direccion;
	
	
	@OneToMany
	List<Factura> facturas;
	

	
	public Usuario(){
		facturas = new ArrayList<Factura>();
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
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



	public List<Factura> getFacturas() {
		return facturas;
	}



	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}
	
	public void addFactura(Factura f) {
		this.facturas.add(f);
	}

	


}
