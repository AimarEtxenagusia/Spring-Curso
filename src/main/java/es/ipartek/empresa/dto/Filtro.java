package es.ipartek.empresa.dto;

public class Filtro {
	private String nombre; 		// Nombre de busqueda
	private String ordenado = ""; 	// Criterio de ordenacion
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getOrdenado() {
		return ordenado;
	}
	public void setOrdenado(String ordenado) {
		this.ordenado = ordenado;
	}
}
