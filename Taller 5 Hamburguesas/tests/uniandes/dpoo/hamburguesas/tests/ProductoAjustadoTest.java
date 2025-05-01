package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest {
    private ProductoAjustado productoAjus;
    private ProductoMenu productoBase;
    private Ingrediente ingrediente1;
    private Ingrediente ingrediente2;
    
    @BeforeEach
    void setUp() throws Exception {
        productoBase = new ProductoMenu("corral queso", 16000);
        productoAjus = new ProductoAjustado(productoBase);
        ingrediente1 = new Ingrediente("tocineta picada", 6000);
        ingrediente2 = new Ingrediente("huevo", 2500);
    }
    
    @AfterEach
    void tearDown() throws Exception {
    }
    
    @Test
    void testProductoAjustado() {
        ArrayList<Ingrediente> eliminados = productoAjus.getEliminados();
        ArrayList<Ingrediente> agregados = productoAjus.getAgregados();
        assertTrue(eliminados.isEmpty(), "La lista de eliminados debe estar vacia inicialmente");
        assertTrue(agregados.isEmpty(), "La lista de agregados debe estar vacia inicialmente");
    }
    
    @Test
    void testGetNombre() {
        assertEquals("corral queso", productoAjus.getNombre(), "El nombre del producto no es el esperado");
    }
    
    @Test
    void testGetPrecio() {
        assertEquals(16000, productoAjus.getPrecio(), "El precio del producto debe ser igual al precio base");
        productoAjus.getAgregados().add(ingrediente1);
        assertEquals(16000, productoAjus.getPrecio(), "El precio debe seguir siendo igual al precio base aun con ingredientes adicionales");
        productoAjus.getAgregados().add(ingrediente2);
        assertEquals(16000, productoAjus.getPrecio(), "El precio debe seguir siendo igual al precio base con múltiples ingredientes");
    }
    
    @Test
    void testGetProductoBase() {
        assertEquals(productoBase, productoAjus.getProductoBase(), "El producto base no es el esperado");
    }
    
    @Test
    void testAgregarIngrediente() {
        assertEquals(0, productoAjus.getAgregados().size(), "La lista de agregados debe estar vacía inicialmente");
        productoAjus.getAgregados().add(ingrediente1);
        assertEquals(1, productoAjus.getAgregados().size(), "La lista de agregados debe tener un elemento");
        assertEquals(ingrediente1, productoAjus.getAgregados().get(0), "El ingrediente agregado debe ser el esperado");
    }
    
    @Test
    void testEliminarIngrediente() {
        assertEquals(0, productoAjus.getEliminados().size(), "La lista de eliminados debe estar vacía inicialmente");
        productoAjus.getEliminados().add(ingrediente2);
        assertEquals(1, productoAjus.getEliminados().size(), "La lista de eliminados debe tener un elemento");
        assertEquals(ingrediente2, productoAjus.getEliminados().get(0), "El ingrediente eliminado debe ser el esperado");
    }
    
    @Test
    void testGenerarTextoFactura() {
        String textoSinModificaciones = productoAjus.generarTextoFactura();
        
        Ingrediente ingrediente1 = new Ingrediente("tocineta picada", 6000);
        productoAjus.getAgregados().add(ingrediente1);
        
        String textoConAgregado = productoAjus.generarTextoFactura();
        assertNotEquals(textoSinModificaciones, textoConAgregado, 
                       "El texto de la factura debe cambiar al agregar un ingrediente");
        assertTrue(textoConAgregado.contains("+" + ingrediente1.getNombre()), 
                  "El texto debe incluir el nombre del ingrediente agregado");
        assertTrue(textoConAgregado.contains(String.valueOf(ingrediente1.getCostoAdicional())), 
                  "El texto debe incluir el costo del ingrediente agregado");
        Ingrediente ingrediente2 = new Ingrediente("huevo", 2500);
        productoAjus.getEliminados().add(ingrediente2);
        String textoConEliminado = productoAjus.generarTextoFactura();
        assertNotEquals(textoConAgregado, textoConEliminado, 
                       "El texto debe cambiar al eliminar un ingrediente");
        assertTrue(textoConEliminado.contains("-" + ingrediente2.getNombre()), 
                  "El texto debe incluir el nombre del ingrediente eliminado");
    }
    
    @Test
    void testMultiplesIngredientes() {
        productoAjus.getAgregados().add(ingrediente1);
        productoAjus.getAgregados().add(ingrediente2);
        productoAjus.getAgregados().add(ingrediente1);
        assertEquals(3, productoAjus.getAgregados().size(), "No se han agregado todos los ingredientes");
        assertEquals(ingrediente1, productoAjus.getAgregados().get(0), "El primer ingrediente no es el esperado");
        assertEquals(ingrediente2, productoAjus.getAgregados().get(1), "El segundo ingrediente no es el esperado");
        assertEquals(ingrediente1, productoAjus.getAgregados().get(2), "El tercer ingrediente no es el esperado");
        assertEquals(productoBase.getPrecio(), productoAjus.getPrecio(), "El precio debe ser el del producto base según la implementación actual");
    }
}