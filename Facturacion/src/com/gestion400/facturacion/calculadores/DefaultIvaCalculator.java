package com.gestion400.facturacion.calculadores;

import java.math.*;

import org.openxava.calculators.*;

public class DefaultIvaCalculator implements ICalculator {
	
	private static final BigDecimal IVA = new BigDecimal("21");

	@Override
	public Object calculate() throws Exception {
		
		return IVA;
	}
}
