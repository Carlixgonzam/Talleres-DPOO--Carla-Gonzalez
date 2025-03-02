package uniandes.dpoo.aerolinea.persistencia;

import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Avion;

public interface IPersistenciaAerolinea {
	
	public void cargarAerolinea(String archivo,Aerolinea aerolinea);
	
	public void salvarAerolinea(String archivo,Aerolinea aerolinea);
	

}
