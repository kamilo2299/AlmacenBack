package com.almacen.repository;

import org.springframework.data.repository.CrudRepository;

import com.almacen.model.*;

public interface IProducto extends CrudRepository<Producto, Long> {

}