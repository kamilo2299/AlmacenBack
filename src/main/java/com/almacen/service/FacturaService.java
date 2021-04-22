package com.almacen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.almacen.model.*;
import com.almacen.repository.IFactura;
import com.almacen.repository.IProducto;
import com.almacen.wrapper.WrapperCompra;

@Service
public class FacturaService implements IFacturaService {
	
	@Autowired
	private IFactura facturasRepository;

	public List<Factura> getFacturas() {
		return (List<Factura>) facturasRepository.findAll();
	}

	@Override
	public Factura findFactura(long id) {
		Factura p = facturasRepository.findById(id).get();
		return p;
	}

}
