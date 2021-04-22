package com.almacen.service;


import java.util.List;

import com.almacen.model.*;
import com.almacen.wrapper.WrapperCompra;


public interface IUsuarioService {

	public List<Usuario> getUsuarios();
	public Usuario findUsuario(long id);
	public void crearUsuario(Usuario p);
	
}