package com.gestion400.facturacion.modelo;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.*;

@View(members = "datosGenerales[numero,descripcion,precio];" + 
				"categoria;" + 
				"autor;" + 
				"obervaciones{observaciones},fotos{fotos}")

@View(name="DetalleFactura",  members = "numero,descripcion")

@Entity
public class Producto {
	
	@Id 
	@Column(length=9)
	private int numero;
	 
	@Column(length=50) @Required
	private String descripcion;
	
	@ManyToOne
	@DescriptionsList
	private Categoria categoria; // Una referencia Java convencional
	
	@ManyToOne
	@DescriptionsList
	private Autor autor;
	
	@Stereotype("DINERO") // La propiedad precio se usa para almacenar dinero
	private BigDecimal precio; // Incluye el import java.math.*  BigDecimal se suele usar para dinero
	 
	@Stereotype("GALERIA_IMAGENES") // Una galería de fotos completa está disponible
	@Column(length=32) // La cadena de 32 de longitud es para almacenar la clave de la galería
	private String fotos;
	 
	@Stereotype("TEXTO_GRANDE") // Esto es para un texto grande, se usará un área de texto o equivalente
	private String observaciones;
	

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
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

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getFotos() {
		return fotos;
	}

	public void setFotos(String fotos) {
		this.fotos = fotos;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
}
