package es.ipartek.empresa.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.ipartek.empresa.entities.Categoria;
import es.ipartek.empresa.entities.Producto;
import es.ipartek.empresa.services.CategoriaService;
import es.ipartek.empresa.services.ProductoService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	
	@Autowired ProductoService productoService; 
	@Autowired CategoriaService categoriaService; 
	
	@GetMapping
	public String listar( Model modelo, @RequestParam( required = false ) Long categoria) {
		List<Producto> productos = new ArrayList<>();
		
		if(categoria == null) {
			productos = productoService.obtenerTodos();
		} else {
			productos = productoService.obtenerProductosPorCategoria(categoria);
			
		}

		modelo.addAttribute("categoria", categoria);
		modelo.addAttribute("productos", productos);
		
		return "productos/lista"; 
	}
	
	@GetMapping("/alta")
	public String alta( Model modelo) {
		// Instancio el nuevo producto a dar de alta
		Producto producto = new Producto();
		modelo.addAttribute("producto_edicion", producto);
		// Obtengo el listado de las categorias para la seleccion
		List<Categoria> categorias = categoriaService.obtenerTodos();
		modelo.addAttribute("categorias", categorias); 
		return "productos/formulario"; 
	}
	
	@GetMapping("/editar/{id}")
	public String editar( @PathVariable Long id, Model modelo ) {
		// Instancio el nuevo producto a dar de alta
		Producto producto = productoService.obtenerPorId(id); 
		modelo.addAttribute("producto_edicion", producto);
		// Obtengo el listado de las categorias para la seleccion
		List<Categoria> categorias = categoriaService.obtenerTodos();
		modelo.addAttribute("categorias", categorias); 
		return "productos/formulario"; 
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar( @PathVariable Long id, 
			RedirectAttributes redireccion ) {
		
		productoService.eliminarPorId(id);
		redireccion.addFlashAttribute("mensaje", "Producto eliminado. ");
		return "redirect:/productos";
		
	}
	
	@PostMapping
	public String guardar(@Valid @ModelAttribute(name="producto_edicion") Producto producto, 
			BindingResult result, 
			Model modelo,
			RedirectAttributes redireccion) {
		
		if(productoService.comprobarProductioDuplicado(producto)) {
			result.rejectValue("nombre", "", "Nombre de producto ya existente");
		}
		
		if ( result.hasErrors() ) {
			// Si hay errores
			List<Categoria> categorias = categoriaService.obtenerTodos();
			modelo.addAttribute("categorias", categorias);
			return "productos/formulario";
		} else {
			// Si no hay errores
			if(producto.getId() == null) {
				redireccion.addFlashAttribute("mensaje", "Producto guardado. ");
			} else {
				redireccion.addFlashAttribute("mensaje", "Producto modificado. ");
			}
			productoService.guardar(producto);
			return "redirect:/productos";
		}
		
	}
	
}
