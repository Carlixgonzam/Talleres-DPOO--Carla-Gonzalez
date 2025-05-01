package uniandes.dpoo.hamburguesas.tests;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Producto;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest {
	private Pedido pedido;
	private ProductoMenu productoMenu;
	private ProductoAjustado productoAjus;
	
	@BeforeEach
	void SetUp() throws Exception{
		
		productoMenu  = new ProductoMenu("corral queso", 16000);
		productoAjus  =new ProductoAjustado(productoMenu);
		
		pedido = new Pedido("Carla", "CityU Torre 1");
	}
	@AfterEach
	
    void tearDown( ) throws Exception
    {
    }
	@Test
	void testPedido() {
		assertEquals("Carla", pedido.getNombreCliente( ),"El nombre del cliente no es valido.");
		assertEquals("CityU Torre 1", pedido.getDireccionCliente(),"La direccion no es valida.");

	}
	@Test
	void testGetIdPedido() {
	    int idPedido = pedido.getIdPedido();
	    assertTrue(idPedido >= 0, "El id del pedido debe ser un número no negativo.");
	}
	@Test
	void testAgregarProducto() {
		pedido.agregarProducto(productoAjus);
		ArrayList<Producto> productos = pedido.getProductos();
		assertEquals(1, productos.size(), "No se agregó el producto correctamente.");
	    assertEquals(productoAjus, productos.get(0), "El producto agregado no es el esperado.");
	

	}
	@Test
	void testGetPrecioTotalPedido() {
		pedido.agregarProducto(productoAjus);
		int precioEsperado = 16000 + (int)(16000 * Pedido.getIva());
	    int precioCalculado = pedido.getPrecioTotalPedido();

	    assertEquals(precioEsperado, precioCalculado, "El precio total calculado del pedido no es correcto.");

		
		
		
	}
	@Test
	void testGetNumeroPedido() {
		int numeroAntes = Pedido.getNumeroPedidos();
	    Pedido nuevoPedido = new Pedido("Felipe", " Candelaria");
	    int numeroDespues = Pedido.getNumeroPedidos();
	    assertEquals(numeroAntes + 1, numeroDespues, "El numero de pedidos no incrementó correctamente despues de crear un nuevo pedido.");
	}
	@Test
	void testGuardarFactura() throws Exception{
		
		pedido.agregarProducto(productoAjus);
		File archivoFactura = File.createTempFile("factura_deCarla", ".txt");
	    archivoFactura.deleteOnExit();
	    pedido.guardarFactura(archivoFactura);

	    StringBuilder contenido = new StringBuilder();
	    Scanner lector = new Scanner(archivoFactura); //referencié de una publicacion de stackoverflow para que me ayude a hacer lectura del .txt
	    while (lector.hasNextLine()) {
	        contenido.append(lector.nextLine()).append("\n");
	    }
	    lector.close();

	    String esperado = pedido.generarTextoFactura();
	    assertEquals(esperado.trim(), contenido.toString().trim(), "El contenido de la factura no es el esperado.");	
	}
	
	
	

}
