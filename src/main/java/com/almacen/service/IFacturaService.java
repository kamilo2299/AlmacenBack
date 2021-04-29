package com.almacen.service;


import java.util.List;

import com.almacen.model.*;
import com.almacen.wrapper.WrapperCompra;
import com.almacen.wrapper.WrapperEditarFactura;


public interface IFacturaService {

	public List<Factura> getFacturas();
	public Factura findFactura(long id);
	public Factura calcularFactura(WrapperCompra idProductos);
	public Factura editarFactura(WrapperEditarFactura factura);
	public String borrarFactura(Long id);
		
}