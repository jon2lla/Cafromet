//package com.cafromet.clientetest;
//
//import static org.junit.Assert.*;
//
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mockito;
//import org.mockito.Spy;
//
//
//import com.cafromet.cliente.ControladorVentanaEspacioNatural;
//import com.cafromet.cliente.VentanaEspacioNatural;
//import com.cafromet.modelo.EspacioNatural;
//import com.cafromet.server.Datos;
//import com.cafromet.server.Peticiones;
//
//public class ControladorVentanaEspacioNaturalTest {
//	
//	VentanaEspacioNatural ventanaEspacioNatural;
//	@Spy
//	ControladorVentanaEspacioNatural controlador;
//	
//	@Before
//	public void setUp() {
//		ventanaEspacioNatural = new VentanaEspacioNatural();
//		controlador = new ControladorVentanaEspacioNatural(ventanaEspacioNatural);
//	}
//
//	@Test
//	public void enviarPeticion() {
//		controlador = Mockito.spy(controlador);
//		Mockito.doReturn(true).when(controlador).procesarRecepcion();
//		boolean result = controlador.enviarPeticion("prueba", Peticiones.p104a);
//		assertTrue(result);
//	}
//	@Test
//	public void test() {
//		EspacioNatural espacio = new EspacioNatural();
//		espacio.setCategoria("Pantanos");
//		espacio.setDescripcion("limpia");
//		espacio.setIdEspacio(1);
//		espacio.setNombre("playa");
//		ArrayList<EspacioNatural> espacioNatural = new ArrayList<EspacioNatural>();
//		espacioNatural.add(espacio);
//		Field field;
//		try {
//			field = ControladorVentanaEspacioNatural.class.getDeclaredField("espacioNatural");
//			field.setAccessible(true);
//			field.set(controlador, espacioNatural);
//		} catch (NoSuchFieldException | SecurityException e) {
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		}	
//		boolean rs =controlador.filtrar("Pantanos");
//		controlador.mLimpiarTabla();
//		assertEquals(true, rs);
//	}
//	
//	@Test
//	public void testProcesar() {
//		Datos dato = new Datos();
//		dato.setContenido("prueba");
//		dato.setIdConexion("cas");
//		dato.setPeticion(Peticiones.p104a);		
//		EspacioNatural espacio = new EspacioNatural();
//		espacio.setCategoria("Pantanos");
//		espacio.setDescripcion("limpia");
//		espacio.setIdEspacio(1);
//		espacio.setNombre("playa");
//		ArrayList<EspacioNatural> espacioNatural = new ArrayList<EspacioNatural>();
//		espacioNatural.add(espacio);
//		dato.setObjeto(espacioNatural);
//		Field field;
//		try {
//			field = ControladorVentanaEspacioNatural.class.getDeclaredField("espacioNatural");
//			field.setAccessible(true);
//			field.set(controlador, espacioNatural);
//			field = ControladorVentanaEspacioNatural.class.getDeclaredField("datos");
//			field.setAccessible(true);
//			field.set(controlador, dato);
//		} catch (NoSuchFieldException | SecurityException e) {
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		}	
//		boolean rs =controlador.procesarRecepcion();
//		assertEquals(true, rs);
//	}
//}
