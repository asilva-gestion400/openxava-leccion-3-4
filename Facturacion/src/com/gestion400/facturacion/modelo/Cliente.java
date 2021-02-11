package com.gestion400.facturacion.modelo;

import javax.persistence.*;

import org.openxava.annotations.*;


@View(members = "numero,nombre;direccion")
@View(name = "referencia", members = "numero,nombre")
@Entity
public class Cliente {
	
    @Id  // La propiedad numero es la clave.  Las claves son obligatorias (required) por defecto
    @Column(length=6)  // La longitud de columna se usa a nivel UI y a nivel DB
    private int numero;
 
    @Column(length=50) // La longitud de columna se usa a nivel UI y a nivel DB
    @Required  // Se mostrará un error de validación si la propiedad nombre se deja en blanco
    private String nombre;
    
    @NoFrame
    @Embedded // Así para referenciar a una clase incrustable
    private Direccion direccion; // Una referencia Java convencional
 
    public int getNumero() {
        return numero;
    }
 
    public void setNumero(int numero) {
        this.numero = numero;
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
}
