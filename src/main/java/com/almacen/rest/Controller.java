package com.almacen.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.almacen.model.*;
import com.almacen.service.IFacturaService;
import com.almacen.service.IProductoService;
import com.almacen.service.IUsuarioService;
import com.almacen.wrapper.WrapperCompra;
import com.almacen.wrapper.WrapperEditarFactura;

import java.util.List;


@RestController
@RequestMapping (value="almacen")
public class Controller {
	
	@Autowired
	private IProductoService productosService;
	
	@Autowired
	private IFacturaService facturasService;
	
	@Autowired
	private IUsuarioService usuariosService;

	@GetMapping(value="productos/all")
	public List<Producto> getProductos() {
		return productosService.getProductos();
	}
	
	@GetMapping(value="productos/{idProducto}")
	public Producto getProducto(
		@PathVariable("idProducto") final Long id
	) {
		return productosService.findProducto(id);
	}
	
	@PostMapping(value="crear/producto")
	public void crearProducto(
		@RequestBody final Producto p
	) {
		productosService.crearProducto(p);
	}
	
	@GetMapping(value="calcular")
	public Factura calculo(
		@RequestBody final WrapperCompra data
	) {
		
		return facturasService.calcularFactura(data);
	}
	
	@GetMapping(value="facturas/all")
	public List<Factura> getFactura() {
		return facturasService.getFacturas();
	}
	
	@GetMapping(value="facturas/{idProducto}")
	public Factura getFactura(
		@PathVariable("idProducto") final Long id
	) {
		return facturasService.findFactura(id);
	}
	
	@GetMapping(value="usuarios/all")
	public List<Usuario> getUsuarios() {
		return usuariosService.getUsuarios();
	}
	
	@GetMapping(value="usuarios/{idProducto}")
	public Usuario getUsuario(
		@PathVariable("idProducto") final Long id
	) {
		return usuariosService.findUsuario(id);
	}
	
	@PostMapping(value="crear/usuario")
	public void crearProducto(
		@RequestBody final Usuario p
	) {
		usuariosService.crearUsuario(p);
	}
	
	@DeleteMapping(value="borrarFactura/{idFactura}")
	public String borrarFactura(
		@PathVariable("idFactura") final Long id
	) {
		String resultado = " ";
		resultado = facturasService.borrarFactura(id);
		return resultado;
	}
	
	@PutMapping(value="editarFactura")
	public Factura editarFactura(
		@RequestBody final WrapperEditarFactura data
	) {
		return facturasService.editarFactura(data);
	
	}
	
	
}