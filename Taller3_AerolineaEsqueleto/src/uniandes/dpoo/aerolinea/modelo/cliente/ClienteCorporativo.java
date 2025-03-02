package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;

import org.json.JSONObject;

import uniandes.dpoo.aerolinea.tiquetes.Tiquete;
/**
 * Esta clase se usa para representar a los clientes de la aerolínea que son empresas
 */
public class ClienteCorporativo extends Cliente {
    // TODO completar
    


    private String nombreEmpresa;
    private int tamanoEmpresa;
    public String  CORPORATIVO= "Corporativo";
    public int GRANDE = 1;
    public static int PEQUENA = 3;
    public static int MEDIANA = 2;
    

	public ClienteCorporativo(String nombreEmpresa, int tamanoEmpresa) {
		// TODO Auto-generated constructor stub
		this.nombreEmpresa=nombreEmpresa;
		this.tamanoEmpresa=tamanoEmpresa;
		this.tipoCliente=CORPORATIVO;
		this.identificador=nombreEmpresa;
		this.tiquetesSinUsar= new ArrayList<Tiquete>();
		this.tiquetesSinUsar=new ArrayList<Tiquete>();
		
	}

	/**
     * Crea un nuevo objeto de tipo a partir de un objeto JSON.
     * 
     * El objeto JSON debe tener dos atributos: nombreEmpresa (una cadena) y tamanoEmpresa (un número).
     * @param cliente El objeto JSON que contiene la información
     * @return El nuevo objeto inicializado con la información
     */
    public static ClienteCorporativo cargarDesdeJSON( JSONObject cliente )
    {
        String nombreEmpresa = cliente.getString( "nombreEmpresa" );
        int tamanoEmpresa = cliente.getInt( "tamanoEmpresa" );
        return new ClienteCorporativo( nombreEmpresa, tamanoEmpresa );
    }

    /**
     * Salva este objeto de tipo ClienteCorporativo dentro de un objeto JSONObject para que ese objeto se almacene en un archivo
     * @return El objeto JSON con toda la información del cliente corporativo
     */
    public JSONObject salvarEnJSON( )
    {
        JSONObject jobject = new JSONObject( );
        jobject.put( "nombreEmpresa", this.nombreEmpresa );
        jobject.put( "tamanoEmpresa", this.tamanoEmpresa );
        jobject.put( "tipo", CORPORATIVO );
        return jobject;
    }
    public String getNombreEmpresa() {
    	return this.nombreEmpresa;
    	
    }
    public int getTamanoEmpresa() {
    	return this.tamanoEmpresa;
    }
    public String getTipoCliente() {
    	return CORPORATIVO;
    }
    public String getIdentificador() {
    	return getIdentificador();
    }
    
}
