package uniandes.dpoo.aerolinea.modelo;

import java.util.Collection;
import java.util.HashMap;

import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;
import uniandes.dpoo.aerolinea.modelo.Avion;
import uniandes.dpoo.aerolinea.modelo.Ruta;

public class Vuelo {
	private String fecha;
	private Ruta ruta;
	private Avion avion;
	//mapa de tiquetes doende las llaves son el codigo del tiquete
	private HashMap<String,Tiquete> tiquetes;
	

	public Vuelo(Ruta ruta, String fecha, Avion avion) {
		//Mis metodos de vuelo
		this.ruta = ruta;
		this.fecha = fecha;
		this.avion = avion;
		this.tiquetes = new HashMap<String, Tiquete>();
		
	}
	public Ruta getRuta() {
		return ruta;
	}
	public String getFecha() {
		return fecha;
	}
	public Avion getAvion() {
		return avion;
	}
	public HashMap<String,Tiquete> getTiquetes(){
		return tiquetes;
	}
	
	public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad) {
		for(int i=0;i<cantidad;i++) {
			Tiquete tiquete=GeneradorTiquetes.generarTiquete(this, cliente, calculadora.calcularTarifa(this, cliente));
			cliente.agregarTiquete(tiquete);
			this.tiquetes.put(tiquete.getCodigo(), tiquete);
		}
	
	}
	public boolean equals(Object obj) {
		return false;
		
	}
	
	
	

}
