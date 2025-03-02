package uniandes.dpoo.aerolinea.modelo;

/**
 * Esta clase tiene la información de una ruta entre dos aeropuertos que cubre una aerolínea.
 */
public class Ruta
{
    
	private String horaSalida;
	
	public String getHoraSalida() {
		return horaSalida;
	}


	public String getHoraLlegada() {
		return this.horaLlegada;
	}


	public Aeropuerto getOrigen() {
		return origen;
	}


	public Aeropuerto getDestino() {
		return destino;
	}


	public String getCodigoRuta() {
		return codigoRuta;
	}


	public int getDuracion() {
		return this.duracion;
	}

	private String horaLlegada;
	private Aeropuerto origen;
	private Aeropuerto destino;
	private String codigoRuta;
	private int duracion;
	
	public Ruta(Aeropuerto origen, Aeropuerto destino, String horaSalida, String horaLlegada, String codigoRuta) {
        if (origen == null || destino == null) {
            throw new IllegalArgumentException("El aeropuerto de origen y destino no pueden ser null");
        }
       

        this.origen = origen;
        this.destino = destino;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.codigoRuta = codigoRuta;
        this.duracion = calcularDuracion(horaSalida, horaLlegada);
    }
	

    /**
     * Dada una cadena con una hora y minutos, retorna los minutos.
     * 
     * Por ejemplo, para la cadena '715' retorna 15.
     * @param horaCompleta Una cadena con una hora, donde los minutos siempre ocupan los dos últimos caracteres
     * @return Una cantidad de minutos entre 0 y 59
     */
    public static int getMinutos( String horaCompleta )
    {
        int minutos = Integer.parseInt( horaCompleta ) % 100;
        return minutos;
    }

    /**
     * Dada una cadena con una hora y minutos, retorna las horas.
     * 
     * Por ejemplo, para la cadena '715' retorna 7.
     * @param horaCompleta Una cadena con una hora, donde los minutos siempre ocupan los dos últimos caracteres
     * @return Una cantidad de horas entre 0 y 23
     */
    public static int getHoras( String horaCompleta )
    {
        int horas = Integer.parseInt( horaCompleta ) / 100;
        return horas;
    }


    private int calcularDuracion(String horaSalida, String horaLlegada) {
        int horasSalida = getHoras(horaSalida);
        int minutosSalida = getMinutos(horaSalida);
        int horasLlegada = getHoras(horaLlegada);
        int minutosLlegada = getMinutos(horaLlegada);

        int duracion = (horasLlegada - horasSalida) * 60 + (minutosLlegada - minutosSalida);
        return duracion < 0 ? duracion + 1440 : duracion; // 
    }


	public int getDistanciaEnKM() {
		// TODO Auto-generated method stub
		return 0;
	}

	
    
}
