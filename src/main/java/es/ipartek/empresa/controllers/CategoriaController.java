package es.ipartek.empresa.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.ipartek.empresa.entities.Categoria;
import es.ipartek.empresa.services.CategoriaService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired CategoriaService categoriaService;
	
	@GetMapping
	public String listar( Model modelo ) {
		List<Categoria> categorias = new ArrayList<>();
		categorias = categoriaService.obtenerTodos();
		modelo.addAttribute("categorias", categorias);
		return "categorias/lista";
	}
	
	@PostMapping("/alta")
	public String guardar( @Valid @ModelAttribute("nueva_categoria") Categoria categoria, BindingResult result ) {
		
		String nombre_nuevo = categoria.getNombre();
		if( categoria.getId() == null ) {
			if( categoriaService.existeCategoriaNombre(nombre_nuevo) ) {
				result.rejectValue("nombre", "", "Nombre de categoria existente");
			}
		} else {
			String nombre_bd = categoriaService.obtenerPorId(categoria.getId()).getNombre();
		}
		
		if( result.hasErrors() ) {
			return "categorias/formulario";
		} else {
			categoriaService.guardar(categoria);
			return "redirect:/categorias";
		}
	}
	
	@GetMapping("/alta")
	public String alta( Model modelo ) {
		Categoria nuevaCategoria = new Categoria();
		modelo.addAttribute("nueva_categoria", nuevaCategoria);
		return "categorias/formulario";
	}
	
	@GetMapping("/editar/{id}")
	public String editar( @PathVariable Long id, Model modelo ) {
		// Recuperar el objeto categoria correspondiente al ID recibido
		Categoria categoria = categoriaService.obtenerPorId(id);
		modelo.addAttribute("nuevaCategoria", categoria);
		return "categorias/formulario";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarPorId( @PathVariable Long id ) {
		categoriaService.eliminarPorId(id);
		return "redirect:/categorias";
	}
	
	
	
}
