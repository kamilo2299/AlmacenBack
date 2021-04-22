package com.almacen.repository;

import org.springframework.data.repository.CrudRepository;

import com.almacen.model.Usuario;

public interface IUsuario extends CrudRepository<Usuario, Long>{

}
