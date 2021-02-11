package com.gestion400.facturacion.modelo;

import java.math.*;
import java.util.*;

import javax.persistence.*;
import javax.persistence.Entity;

import org.hibernate.annotations.*;
import org.openxava.annotations.*;
import org.openxava.calculators.*;

import com.gestion400.facturacion.calculadores.*;

@Tab(properties = "numero, fecha, estado, cliente.numero, cliente.nombre, importeTotal",
	 defaultOrder = "${numero} DESC")

@Tab(name = "facturasPendientes",
	 properties = "numero, fecha, estado, cliente.numero, cliente.nombre, importeTotal",
	 defaultOrder = "${numero} DESC",
	 baseCondition = "${estado} = 'PENDIENTE'")

@Tab(name = "facturasPagadas",
	 properties = "numero, fecha, estado, cliente.numero, cliente.nombre, importeTotal",
	 defaultOrder = "${numero} DESC",
	 baseCondition = "${estado} = 'PAGADA'")

@View(members = "anyo,numero,fecha;" + 
				"cliente;" + 
				"estado;" +
				"detallesFactura;" + 
				"observaciones")
@Entity
public class Factura {
	
	@Id
    @GeneratedValue(generator="system-uuid")
    @Hidden
    @GenericGenerator(name="system-uuid", strategy="uuid")
    @Column(length=32)
    private String oid;
 
    @Column(length=4)
    @DefaultValueCalculator(CurrentYearCalculator.class) // Año actual
    private int anyo;
 
    @Column(length=6)
    private int numero;
 
    @Required
    @DefaultValueCalculator(CurrentDateCalculator.class) // Fecha actual
    private Date fecha;
    
    @Required
    @DefaultValueCalculator(DefaultEstadoFacturaCalculator.class)
    @Enumerated(EnumType.STRING)
    private EstadoFactura estado;
 
    @Stereotype("MEMO")
    private String observaciones;
    
    @ManyToOne(fetch=FetchType.LAZY, optional=false) // El cliente es obligatorio
    @NoFrame @NoCreate @NoModify
    @ReferenceView("referencia")
    private Cliente cliente;
    
    @OneToMany(mappedBy="factura")
    @ListProperties("producto.numero, producto.descripcion, cantidad, importe+[factura.importe, factura.porcentajeIVA, factura.importeTotal]")
	@AddAction("")
    @CollectionView("Factura")
    private List<DetalleFactura> detallesFactura;
    
    @Required
    @Column(length = 3, scale = 2)
    @DefaultValueCalculator(DefaultIvaCalculator.class)
    private BigDecimal porcentajeIVA;

    @ReadOnly
    @Calculation("sum(detallesFactura.importe)/100")
    @Column(length = 9, scale = 2)
    private BigDecimal importe;  
    
    public BigDecimal getImporteTotal() {
    	
    	if(importe == null || porcentajeIVA == null) return BigDecimal.ZERO;
    	
    	return importe.add(importe.multiply(porcentajeIVA).divide(new BigDecimal("100.00")));
    }

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

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<DetalleFactura> getDetallesFactura() {
		return detallesFactura;
	}

	public void setDetallesFactura(List<DetalleFactura> detallesFactura) {
		this.detallesFactura = detallesFactura;
	}

	public EstadoFactura getEstado() {
		return estado;
	}

	public void setEstado(EstadoFactura estado) {
		this.estado = estado;
	}

	public BigDecimal getPorcentajeIVA() {
		return porcentajeIVA;
	}

	public void setPorcentajeIVA(BigDecimal porcentajeIVA) {
		this.porcentajeIVA = porcentajeIVA;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
}
