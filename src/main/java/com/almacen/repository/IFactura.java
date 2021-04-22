package com.almacen.repository;

import org.springframework.data.repository.CrudRepository;

import com.almacen.model.*;

public interface IFactura extends CrudRepository<Factura, Long> {

}