package com.almacen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.almacen.model.*;
import com.almacen.repository.IFactura;
import com.almacen.repository.IProducto;
import com.almacen.repository.IUsuario;
import com.almacen.wrapper.WrapperCompra;
import com.almacen.wrapper.WrapperEditarFactura;

@Service
public class FacturaService implements IFacturaService {
	
	@Autowired
	private IFactura facturasRepository;

	@Autowired
	private IProducto productosRepository;
	
	@Autowired
	private IUsuario usuarioRepository;
	
	@Autowired
	private ProductoService pService;
	
	@Autowired
	private UsuarioService uService;
	
	public List<Factura> getFacturas() {
		return (List<Factura>) facturasRepository.findAll();
	}

	@Override
	public Factura findFactura(long id) {
		Factura p = facturasRepository.findById(id).get();
		return p;
	}

	
	public String borrarFactura(Long id) {
		String resultado = " ";
		Factura fact = new Factura();
		fact = this.buscarFactura(id);
		Usuario user = uService.findUsuario(fact.getUser().getId()); 
		LocalDateTime tiempoActual = LocalDateTime.now();
		LocalDateTime fechaFactura12 = fact.getFecha();
		long precioACobrar = (long) (fact.getPrecioTotal()*0.10);
		
		fechaFactura12 = fechaFactura12.plusHours(12);
		
		user.borrarFactura(fact);
		
		if(tiempoActual.isAfter(fechaFactura12)) {
			System.out.println("Despues de 12 horas");
			resultado = "Se elimina factura "+ id + ". Se ha cargado al usuario 10% (" + precioACobrar +") del precio de la factura "; 
			user.setValorpagar(precioACobrar);
			
		}
		else {
			System.out.println("Antes de 12 horas");
			resultado = "Se elimina factura "+ id + " sin problemas"; 
			user.setValorpagar(valorPorPagarUsuario(user));
		}
		
		facturasRepository.delete(fact);
		usuarioRepository.save(user);

		return resultado;
	}
	
	

	public Factura calcularFactura(WrapperCompra idProductos) {
		Factura fact = new Factura();
		Usuario user = new Usuario();
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
				fact = calcularPrecio(fact);
				fact.setUser(user);
				user.addFactura(fact);
				user.setValorpagar(user.getValorpagar()+fact.getPrecioTotal());
				facturasRepository.save(fact);
				usuarioRepository.save(user);
				return fact;
			}
			
		}
		
		return null;
	}
	
	public Factura editarFactura(WrapperEditarFactura editfactura) {
		Factura fact = new Factura();
		fact = this.buscarFactura(editfactura.getIdFactura());
		Usuario user = uService.findUsuario(fact.getUser().getId());

		List<Producto> productosNuevos = new ArrayList<Producto>();
		List<Producto> productosBorrados = new ArrayList<Producto>();
		LocalDateTime fechaFactura5 = fact.getFecha();
		fechaFactura5 = fechaFactura5.plusHours(5);
		LocalDateTime tiempoActual = LocalDateTime.now();
		Long precioProductosAntiguos = (long) 0;
		Long precioProductosNuevos = (long) 0;
		
		if(tiempoActual.isAfter(fechaFactura5)) {
			System.out.println("Despues de 5 horas");
		}
		else {
			System.out.println("Antes de 5 horas");
			for(int i=0; i<editfactura.getProductosNuevos().size(); i++) {
				Producto produc = new Producto();
				produc = pService.findProducto(editfactura.getProductosNuevos().get(i));
				productosNuevos.add(produc);
				precioProductosNuevos += produc.getPrecio();
			}
			for(int i=0; i<editfactura.getProductosBorrados().size(); i++) {
				Producto produc = new Producto();
				produc = pService.findProducto(editfactura.getProductosBorrados().get(i));
				productosBorrados.add(produc);
				precioProductosAntiguos += produc.getPrecio();
			}
			
			if(precioProductosAntiguos <= precioProductosNuevos) {
				System.out.println("Se hace modificación");
				for(int i=0; i<editfactura.getProductosNuevos().size(); i++) {
					fact.agregarElemento(productosNuevos.get(i));
				}
				
				for(int i=0; i<editfactura.getProductosBorrados().size(); i++) {
					fact.borrarElemento(productosBorrados.get(i));
				}
				
				fact = calcularPrecio(fact);
				user.setValorpagar(valorPorPagarUsuario(user));
				
				facturasRepository.save(fact);
				usuarioRepository.save(user);
				return fact;
			}
		}
		
		return fact;
	}
	
	public Factura calcularPrecio(Factura fact) {
		long precioFinal = 0;
		long precioDomicilio = 10000;
		long precioIva = 0;
		
		for(int i=0; i<fact.getProductos().size(); i++) {
			precioFinal+= fact.getProductos().get(i).getPrecio();
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
		}
		
		return fact;
	}
	
	public long valorPorPagarUsuario(Usuario user) {
		long valor = 0;
		for(int i=0; i<user.getFacturas().size(); i++) {
			valor+= user.getFacturas().get(i).getPrecioTotal();
		}
			
		return valor;
	}
	
	
	public Usuario buscarUsuario(String id) {
		Usuario user = new Usuario();
		try {
			user = pService.buscarUsuario(id);
		} catch (Exception e) {
			// TODO: handle exception
			user = null;
			System.out.println("Usuario no existe");
		}
		return user;
	}
	
	public Factura buscarFactura(long id) {
		Factura fact = new Factura();
		try {
			fact = this.findFactura(id);
		} catch (Exception e) {
			// TODO: handle exception
			fact = null;
			System.out.println("Factura no existe");
		}
		return fact;
	}
	

}
