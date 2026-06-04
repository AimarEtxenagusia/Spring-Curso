package es.ipartek.empresa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
	
	public List<Producto> obtenerFiltrados( Filtro filtro ) {
		switch( filtro.getOrdenado() ) {
			case "CAROS": 
				return productos.findAllByNombreContainingIgnoreCase(filtro.getNombre(), Sort.by(Sort.Direction.DESC, "precio"));
			case "BARATOS": 
				return productos.findAllByNombreContainingIgnoreCase(filtro.getNombre(), Sort.by(Sort.Direction.ASC, "precio"));
			case "STOCK": 
				return productos.findAllByNombreContainingIgnoreCase(filtro.getNombre(), Sort.by(Sort.Direction.DESC, "stock"));
			case "CATEGORIA": 
				return productos.findAllByNombreContainingIgnoreCase(filtro.getNombre(), Sort.by(Sort.Direction.DESC, "categoria"));
			default: 
				return productos.findAllByNombreContainingIgnoreCase(filtro.getNombre());				
		}
	}

	
	
	
	public void guardar( Producto producto ) {
		productos.save(producto); 
	}
	
	public void eliminarPorId( Long id ) {
		productos.deleteById(id);
	}
	
}
