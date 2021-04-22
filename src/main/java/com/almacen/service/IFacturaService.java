package com.almacen.service;


import java.util.List;

import com.almacen.model.*;
import com.almacen.wrapper.WrapperCompra;


public interface IFacturaService {

	public List<Factura> getFacturas();
	public Factura findFactura(long id);
	
}