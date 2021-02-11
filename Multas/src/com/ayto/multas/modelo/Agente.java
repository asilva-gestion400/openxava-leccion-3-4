package com.ayto.multas.modelo;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

@Tab(properties = "codigo,nombre,direccion.viaPublica,direccion.codigoPostal", 
	 defaultOrder =  "${codigo} DESC")

@Views({
	@View(members = "datosGenerales[codigo, nombre]; direccion{direccion},multas{multas}"),
	@View(name = "desdeMulta", members = "codigo, nombre")
})

@Entity
public class Agente {
	
	@Id
	@Column(length = 9)
	private int codigo;
	    
	@Column(length = 40)
	private String nombre;
	
	@Embedded @NoFrame
	private Direccion direccion;
	
	@ReadOnly
	@OneToMany(mappedBy = "agente")
	@ListProperties(value = "anyo, fecha, importe, " +
			 				"infractor.nif, infractor.nombre")
	private Collection<Multa> multas;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
