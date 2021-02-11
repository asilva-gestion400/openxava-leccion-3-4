package _run;

import org.openxava.util.*;

/**
 * Ejecuta esta clase para arrancar la aplicación.
 *
 * Con Eclipse: Botón derecho del ratón > Run As > Java Application
 */

public class _Run_Facturacion {

	public static void main(String[] args) throws Exception {
		DBServer.start("FacturacionDB"); // Para usar tu propia base de datos comenta esta línea y configura web/META-INF/context.xml
		AppServer.run("Facturacion"); // Usa AppServer.run("") para funcionar en el contexto raíz
	}

}
