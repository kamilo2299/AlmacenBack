package com.almacen.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Facturas")
public class Factura implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	long id;
	
	@ManyToMany
	List<Producto> productos;
	
	@JsonIgnore
	@ManyToOne
	Usuario user;
	
	LocalDateTime fecha;
	
	long precioSinIva;
	long precioIva;
	long precioDomicilio;
	long precioTotal;
	
	
	public Factura(){
		productos = new ArrayList<Producto>();
		this.precioSinIva = 0;
		this.precioIva = 0;
		this.precioDomicilio = 0;
		this.precioTotal = 0;
		this.fecha = LocalDateTime.now();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	public void addProducto(Producto producto) {
		this.productos.add(producto);
	}

	public long getPrecioSinIva() {
		return precioSinIva;
	}

	public void setPrecioSinIva(long precioSinIva) {
		this.precioSinIva = precioSinIva;
	}

	public long getPrecioIva() {
		return precioIva;
	}

	public void setPrecioIva(long precioIva) {
		this.precioIva = precioIva;
	}

	public long getPrecioDomicilio() {
		return precioDomicilio;
	}

	public void setPrecioDomicilio(long precioDomicilio) {
		this.precioDomicilio = precioDomicilio;
	}

	public long getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(long f) {
		this.precioTotal = f;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public void borrarElemento(Producto e) {
		this.productos.remove(e);
	}
	
	public void agregarElemento(Producto e) {
		this.productos.add(e);
	}
	
	
	
	
	
}