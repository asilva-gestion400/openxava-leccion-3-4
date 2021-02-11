package com.gestion400.facturacion.modelo;

import java.math.*;

import javax.persistence.*;
import javax.persistence.Entity;

import org.hibernate.annotations.*;
import org.openxava.annotations.*;


@View(name = "Factura", members = "producto, cantidad, importe")

@Entity
public class DetalleFactura {
	
	@Id
    @GeneratedValue(generator="system-uuid")
    @Hidden
    @GenericGenerator(name="system-uuid", strategy="uuid")
    @Column(length=32)
    private String oid;
	
	@ManyToOne
	private Factura factura;
	
	@Required
	@Column(length = 9)
	private int cantidad;
	
	@ManyToOne
	@NoFrame @NoCreate @NoModify
	@ReferenceView("DetalleFactura")
	private Producto producto;
	
	@Stereotype("DINERO")
	public BigDecimal getImporte() {
		
		if (producto == null || producto.getPrecio() == null) return BigDecimal.ZERO;
		
		return new BigDecimal(cantidad).multiply(producto.getPrecio());
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
}
