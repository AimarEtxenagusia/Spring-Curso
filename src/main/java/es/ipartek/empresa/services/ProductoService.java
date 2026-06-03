package es.ipartek.empresa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ipartek.empresa.dto.Filtro;
import es.ipartek.empresa.entities.Producto;
import es.ipartek.empresa.repositories.ProductoRepository;

@Service
public class ProductoService {

	@Autowired ProductoRepository productos; 
	
	public List<Producto> obtenerTodos() {
		return productos.findAll(); 
	}
	
	public Producto obtenerPorId( Long id ) {
		return productos.findById(id).orElse(null);
		//return productos.getReferenceById(id);
	}
	
	public boolean comprobarProductioDuplicado( Producto producto ) {
		Producto producto_bd = productos.findByNombre(producto.getNombre());
		if ( producto.getId() == null ) {
			return producto_bd != null;
		} else {
			return producto_bd.getId() != producto.getId();
		}
	}
	
	public List<Producto> obtenerProductosPorCategoria( Long id ){
		return productos.findAllByCategoriaId(id);
	}
	
	//public List<Producto> obtenerFiltrados( Filtro filtro ){
	//	if( filtro.getOrdenado().equals("CAROS"))
	//}
	
	
	
	public void guardar( Producto producto ) {
		productos.save(producto); 
	}
	
	public void eliminarPorId( Long id ) {
		productos.deleteById(id);
	}
	
}
