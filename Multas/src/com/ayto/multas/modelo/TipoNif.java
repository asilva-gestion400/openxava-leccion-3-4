package com.ayto.multas.modelo;

import javax.persistence.*;

@Entity
public class TipoNif {
	
	@Id
	@Column(length = 10)
	private String tipo;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
