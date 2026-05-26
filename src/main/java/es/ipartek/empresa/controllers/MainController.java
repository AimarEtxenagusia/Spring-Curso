package es.ipartek.empresa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

	@GetMapping("/")
	public String mostrar_inicio() {
		return "inicio";
	}


	
	
	
}
