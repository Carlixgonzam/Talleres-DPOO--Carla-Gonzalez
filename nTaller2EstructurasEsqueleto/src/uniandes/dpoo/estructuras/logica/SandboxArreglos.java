package uniandes.dpoo.estructuras.logica;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Esta clase tiene un conjunto de métodos para practicar operaciones sobre arreglos de enteros y de cadenas.
 *
 * Todos los métodos deben operar sobre los atributos arregloEnteros y arregloCadenas.
 * 
 * No pueden agregarse nuevos atributos.
 * 
 * Implemente los métodos usando operaciones sobre arreglos (ie., no haga cosas como construir listas para evitar la manipulación de arreglos).
 */
public class SandboxArreglos
{
    /**
     * Un arreglo de enteros para realizar varias de las siguientes operaciones.
     * 
     * Ninguna posición del arreglo puede estar vacía en ningún momento.
     */
    private int[] arregloEnteros;

    /**
     * Un arreglo de cadenas para realizar varias de las siguientes operaciones
     * 
     * Ninguna posición del arreglo puede estar vacía en ningún momento.
     */
    private String[] arregloCadenas;

    /**
     * Crea una nueva instancia de la clase con los dos arreglos inicializados pero vacíos (tamaño 0)
     */
    public SandboxArreglos( )
    {
        arregloEnteros = new int[]{};
        arregloCadenas = new String[]{};
    }

    /**
     * Retorna una copia del arreglo de enteros, es decir un nuevo arreglo del mismo tamaño que contiene copias de los valores del arreglo original
     * @return Una copia del arreglo de enteros
     */
    public int[] getCopiaEnteros( )
    {
        return arregloEnteros.clone();
    }

    /**
     * Retorna una copia del arreglo de cadenas, es decir un nuevo arreglo del mismo tamaño que contiene copias de los valores del arreglo original
     * @return Una copia del arreglo de cadenas
     */
    public String[] getCopiaCadenas( )
    {
        return arregloCadenas.clone();
    }

    /**
     * Retorna la cantidad de valores en el arreglo de enteros
     * @return
     */
    public int getCantidadEnteros( )
    {
        return arregloEnteros.length;
    }

    /**
     * Retorna la cantidad de valores en el arreglo de cadenas
     * @return
     */
    public int getCantidadCadenas( )
    {
        return arregloCadenas.length;
    }

    /**
     * Agrega un nuevo valor al final del arreglo. Es decir que este método siempre debería aumentar en 1 la capacidad del arreglo.
     * 
     * @param entero El valor que se va a agregar.
     */
    
    public void agregarEntero( int entero )
    {
    	int[] nuevalista = Arrays.copyOf(arregloEnteros, arregloEnteros.length + 1); //Creo una copia de la original
    	nuevalista[nuevalista.length - 1] = entero; //asigno el valor al final
    	arregloEnteros = nuevalista; //devualvo la copia
    			
    }

    /**
     * Agrega un nuevo valor al final del arreglo. Es decir que este método siempre debería aumentar en 1 la capacidad del arreglo.
     * 
     * @param cadena La cadena que se va a agregar.
     */
    public void agregarCadena( String cadena )
    {
    	String[] nuevaArreglito = Arrays.copyOf(arregloCadenas, arregloCadenas.length + 1); //Copio la copia de la lista de cadenas
    	nuevaArreglito[nuevaArreglito.length - 1] = cadena;
    	arregloCadenas = nuevaArreglito;
    	
    }

    /**
     * Elimina todas las apariciones de un determinado valor dentro del arreglo de enteros
     * @param valor El valor que se va eliminar
     */
    public void eliminarEntero( int valor )
    {
    	int contador = 0;
    	
    	for (int i= 0; i < arregloEnteros.length; i++) { //Hago el recorrido
    		int bloque=arregloEnteros[i]; //guardo en una variable ese indice
    		if (bloque == valor) {
    			contador ++;	//cuenta los valores que coinciden con valor
    		}

    	}
    	int [] nuevalista = new int[arregloEnteros.length-contador]; //creo una nueva lista 
    	int posicion = 0;
    	for(int i = 0; posicion<(arregloEnteros.length-contador); i++) { //cuenta la long - el contador 
    		if (valor!=arregloEnteros[i]) {
    			nuevalista[posicion] = arregloEnteros[i]; //si es diferente lo puede agregar a la nueva lista
    			posicion++;
    		}
    	}
    	arregloEnteros = nuevalista;
    	
    }

    /**
     * Elimina todas las apariciones de un determinado valor dentro del arreglo de cadenas
     * @param cadena La cadena que se va eliminar
     */
    public void eliminarCadena( String cadena )
    {
    	int cont = 0;
        for (String str : arregloCadenas) { //pongo el ciclo de strings 
            if (str.equals(cadena)) { //comparo si la cadena es igual 
            	cont++;
            }
        }

        String[] nuevoArr = new String[arregloCadenas.length - cont]; //creo un nuevo arreglo 
        int i = 0;
        for (String str : arregloCadenas) {
            if (!str.equals(cadena)) {
                nuevoArr[i++] = str;
            }
        }
        arregloCadenas = nuevoArr;
    }

    /**
     * Inserta un nuevo entero en el arreglo de enteros.
     * 
     * @param entero El nuevo valor que debe agregarse
     * @param posicion La posición donde debe quedar el nuevo valor en el arreglo aumentado. Si la posición es menor a 0, se inserta el valor en la primera posición. Si la
     *        posición es mayor que el tamaño del arreglo, se inserta el valor en la última posición.
     */
    public void insertarEntero( int entero, int posicion )
    {
    	if (posicion < 0) {
    		posicion = 0;
    	}
    	if(posicion > arregloEnteros.length) { 
    		posicion = arregloEnteros.length;
    	}
    	int[] nue = new int[arregloEnteros.length +1]; //nuevo arreglo
    	for(int i=0, j=0; i < nue.length; i++) { 
    		if(i==posicion) {
    			nue[i] = entero;
    		}else {
    			nue[i] = arregloEnteros[j++];
    		}
    	}
    	
    	arregloEnteros = nue;
    }

    /**
     * Elimina un valor del arreglo de enteros dada su posición.
     * @param posicion La posición donde está el elemento que debe ser eliminado. Si el parámetro posicion no corresponde a ninguna posición del arreglo de enteros, el método
     *        no debe hacer nada.
     */
    public void eliminarEnteroPorPosicion( int posicion )
    {
    	if (posicion < 0 || posicion >= arregloEnteros.length) {
    		return;
    	}
        int[] nuevoArreglo = new int[arregloEnteros.length - 1];
        for (int i = 0, j = 0; i < arregloEnteros.length; i++) { //recorrido del arreglo
            if (i != posicion) {
                nuevoArreglo[j++] = arregloEnteros[i];
            }
        }
        arregloEnteros = nuevoArreglo;
    }

    /**
     * Reinicia el arreglo de enteros con los valores contenidos en el arreglo del parámetro 'valores' truncados.
     * 
     * Es decir que si el valor fuera 3.67, en el nuevo arreglo de enteros debería quedar el entero 3.
     * @param valores Un arreglo de valores decimales.
     */
    public void reiniciarArregloEnteros( double[] valores )
    {
    	arregloEnteros = new int[valores.length]; //meto los valores en el arreglo de enteros
    	for(int i=0; i < arregloEnteros.length; i++) {
    		arregloEnteros[i] = (int) valores[i]; //se pasa a entero el valor
    	}
    }

    /**
     * Reinicia el arreglo de cadenas con las representaciones como Strings de los objetos contenidos en el arreglo del parámetro 'objetos'.
     * 
     * Use el método toString para convertir los objetos a cadenas.
     * @param valores Un arreglo de objetos
     */
    public void reiniciarArregloCadenas( Object[] objetos )
    {
    	arregloCadenas = new String[objetos.length]; //Creo un nuevo string de cadena para hacer el reinicio
    	for(int i=0; i < arregloCadenas.length; i++) {
    		arregloCadenas[i] = objetos[i].toString(); //paso esos objetos a string
    	}
    }

    /**
     * Modifica el arreglo de enteros para que todos los valores sean positivos.
     * 
     * Es decir que si en una posición había un valor negativo, después de ejecutar el método debe quedar el mismo valor muliplicado por -1.
     */
    public void volverPositivos( )
    {
    	for(int i=0; i< arregloEnteros.length; i++) {
    		if(arregloEnteros[i] < 0) {
    			arregloEnteros[i] *= -1;
    		}
    	}
    }

    /**
     * Modifica el arreglo de enteros para que todos los valores queden organizados de menor a mayor.
     */
    public void organizarEnteros( )
    {
    	Arrays.sort(arregloEnteros);
    }

    /**
     * Modifica el arreglo de cadenas para que todos los valores queden organizados lexicográficamente.
     */
    public void organizarCadenas( )
    {
    	Arrays.sort(arregloCadenas);
    }

    /**
     * Cuenta cuántas veces aparece el valor recibido por parámetro en el arreglo de enteros
     * @param valor El valor buscado
     * @return La cantidad de veces que aparece el valor
     */
    public int contarApariciones( int valor )
    {
    	int co = 0;
        for(int i = 0; i< arregloEnteros.length; i++) {
        	if(arregloEnteros[i] == valor) {
        		co++;
        	}
        }
        return co;
    }

    /**
     * Cuenta cuántas veces aparece la cadena recibida por parámetro en el arreglo de cadenas.
     * 
     * La búsqueda no debe diferenciar entre mayúsculas y minúsculas.
     * @param cadena La cadena buscada
     * @return La cantidad de veces que aparece la cadena
     */
    public int contarApariciones( String cadena )
    {
    	int cun=0;
    	for(String str: arregloCadenas) {
    		if(str.equalsIgnoreCase(cadena)) {
    			cun++;
    		}
    	}
    	return cun;
    }

    /**
     * Busca en qué posiciones del arreglo de enteros se encuentra el valor que se recibe en el parámetro
     * @param valor El valor que se debe buscar
     * @return Un arreglo con los números de las posiciones del arreglo de enteros en las que se encuentra el valor buscado. Si el valor no se encuentra, el arreglo retornado
     *         es de tamaño 0.
     */
    public int[] buscarEntero( int valor )
    {
        int c = contarApariciones(valor);
        int[] pos = new int[c];
        int in = 0;
        for(int i = 0; i<arregloEnteros.length; i++) {
        	if(arregloEnteros[i] == valor) {
        		pos[ in++] = i;
        	}
        }
        return pos;
    }

    /**
     * Calcula cuál es el rango de los enteros (el valor mínimo y el máximo).
     * @return Un arreglo con dos posiciones: en la primera posición, debe estar el valor mínimo en el arreglo de enteros; en la segunda posición, debe estar el valor máximo
     *         en el arreglo de enteros. Si el arreglo está vacío, debe retornar un arreglo vacío.
     */
    public int[] calcularRangoEnteros( )
    {
    	if (arregloEnteros.length == 0) {
    		return new int[]{};
    	}

        int min = arregloEnteros[0], max = arregloEnteros[0];

        for (int num : arregloEnteros) {
            if (num < min) min = num;
            if (num > max) max = num;
        }

        return new int[]{min, max};
    }

    /**
     * Calcula un histograma de los valores del arreglo de enteros y lo devuelve como un mapa donde las llaves son los valores del arreglo y los valores son la cantidad de
     * veces que aparece cada uno en el arreglo de enteros.
     * @return Un mapa con el histograma de valores.
     */
    public HashMap<Integer, Integer> calcularHistograma( )
    {
    	HashMap<Integer, Integer> histograma = new HashMap<>();
    	for(int num: arregloEnteros) {
    		
			histograma.put(num, histograma.getOrDefault(num, 0) + 1); //retorno el valor de la llave
    	}
    	return histograma;
    }

    /**
     * Cuenta cuántos valores dentro del arreglo de enteros están repetidos.
     * @return La cantidad de enteos diferentes que aparecen más de una vez
     */
    public int contarEnterosRepetidos( )
    {
    	HashMap<Integer, Integer> f = calcularHistograma();
    	int cuncun = 0;
        for(int valor: f.values() ) {
        	if(valor > 1) {
        		cuncun++;
        	}
        	
        }
        return cuncun;
    }

    /**
     * Compara el arreglo de enteros con otro arreglo de enteros y verifica si son iguales, es decir que contienen los mismos elementos exactamente en el mismo orden.
     * @param otroArreglo El arreglo de enteros con el que se debe comparar
     * @return True si los arreglos son idénticos y false de lo contrario
     */
    public boolean compararArregloEnteros( int[] otroArreglo )
    {
        return Arrays.equals(arregloEnteros, otroArreglo);
    }

    /**
     * Compara el arreglo de enteros con otro arreglo de enteros y verifica que tengan los mismos elementos, aunque podría ser en otro orden.
     * @param otroArreglo El arreglo de enteros con el que se debe comparar
     * @return True si los elementos en los dos arreglos son los mismos
     */
    public boolean mismosEnteros( int[] otroArreglo )
    {
        if (arregloEnteros.length != otroArreglo.length ) {
        	return false;
        }
        int[] una = Arrays.copyOf(arregloEnteros, arregloEnteros.length);
        int[] dos = Arrays.copyOf(otroArreglo, otroArreglo.length);
        
        Arrays.sort(una);
        Arrays.sort(dos);
        
        return Arrays.equals(una, dos);
    }

    /**
     * Cambia los elementos del arreglo de enteros por una nueva serie de valores generada de forma aleatoria.
     * 
     * Para generar los valores se debe partir de una distribución uniforme usando Math.random().
     * 
     * Los números en el arreglo deben quedar entre el valor mínimo y el máximo.
     * @param cantidad La cantidad de elementos que debe haber en el arreglo
     * @param minimo El valor mínimo para los números generados
     * @param maximo El valor máximo para los números generados
     */
    public void generarEnteros( int cantidad, int minimo, int maximo )
    {
    	if (cantidad <= 0 || minimo > maximo) {
    		return;
    	}

        arregloEnteros = new int[cantidad];
        for (int i = 0; i < cantidad; i++) {
            arregloEnteros[i] = (int) (Math.random() * (maximo - minimo + 1)) + minimo;
        }
    }

}
