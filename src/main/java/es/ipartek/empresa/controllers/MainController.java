package es.ipartek.empresa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

	@GetMapping("/")
	public String mostrar_inicio() {
		return "inicio";
	}

	@GetMapping("/categorias")
	public String mostrar_categorias() {
		return "categorias";
	}

	@GetMapping("/productos")
	public String mostrar_productos() {
		return "productos";
	}
	
	
	
}
