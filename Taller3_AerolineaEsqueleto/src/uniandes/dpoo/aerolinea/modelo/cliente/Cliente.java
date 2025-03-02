package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;


public abstract class Cliente{
	
	protected String tipoCliente; 
	protected String identificador;
	protected ArrayList<Tiquete> tiquetesSinUsar;
	protected ArrayList<Tiquete> tiquetesUsados; //tomo el arraylist de tiquete
	protected int valorTotalTiquetes;
	protected Vuelo usarTiquetes;

	
	public Cliente() {
		
		// TODO Auto-generated constructor stub
		
		
		
	}
	public String getIdentficador() {
		return this.identificador;
		
	}
	public String getTipoCliente(){
		return this.tipoCliente;
		
	}
	
	public ArrayList<Tiquete>getTiquetesUsados(){
		return this.tiquetesUsados;
		
	}
	public ArrayList<Tiquete>getTiquetesSinUsar(){
		return this.tiquetesSinUsar;
		
	}
	
	public void agregarTiquete(Tiquete tiquete) {
		this.tiquetesSinUsar.add(tiquete);
	}
	public void usarTiquetes(Vuelo vuelo) {
		ArrayList<Tiquete>lista=this.tiquetesSinUsar;
		for (int i =0;i<lista.size();i++) {
			Tiquete ticket=lista.get(i);
			if (ticket.getVuelo()==vuelo) {
				ticket.marcarComoUsado();
				lista.remove(ticket);
				this.tiquetesUsados.add(ticket);
			}
		}
		
	}
	/**
	 * Estoy asumiendo que toca calcular el valor de los tiquetes que no se han usado 
	 * por q para que quieres lo ya usados?
	 * @return la suma de todos los valores de tiquetes
	 */
	public int calcularValorTotalTiquetes() {
		int cuenta=0;
		for (int i =0;i<this.tiquetesSinUsar.size();i++) {
			cuenta=cuenta+tiquetesSinUsar.get(i).getTarifa();
		}
		return cuenta;
	}
	
}
