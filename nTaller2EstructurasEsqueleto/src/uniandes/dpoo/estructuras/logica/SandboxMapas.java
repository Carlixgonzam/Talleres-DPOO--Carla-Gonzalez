package uniandes.dpoo.estructuras.logica;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Esta clase tiene un conjunto de métodos para practicar operaciones sobre mapas.
 *
 * Todos los métodos deben operar sobre el atributo mapaCadenas que se declara como un Map.
 * 
 * En este mapa, las llaves serán cadenas y los valores serán también cadenas. La relación entre los dos será que cada llave será igual a la cadena del valor, pero invertida.
 * 
 * El objetivo de usar el tipo Map es que sólo puedan usarse métodos de esa interfaz y no métodos adicionales provistos por la implementación concreta (HashMap).
 * 
 * No pueden agregarse nuevos atributos.
 */
public class SandboxMapas
{
    /**
     * Un mapa de cadenas para realizar varias de las siguientes operaciones.
     * 
     * Las llaves del mapa son cadenas, así como los valores.
     * 
     * Las llaves corresponden a invertir la cadena que aparece asociada a cada llave.
     */
    private Map<String, String> mapaCadenas;

    /**
     * Crea una nueva instancia de la clase con las dos listas inicializadas pero vacías
     */
    public SandboxMapas( )
    {
        mapaCadenas = new HashMap<String, String>( );
    }

    /**
     * Retorna una lista con las cadenas del mapa (los valores) ordenadas lexicográficamente
     * @return Una lista ordenada con las cadenas que conforman los valores del mapa
     */
    public List<String> getValoresComoLista( )
    {
        List<String> val = new ArrayList<>(mapaCadenas.values());
        Collections.sort(val);
        return val;
    }

    /**
     * Retorna una lista con las llaves del mapa ordenadas lexicográficamente de mayor a menor
     * @return Una lista ordenada con las cadenas que conforman las llaves del mapa
     */
    public List<String> getLlavesComoListaInvertida( )
    {
        List<String> llaves = new ArrayList<>(mapaCadenas.keySet());
        Collections.sort(llaves, Collections.reverseOrder()); //ordende mayor a menor
        return llaves;
    }

    /**
     * Retorna la cadena que sea lexicográficamente menor dentro de las llaves del mapa .
     * 
     * Si el mapa está vacío, debe retornar null.
     * @return
     */
    public String getPrimera( )
    {
    	if (mapaCadenas.isEmpty()) {
    		return null;
    	}

        String primera = null;
        for (String llave : mapaCadenas.keySet()) {
            if (primera == null || llave.compareTo(primera) < 0) {
                primera = llave; //guardo la menor lexicográficamente
            }
        }
        return primera;
    }

    /**
     * Retorna la cadena que sea lexicográficamente mayor dentro de los valores del mapa
     * 
     * Si el conjunto está vacío, debe retornar null.
     * @return
     */
    public String getUltima( )
    {
    	if (mapaCadenas.isEmpty()) return null;

        String ultima = null;
        for (String valor : mapaCadenas.values()) {
            if (ultima == null || valor.compareTo(ultima) > 0) {
                ultima = valor;
            }
        }
        return ultima;
    }

    /**
     * Retorna una colección con las llaves del mapa, convertidas a mayúsculas.
     * 
     * El orden de las llaves retornadas no importa.
     * @return Una lista de cadenas donde todas las cadenas están en mayúsculas
     */
    public Collection<String> getLlaves( )
    {
    	Set<String> llavesMayu = new HashSet<>();
        
        for (String llave : mapaCadenas.keySet()) {
            llavesMayu.add(llave.toUpperCase());
        }
        
        return llavesMayu;
    }

    /**
     * Retorna la cantidad de *valores* diferentes en el mapa
     * @return
     */
    public int getCantidadCadenasDiferentes( )
    {
    	return new HashSet<>(mapaCadenas.values()).size(); //uso un conjunto para eliminar duplicados
    }

    /**
     * Agrega un nuevo valor al mapa de cadenas: el valor será el recibido por parámetro, y la llave será la cadena invertida
     * 
     * Este método podría o no aumentar el tamaño del mapa, dependiendo de si ya existía la cadena en el mapa
     * 
     * @param cadena La cadena que se va a agregar al mapa
     */
    public void agregarCadena( String cadena )
    {
    	String clav = new StringBuilder(cadena).reverse().toString();
    	mapaCadenas.put(clav, cadena);
    }

    /**
     * Elimina una cadena del mapa, dada la llave
     * @param cadena La llave para identificar el valor que se debe eliminar
     */
    public void eliminarCadenaConLLave( String llave )
    {
    	mapaCadenas.remove(llave);
    }

    /**
     * Elimina una cadena del mapa, dado el valor
     * @param cadena El valor que se debe eliminar
     */
    public void eliminarCadenaConValor( String valor )
    {
    	Iterator<Map.Entry<String, String>> iterador = mapaCadenas.entrySet().iterator();

        while (iterador.hasNext()) {
            if (iterador.next().getValue().equals(valor)) {
                iterador.remove(); //elimino la entrada actual de forma segura
            }
        }
    }

    /**
     * Reinicia el mapa de cadenas con las representaciones como Strings de los objetos contenidos en la lista del parámetro 'objetos'.
     * 
     * Use el método toString para convertir los objetos a cadenas.
     * @param valores Una lista de objetos
     */
    public void reiniciarMapaCadenas( List<Object> objetos )
    {
    	Map<String, String> nuevoMapa = new HashMap<>();

        for (Object obj : objetos) {
            String valor = obj.toString();
            String claveInve = new StringBuilder(valor).reverse().toString(); //invierto la cadena
            nuevoMapa.put(claveInve, valor);
        }

        mapaCadenas = nuevoMapa;

    }

    /**
     * Modifica el mapa de cadenas reemplazando las llaves para que ahora todas estén en mayúsculas pero sigan conservando las mismas cadenas asociadas.
     */
    public void volverMayusculas( )
    {
    	Map<String, String> nuevoMapa = new HashMap<>();

        for (Map.Entry<String, String> entrada : mapaCadenas.entrySet()) {
            nuevoMapa.put(entrada.getKey().toUpperCase(), entrada.getValue()); //guardo la llave en mayúsculas
        }

        mapaCadenas = nuevoMapa;

    }

    /**
     * Verifica si todos los elementos en el arreglo de cadenas del parámetro hacen parte del mapa de cadenas (de los valores)
     * @param otroArreglo El arreglo de enteros con el que se debe comparar
     * @return True si todos los elementos del arreglo están dentro de los valores del mapa
     */
    public boolean compararValores( String[] otroArreglo )
    {
    	for (String valor : otroArreglo) {
            if (!mapaCadenas.containsValue(valor)) {
                return false;
            }
        }
        return true;
    }

}
