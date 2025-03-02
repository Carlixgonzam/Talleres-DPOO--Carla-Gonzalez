package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public abstract class CalculadoraTarifas {

	public double IMPUESTO=0.28;
	
	public int calcularTarifa(Vuelo vuelo,Cliente cliente) {
		int base=calcularCostoBase(vuelo,cliente);
		double descuento=calcularPorcentajeDescuento(cliente);
		double precio=base*(1-descuento);
		double precioFinal=precio*(1+IMPUESTO);
		return (int)precioFinal;

}
	protected abstract int calcularCostoBase(Vuelo vuelo,Cliente cliente);
	
	protected abstract double calcularPorcentajeDescuento(Cliente cliente);
	
}