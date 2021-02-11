package com.ayto.multas.modelo;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;


@View(name = "desdeMulta", members = "nif, nombre")

@Entity
public class Infractor {
	
	@Id
	@Column(length = 9)
	private String nif;
	
	@ManyToOne
	@DescriptionsList(descriptionProperties = "tipo")
	private TipoNif tipoNif;
	
	@Column(length = 40)
	private String nombre;
	
	@Column(length = 20)
	private String telefono;
	
	@Embedded
	private Direccion direccion;
	
	@Column
	private Date fechaCaducidadPermisoConducir;
	
	@ReadOnly
	@OneToMany(mappedBy = "infractor")
	@ListProperties(value = "anyo, fecha, importe, agente.codigo, agente.nombre")
	private Collection<Multa> multas;

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public TipoNif getTipoNif() {
		return tipoNif;
	}

	public void setTipoNif(TipoNif tipoNif) {
		this.tipoNif = tipoNif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFechaCaducidadPermisoConducir() {
		return fechaCaducidadPermisoConducir;
	}

	public void setFechaCaducidadPermisoConducir(Date fechaCaducidadPermisoConducir) {
		this.fechaCaducidadPermisoConducir = fechaCaducidadPermisoConducir;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Collection<Multa> getMultas() {
		return multas;
	}

	public void setMultas(Collection<Multa> multas) {
		this.multas = multas;
	}
}
