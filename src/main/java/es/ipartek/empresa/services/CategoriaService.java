package es.ipartek.empresa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ipartek.empresa.entities.Categoria;
import es.ipartek.empresa.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired CategoriaRepository categorias;
	
	public List<Categoria> obtenerTodos(){
		return categorias.findAll();
	}
	
	public Categoria obtenerPorId( Long id ) {
		return categorias.getReferenceById(id);
	}
	
	
	public void guardar( Categoria objCategoria ) {
		categorias.save(objCategoria);
	}
	
	public void eliminar( Categoria objCategoria ) {
		categorias.delete(objCategoria);
	}
	
	public void eliminarPorId( Long id ) {
		categorias.deleteById(id);
	}
}
