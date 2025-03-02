package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;

import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class ClienteNatural extends Cliente{
	public static String NATURAL = "Natural";
	private String nombre;
	
	public ClienteNatural(String nombre) {
		this.nombre=nombre;
		this.tipoCliente=NATURAL;
		this.identificador=nombre;
		this.tiquetesSinUsar= new ArrayList<Tiquete>();
		this.tiquetesSinUsar=new ArrayList<Tiquete>();
	}
	public String getIdentificador() {
		return getIdentificador();
	}
	public String getTipoCliente() {
		return NATURAL;
		
	}
	//Es correcto dejar un getter y setter para nombre?
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
