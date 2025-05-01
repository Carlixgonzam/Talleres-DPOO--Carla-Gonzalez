package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.excepciones.HamburguesaException;
import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoFaltanteException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;

public class RestauranteTest {
    
    private Restaurante restaurante;
    private File archivoIngredientes;
    private File archivoMenu;
    private File archivoCombos;
    
    @BeforeEach
    void setUp() throws Exception {
        restaurante = new Restaurante();
        archivoIngredientes = new File("./data/ingredientes.txt");
        archivoMenu = new File("./data/menu.txt");
        archivoCombos = new File("./data/combos.txt");
    }
    
    @AfterEach
    void tearDown() throws Exception {
    }
    
    @Test
    void testRestaurante() {
        assertEquals(0, restaurante.getIngredientes().size(), "La cantidad de ingredientes no es la correcta.");
        assertEquals(0, restaurante.getMenuBase().size(), "La cantidad de menus bases no es la correcta.");
        assertEquals(0, restaurante.getMenuCombos().size(), "La cantidad de combos no es la correcta");
        assertEquals(0, restaurante.getPedidos().size(), "La cantidad de pedidos no es la correcta");
        assertNull(restaurante.getPedidoEnCurso(), "No deberia haber un pedido en curso al iniciar");
    }
    
    @Test
    void testIniciarPedido() {
        try {
            restaurante.iniciarPedido("Carla", "CityU Torre 1");
            assertNotNull(restaurante.getPedidoEnCurso(), "deberia existir un pedido en curso");
            assertEquals("Carla", restaurante.getPedidoEnCurso().getNombreCliente(), "el nombre del cliente no coincide");
            assertEquals("CityU Torre 1", restaurante.getPedidoEnCurso().getDireccionCliente(), "La direccion del cliente no coincide");
            
            YaHayUnPedidoEnCursoException exception = assertThrows(YaHayUnPedidoEnCursoException.class, () -> {
                restaurante.iniciarPedido("Felipe", "Candelaria");
            }, "deberia lanzar excepción al intentar iniciar un segundo pedido");
            assertTrue(exception.getMessage().contains("Carla"), "La excepción debe contener el nombre del cliente actual");
            assertTrue(exception.getMessage().contains("Felipe"), "La excepción debe contener el nombre del nuevo cliente");
        } catch (YaHayUnPedidoEnCursoException e) {
            fail("No debería lanzar excepción al iniciar el primer pedidox" + e.getMessage());
        }
    }
    @Test
    void testCerrarYGuardarPedido() {
        try {
            
            restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
            restaurante.iniciarPedido("Carla", "CityU Torre 1");
            Pedido pedidoEnCurso = restaurante.getPedidoEnCurso();
            ProductoMenu producto = restaurante.getMenuBase().get(0);
            pedidoEnCurso.agregarProducto(producto);
            int idPedido = pedidoEnCurso.getIdPedido();
            restaurante.cerrarYGuardarPedido();
            assertNull(restaurante.getPedidoEnCurso(), "no deberia haber pedido en curso despues de cerrarlo");
            File archivoFactura = new File("./facturas/factura_" + idPedido + ".txt");
            assertTrue(archivoFactura.exists(), "El archivo de factura deberia existir");
            assertEquals(1, restaurante.getPedidos().size(), "El pedido debería haberse agregado a la lista de pedidos");
            NoHayPedidoEnCursoException exception = assertThrows(NoHayPedidoEnCursoException.class, () -> {
                restaurante.cerrarYGuardarPedido();
            }, "deberia lanzar excepción al intentar cerrar un pedido cuando no hay uno en curso");
            
            assertNotNull(exception.getMessage(), "La excepción debe contener un mensaje");
            
        } catch (Exception e) {
            fail("No deberia lanzar excepcion" + e.getMessage());
        }
    }
    
    @Test
    void testCargarInformacionRestaurante() {
        try {
            restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
            ArrayList<Ingrediente> ingredientes = restaurante.getIngredientes();
            assertFalse(ingredientes.isEmpty(), "La lista de ingredientes no deberia estar vacia");
            ArrayList<ProductoMenu> menuBase = restaurante.getMenuBase();
            assertFalse(menuBase.isEmpty(), "La lista de productos del menú no debria estar vacia");
            ArrayList<Combo> combos = restaurante.getMenuCombos();
            assertFalse(combos.isEmpty(), "La lista de combos no deberia estar vacia");
        } catch (Exception e) {
            fail("No deberia lanzar excepción al cargar archivos correctos" + e.getMessage());
        }
    }
    
    @Test
    void testCargarInformacionRestauranteArchivoNoExistente() {
        File archivoInexistente = new File("./data/inexistente.txt");
        
        assertThrows(IOException.class, () -> {
            restaurante.cargarInformacionRestaurante(archivoInexistente, archivoMenu, archivoCombos);
        }, "deberia lanzar IOException al intentar cargar un archivo inexistente");
    }
    
    @Test
    void testIniciarPedidoConDatosInvalidos() {
        assertThrows(IllegalArgumentException.class, () -> {
            restaurante.iniciarPedido("", "Dirección válida");
        }, "deberia lanzar excepcion si el nombre del cliente es vacío");

        assertThrows(IllegalArgumentException.class, () -> {
            restaurante.iniciarPedido("Cliente válido", "");
        }, "deberia lanzar excepcion si la dirección del cliente es vacía");
    }
    
    @Test
    void testAgregarProductoNuloAlPedido() throws Exception {
        restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
        restaurante.iniciarPedido("Carla", "CityU Torre 1");
        Pedido pedido = restaurante.getPedidoEnCurso();
        assertThrows(IllegalArgumentException.class, () -> {
            pedido.agregarProducto(null);
        }, "deberia lanzar excepcion al intentar agregar un producto nulo");
    }
    @Test
    void testGetPedidoEnCursoNull() {
        assertNull(restaurante.getPedidoEnCurso(), "No debería haber un pedido en curso al iniciar");
    }
    
    @Test
    void testCargaExitosaInformacionRestaurante() throws Exception {
        File archivoIngredientes = File.createTempFile("ingredientes_validos", ".txt");
        File archivoMenu = File.createTempFile("menu_valido", ".txt");
        File archivoCombos = File.createTempFile("combos_validos", ".txt");

        PrintWriter writerIng = new PrintWriter(archivoIngredientes);
        writerIng.println("Lechuga;200");
        writerIng.close();

        PrintWriter writerMenu = new PrintWriter(archivoMenu);
        writerMenu.println("Hamburguesa;5000");
        writerMenu.close();

        PrintWriter writerCombos = new PrintWriter(archivoCombos);
        writerCombos.println("Combo1;10%;Hamburguesa");
        writerCombos.close();

        Restaurante restaurante = new Restaurante();
        restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);

        assertEquals(1, restaurante.getIngredientes().size());
        assertEquals(1, restaurante.getMenuBase().size());
        assertEquals(1, restaurante.getMenuCombos().size());

        archivoIngredientes.delete();
        archivoMenu.delete();
        archivoCombos.delete();
    }
    
    @Test
    void testIngredienteRepetido() throws IOException {
        File archivoIngredientesRepetidos = File.createTempFile("ingredientes_repetidos", ".txt");
        PrintWriter writer = new PrintWriter(archivoIngredientesRepetidos);
        writer.println("lechuga;1000");
        writer.println("tomate;1000");
        writer.println("lechuga;1000"); 
        writer.close();
        
        IngredienteRepetidoException exception = assertThrows(IngredienteRepetidoException.class, () -> {
            restaurante.cargarInformacionRestaurante(archivoIngredientesRepetidos, archivoMenu, archivoCombos);
        }, "deberia lanzar excepción al intentar cargar un ingrediente repetido");
        
        assertTrue(exception.getMessage().contains("lechuga"), "La excepción debe contener el nombre del ingrediente repetido");
        
        archivoIngredientesRepetidos.delete();
    }
    
    @Test
    void testProductoMenuRepetido() throws IOException {
        File archivoMenuRepetido = File.createTempFile("menu_repetido", ".txt");
        PrintWriter writer = new PrintWriter(archivoMenuRepetido);
        writer.println("hamburguesa;10000");
        writer.println("papas;5000");
        writer.println("hamburguesa;12000"); 
        writer.close();
        
        ProductoRepetidoException exception = assertThrows(ProductoRepetidoException.class, () -> {
            restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenuRepetido, archivoCombos);
        }, "deberia lanzar excepcion al intentar cargar un producto repetido");
        
        assertTrue(exception.getMessage().contains("hamburguesa"), "La excepción debe contener el nombre del producto repetido");
        archivoMenuRepetido.delete();
    }
    
    
    @Test
    void testComboRepetido() throws Exception {
        Restaurante restaurante = new Restaurante();
        restaurante.agregarProductoMenu(new ProductoMenu("Producto1", 1000));
        restaurante.agregarProductoMenu(new ProductoMenu("Producto2", 2000));
        restaurante.agregarProductoMenu(new ProductoMenu("Producto3", 3000));

        File archivoCombosRepetidos = File.createTempFile("combos_repetidos", ".txt");
        PrintWriter writer = new PrintWriter(archivoCombosRepetidos);

        String producto1 = "Producto1";
        String producto2 = "Producto2";
        String producto3 = "Producto3";

        writer.println("combo test;10%;" + producto1 + ";" + producto2 + ";" + producto3);
        writer.println("combo test;15%;" + producto1 + ";" + producto2 + ";" + producto3); 
        writer.close();

        ProductoRepetidoException exception = assertThrows(ProductoRepetidoException.class, () -> {
            restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombosRepetidos);
        });

        assertTrue(exception.getMessage().contains("combo test"), "La excepción debe contener el nombre del combo repetido");

        archivoCombosRepetidos.delete();
    }
    @Test
    void testComboConPorcentajeInvalido() throws Exception {
        File archivoInvalido = File.createTempFile("combo_porcentaje_invalido", ".txt");
        PrintWriter writer = new PrintWriter(archivoInvalido);
        writer.println("combo raro;abc;corral;papas grandes");
        writer.close();

        Restaurante restaurante = new Restaurante();
        assertThrows(NumberFormatException.class, () -> {
            restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoInvalido);
        });

        archivoInvalido.delete();
    }
    
    
    @Test
    void testProductoFaltante() throws IOException {
        File archivoCombosInvalidos = File.createTempFile("combos_invalidos", ".txt");
        PrintWriter writer = new PrintWriter(archivoCombosInvalidos);
        writer.println("combo especial;10%;corral especial;papas grandes;producto_inexistente");
        writer.close();
        
        ProductoFaltanteException exception = assertThrows(ProductoFaltanteException.class, () -> {
            restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombosInvalidos);
        }, "deberia lanzar excepción al intentar cargar un combo con un producto inexistente");
        
        assertNotNull(exception.getMessage(), "El mensaje de excepción no debe ser nulo");
        
        archivoCombosInvalidos.delete();
    }
    
    
    
    
    
    @Test
    void cargarIngreTest() throws IOException {
        File archivoIngredientesTest = File.createTempFile("ingredientes_test", ".txt");
        PrintWriter writer = new PrintWriter(archivoIngredientesTest);
        writer.println("lechuga;1000");
        writer.println("tomate;1000");
        writer.println("cebolla;1000");
        writer.close();
        
        try {
            restaurante.cargarInformacionRestaurante(archivoIngredientesTest, archivoMenu, archivoCombos);
            ArrayList<Ingrediente> ingredientes = restaurante.getIngredientes();
            
            assertEquals(3, ingredientes.size(), "La cantidad de ingredientes cargados no es correcta");
            
            boolean encontroLechuga = false;
            boolean encontroTomate = false;
            boolean encontroCebolla = false;
            
            for (Ingrediente ing : ingredientes) {
                if (ing.getNombre().equals("lechuga")) {
                    encontroLechuga = true;
                    assertEquals(1000, ing.getCostoAdicional(), "El precio de la lechuga no es correcto");
                } else if (ing.getNombre().equals("tomate")) {
                    encontroTomate = true;
                    assertEquals(1000, ing.getCostoAdicional(), "El precio del tomate no es correcto");
                } else if (ing.getNombre().equals("cebolla")) {
                    encontroCebolla = true;
                    assertEquals(1000, ing.getCostoAdicional(), "El precio de la cebolla no es correcto");
                }
            }
            
            assertTrue(encontroLechuga, "No se encontró el ingrediente lechuga");
            assertTrue(encontroTomate, "No se encontró el ingrediente tomate");
            assertTrue(encontroCebolla, "No se encontró el ingrediente cebolla");
        } catch (Exception e) {
            fail("No debería lanzar excepción al cargar ingredientes correctos: " + e.getMessage());
        } finally {
            archivoIngredientesTest.delete();
        }
    }
}