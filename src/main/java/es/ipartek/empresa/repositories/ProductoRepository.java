package es.ipartek.empresa.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ipartek.empresa.entities.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
	public boolean existsByNombre( String nombre );
	public boolean existsByNombreAndIdNot( String nombre, Long id );
	
	public Producto findByNombre( String nombre );
	
	// Recuperar todos los productos con una categoria ID
	public List<Producto> findAllByCategoriaId( Long id );
	
	// Recuperar todos los productos con parte del nombre 
	public List<Producto> findAllByNombreContainingIgnoreCase( String nombre ); 
	public List<Producto> findAllByNombreContainingIgnoreCase( String nombre, Sort campo ); 
		
}
