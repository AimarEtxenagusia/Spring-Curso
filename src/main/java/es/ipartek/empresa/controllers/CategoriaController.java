package es.ipartek.empresa.controllers;

import java.util.List;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.ipartek.empresa.entities.Categoria;
import es.ipartek.empresa.services.CategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired CategoriaService categoriaService;
	
	@GetMapping
	public String listar( Model modelo ) {
		List<Categoria> categorias = categoriaService.obtenerTodos();
		modelo.addAttribute("categorias", categorias);
		return "categorias/lista";
	}
}
