package com.almacen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.almacen.model.*;
import com.almacen.repository.IFactura;
import com.almacen.repository.IProducto;
import com.almacen.repository.IUsuario;
import com.almacen.wrapper.WrapperCompra;

@Service
public class UsuarioService implements IUsuarioService {
	
	@Autowired
	private IUsuario usuariosRepository;



	@Override
	public Usuario findUsuario(long id) {
		Usuario p = usuariosRepository.findById(id).get();
		return p;
	}

	@Override
	public List<Usuario> getUsuarios() {
		return (List<Usuario>) usuariosRepository.findAll();
	}

	@Override
	public void crearUsuario(Usuario p) {
		// TODO Auto-generated method stub
		usuariosRepository.save(p);
	}

}
