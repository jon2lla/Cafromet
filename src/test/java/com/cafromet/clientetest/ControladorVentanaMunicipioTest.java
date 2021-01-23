package com.cafromet.clientetest;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.cafromet.cliente.ControladorVentanMunicipio;
import com.cafromet.cliente.ControladorVentanaEspacioNatural;
import com.cafromet.cliente.VentanaMunicipio;
import com.cafromet.modelo.EspacioNatural;
import com.cafromet.modelo.Municipio;
import com.cafromet.modelo.Provincia;
import com.cafromet.server.Datos;
import com.cafromet.server.Peticiones;

public class ControladorVentanaMunicipioTest {

	VentanaMunicipio VentanaMunicipio;
	ControladorVentanMunicipio controlador;
	@Before
	public void setup(){
		VentanaMunicipio = new VentanaMunicipio();
		controlador = new ControladorVentanMunicipio(VentanaMunicipio);
	}
	
	
	@Test
	public void testFiltrar() {
		Municipio muni = new Municipio();
		Provincia provincia = new Provincia();
		provincia.setIdProvincia(1);
		provincia.setNombre("prueba2");
		muni.setIdMunicipio(1);
		muni.setNombre("prueba");
		muni.setProvincia(provincia);
		ArrayList<Municipio>municipios = new ArrayList<Municipio>();
		municipios.add(muni);
		Field field;
		try {
			field = ControladorVentanMunicipio.class.getDeclaredField("municipios");
			field.setAccessible(true);
			field.set(controlador, municipios);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}	
		boolean rs =controlador.filtrar(1);
		controlador.mLimpiarTabla();
		assertEquals(true, rs);
	}

	@Test
	public void enviarPeticion() {
		controlador = Mockito.spy(controlador);
		Mockito.doReturn(true).when(controlador).procesarRecepcion();
		boolean result =controlador.enviarPeticion("prueba", Peticiones.p103a);
		assertTrue(result);
	}
	
	@Test
	public void testProcesar() {
		Datos dato = new Datos();
		dato.setContenido("prueba");
		dato.setIdConexion("cas");
		dato.setPeticion(Peticiones.p103a);		
		Municipio muni = new Municipio();
		Provincia provincia = new Provincia();
		provincia.setIdProvincia(1);
		provincia.setNombre("prueba2");
		muni.setIdMunicipio(1);
		muni.setNombre("prueba");
		muni.setProvincia(provincia);
		ArrayList<Municipio>municipios = new ArrayList<Municipio>();
		municipios.add(muni);
		dato.setObjeto(municipios);
		Field field;
		try {
			field = ControladorVentanMunicipio.class.getDeclaredField("municipios");
			field.setAccessible(true);
			field.set(controlador, municipios);
			field = ControladorVentanMunicipio.class.getDeclaredField("datos");
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
