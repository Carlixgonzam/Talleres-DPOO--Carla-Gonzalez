package uniandes.dpoo.hamburguesas.tests;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Producto;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {
	
	
	private Combo combito;
	private ProductoMenu produ;
	private ProductoMenu papas;
	private ProductoMenu bebida;
	
	private ArrayList<ProductoMenu> item;
	
	@BeforeEach
	void SetUp() throws Exception{
		produ = new ProductoMenu("corral", 14000);
        papas = new ProductoMenu("papas medianas", 5500);
        bebida = new ProductoMenu("gaseosa", 5000);
        item = new ArrayList<>();
        item.add(produ);
        item.add(papas);
        item.add(bebida);
        combito = new Combo("combo corral", 0.10, item); 
	}
	@AfterEach
	
    void tearDown( ) throws Exception
    {
    }
	@Test
    void testGetNombre() {
        assertEquals("combo corral", combito.getNombre(), "El nombre del combo no es el esperado.");
    }

    @Test
    void testGetPrecio() {
        int precioEsperado = (int) ((14000 + 5500 + 5000) * 0.10);
        assertEquals(precioEsperado, combito.getPrecio(), "El precio del combo no es correcto.");
    }
    @Test
    void testGenerarTextoFactura() {
        String texto = combito.generarTextoFactura();
        assertTrue(texto.contains("combo corral"), "El texto de la factura no contiene el nombre del combo.");
        assertTrue(texto.contains("Descuento: 0.1"), "El texto de la factura no contiene el descuento correcto.");
        assertTrue(texto.contains(String.valueOf(combito.getPrecio())), "El texto de la factura no contiene el precio correcto.");
    }
    }
