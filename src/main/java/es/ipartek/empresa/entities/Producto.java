package es.ipartek.empresa.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="productos")
public class Producto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "El nombre no puede ser nulo ni vacio")
	@Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
	@Column( nullable = false, unique = true, length = 50 )
	private String nombre; 
	
	@NotNull(message = "El precio no puede ser nulo")
	@DecimalMin(value = "0.01", message = "El precio minimo es de 0.01€")
	@Digits(integer = 5, fraction = 2, message = "El precio debe tener 5 digitos enteros y dos decimales como maximo")
	@Column( nullable = false, precision = 7, scale = 2)
	private BigDecimal precio; 
	
	@NotNull(message="El stock no puede ser nulo")
	@PositiveOrZero(message = "El stock debe ser un valor igual o mayor que 0")
	@Column( nullable = false )
	private Integer stock; 
	
	@Column( nullable = false)
	private Character origen = 'N'; 
	
	@Column( nullable = false)
	private Boolean comercializado = true; 
	
	@Column( length = 1024 )
	@Size(max=1024, message = "La descripcion no puede tener mas de 1024 caracteres")
	private String descripcion; 
	
	@ManyToOne()
	@JoinColumn(name="categoria_id")
	private Categoria categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Character getOrigen() {
		return origen;
	}

	public void setOrigen(Character origen) {
		this.origen = origen;
	}

	public Boolean getComercializado() {
		return comercializado;
	}

	public void setComercializado(Boolean comercializado) {
		this.comercializado = comercializado;
	}
}
