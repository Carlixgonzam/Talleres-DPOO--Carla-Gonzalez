package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;


public class ProductoMenuTest {
	private ProductoMenu productoMenu;
	
	
	@BeforeEach
	void SetUp() throws Exception{
		productoMenu = new ProductoMenu("corral queso", 16000);
	}
	
	@AfterEach
    void tearDown( ) throws Exception
    {
    }
	
	@Test
	void testProductoMenu () {
		assertEquals( "corral queso", productoMenu.getNombre( ), "El nombre del producto no es el esperado." );	
		assertEquals( 16000, productoMenu.getPrecio( ), "El precio del producto no es el esperado." );
}
	@Test
	void testGenerarTextoFactura() {
		String factura = productoMenu.generarTextoFactura();
		assertTrue(factura.contains("corral queso"));
		assertTrue(factura.contains("16000"));
		
		
	}
}
