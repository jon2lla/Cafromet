package com.cafromet.clientetest;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;


import com.cafromet.cliente.ControladorVentanaEspacioNatural;
import com.cafromet.cliente.VentanaEspacioNatural;
import com.cafromet.modelo.EspacioNatural;
import com.cafromet.server.Datos;
import com.cafromet.server.Peticiones;

public class ControladorVentanaEspacioNaturalTest {
	
	VentanaEspacioNatural ventanaEspacioNatural;
	@Spy
	ControladorVentanaEspacioNatural controlador;
	EspacioNatural espacio;
	Datos dato;
	@Before
	public void setUp() {
		ventanaEspacioNatural = new VentanaEspacioNatural();
		controlador = new ControladorVentanaEspacioNatural(ventanaEspacioNatural);
		espacio = new EspacioNatural();
		dato = new Datos();
	}

	@Test
	public void enviarPeticion() {
		ActionEvent e = new ActionEvent(ventanaEspacioNatural, 0, "volver");
		controlador.actionPerformed(e );
		controlador = Mockito.spy(controlador);
		Mockito.doReturn(true).when(controlador).procesarRecepcion();
		boolean result = controlador.enviarPeticion("prueba", Peticiones.p104a);
		assertTrue(result);
	}
	@Test
	public void test() {
		
		espacio.setCategoria("Pantanos");
		espacio.setDescripcion("limpia");
		espacio.setIdEspacio(1);
		espacio.setNombre("playa");
		ArrayList<EspacioNatural> espacioNatural = new ArrayList<EspacioNatural>();
		espacioNatural.add(espacio);
		Field field;
		try {
			field = ControladorVentanaEspacioNatural.class.getDeclaredField("espacioNatural");
			field.setAccessible(true);
			field.set(controlador, espacioNatural);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}	
		boolean rs =controlador.filtrar("Pantanos");
		controlador.mLimpiarTabla();
		assertEquals(true, rs);
	}
	
	@Test
	public void testProcesar() {
		
		dato.setContenido("prueba");
		dato.setIdConexion("cas");
		dato.setPeticion(Peticiones.p104a);		
	
		espacio.setCategoria("Pantanos");
		espacio.setDescripcion("limpia");
		espacio.setIdEspacio(1);
		espacio.setNombre("playa");
		ArrayList<EspacioNatural> espacioNatural = new ArrayList<EspacioNatural>();
		espacioNatural.add(espacio);
		dato.setObjeto(espacioNatural);
		Field field;
		try {
			field = ControladorVentanaEspacioNatural.class.getDeclaredField("espacioNatural");
			field.setAccessible(true);
			field.set(controlador, espacioNatural);
			field = ControladorVentanaEspacioNatural.class.getDeclaredField("datos");
			field.setAccessible(true);
			field.set(controlador, dato);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}	
		boolean rs =controlador.procesarRecepcion();
		assertEquals(true, rs);
	}
}
