package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas{
	
	protected static int COSTO_POR_KM_NATURAL=600;
	protected static int COSTO_POR_KM_CORPORATIVO=900;
	protected static double DESCUENTO_PEQ=0.02;
	protected static double DESCUENTO_MEDIANAS=0.1;
	protected static double DESCUENTO_GRANDES=0.2;
	
	public int calcularCostoBase(Vuelo vuelo,Cliente cliente) {
		String tipoCliente=cliente.getTipoCliente();
		if (tipoCliente=="Natural") {
			return COSTO_POR_KM_NATURAL*vuelo.getRuta().getDistanciaEnKM();
		}
		else {
			return COSTO_POR_KM_CORPORATIVO*vuelo.getRuta().getDistanciaEnKM();
		}
	}
	public double calcularPorcentajeDescuento(Cliente cliente) {
		String tipoCliente=cliente.getTipoCliente();
		if (tipoCliente=="Natural") {
			return 0;
		}
		else {
			int tamanio=((ClienteCorporativo)cliente).getTamanoEmpresa();
			if (tamanio==ClienteCorporativo.PEQUENA) {
				return DESCUENTO_PEQ;
				
			}
			if (tamanio==ClienteCorporativo.MEDIANA) {
				return DESCUENTO_MEDIANAS;
			}
			else {
				return DESCUENTO_GRANDES;
			}
			
		}
	}

}
