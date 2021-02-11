package com.ayto.multas.modelo;

import java.math.*;
import java.util.*;

import javax.persistence.*;
import javax.persistence.Entity;

import org.hibernate.annotations.*;
import org.openxava.annotations.*;
import org.openxava.calculators.*;

@View(members = "anyo,fecha,importe;agente;infractor;observaciones")

@Tab(properties = "anyo,fecha,importe, agente.codigo, agente.nombre, infractor.nif, infractor.nombre",
	 defaultOrder = "${fecha} DESC")

@Entity
public class Multa {
	
	@Id
    @Hidden // La propiedad no se muestra al usuario. Es un identificador interno
    @GeneratedValue(generator="system-uuid") // Identificador Universal Único (1)
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(length=32)
    private String oid;
	
	@DefaultValueCalculator(value = CurrentYearCalculator.class)
	@Column(length = 4)
	private int anyo;
	
	@DefaultValueCalculator(value = CurrentDateCalculator.class)
	@Column
	private Date fecha;
	
	@Stereotype("DINERO")
	@Column(length = 12, scale = 2)
	private BigDecimal importe;
	
	@Stereotype("TEXTO_GRANDE")
	@Column(length = 100)
	private String observaciones;
	
	@ManyToOne @NoFrame
	@ReferenceView(value = "desdeMulta")
	private Agente agente;
	
	@ManyToOne @NoFrame
	@ReferenceView(value = "desdeMulta")
	private Infractor infractor;
	
	@Enumerated(EnumType.STRING)
	private EstadoMulta estado;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public int getAnyo() {
		return anyo;
	}

	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Agente getAgente() {
		return agente;
	}

	public void setAgente(Agente agente) {
		this.agente = agente;
	}

	public Infractor getInfractor() {
		return infractor;
	}

	public void setInfractor(Infractor infractor) {
		this.infractor = infractor;
	}

	public EstadoMulta getEstado() {
		return estado;
	}

	public void setEstado(EstadoMulta estado) {
		this.estado = estado;
	}
}
