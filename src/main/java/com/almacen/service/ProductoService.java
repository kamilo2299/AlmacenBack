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
public class ProductoService implements IProductoService {
	
	@Autowired
	private IProducto productosRepository;
	
	@Autowired
	private IFactura facturasRepository;
	
	@Autowired
	private IUsuario usuarioRepository;

	public List<Producto> getProductos() {
		return (List<Producto>) productosRepository.findAll();
	}

	@Override
	public Producto findProducto(long id) {
		Producto p = productosRepository.findById(id).get();
		return p;
	}
	
	public void crearProducto(Producto p) {
		productosRepository.save(p);
	}
	
	public Factura calcularFactura(WrapperCompra idProductos) {
		Factura fact = new Factura();
		Usuario user;
		user = this.buscarUsuario(idProductos.getCedula());
		
		long precioFinal = 0;
		long precioDomicilio = 10000;
		long precioIva = 0;
		
		if(user!=null) {
			
			for(int i=0; i<idProductos.getProductos().size(); i++) {
				try {
					fact.addProducto(productosRepository.findById(idProductos.getProductos().get(i)).get());
					precioFinal+= fact.getProductos().get(i).getPrecio();
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("No existe producto");
				}
				
				
			}
			
			if(fact.getProductos().size() > 0) {
				if(precioFinal > 70000) {
					if(precioFinal > 100000) {
						precioDomicilio = 0;	
					}
					precioIva = (long) (precioFinal*0.19);
				}
				
				fact.setPrecioDomicilio(precioDomicilio);
				fact.setPrecioSinIva(precioFinal);
				fact.setPrecioIva(precioIva);
				fact.setPrecioTotal((long) (precioDomicilio + precioIva + precioFinal));
				user.addFactura(fact);
				
				facturasRepository.save(fact);
				usuarioRepository.save(user);
				return fact;
			}
			
		}
		
		return null;
	}
	
	public Usuario buscarUsuario(String cedula) {
		List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
		Usuario user = new Usuario();
		for(int i=0; i<usuarios.size(); i++) {
			if(usuarios.get(i).getCedula().equals(cedula)) {
				user = usuarios.get(i);
				return user;
			}
		}
		System.out.println("No existe usuario");
		return null;
		
	}
}
