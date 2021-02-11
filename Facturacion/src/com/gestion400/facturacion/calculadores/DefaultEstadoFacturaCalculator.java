package com.gestion400.facturacion.calculadores;

import org.openxava.calculators.*;

import com.gestion400.facturacion.modelo.*;

public class DefaultEstadoFacturaCalculator implements ICalculator{

	private static final long serialVersionUID = 1L;

	@Override
	public Object calculate() throws Exception {
		
		return EstadoFactura.PENDIENTE;
	}
}
