package es.ipartek.empresa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ipartek.empresa.entities.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
	public boolean existsByNombre( String nombre );
	public boolean existsByNombreAndIdNot( String nombre, Long id );
	
	public Producto findByNombre( String nombre );
}
