package com.almacen.service;


import java.util.List;

import com.almacen.model.*;
import com.almacen.wrapper.WrapperCompra;


public interface IProductoService {

	public List<Producto> getProductos();
	public Producto findProducto(long id);
	public void crearProducto(Producto p);
	public Factura calcularFactura(WrapperCompra idProductos);
	
}