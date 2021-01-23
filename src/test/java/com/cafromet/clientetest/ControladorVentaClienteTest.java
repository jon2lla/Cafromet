package com.cafromet.clientetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.lang.reflect.Field;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.cafromet.cliente.ControladorVentanMunicipio;
import com.cafromet.cliente.VentanaMunicipio;
import com.cafromet.modelo.Municipio;
import com.cafromet.modelo.Provincia;
import com.cafromet.modelodto.MunicipioDTO;
import com.cafromet.server.Datos;
import com.cafromet.server.Peticiones;

public class ControladorVentaClienteTest {

	ControladorVentanMunicipio controlador;
	VentanaMunicipio ventanaMunicipio;

	@Before
	public void setup() {
	ventanaMunicipio = new VentanaMunicipio();
	controlador = new ControladorVentanMunicipio(ventanaMunicipio);
	}
	
	@Test
	public void test() {
		controlador = Mockito.spy(controlador);
		Mockito.doReturn(true).when(controlador).procesarRecepcion();
		boolean result =controlador.enviarPeticion("prueba", Peticiones.p101a);
		assertTrue(result);
	}
	
	@Test
	public void testProcesar() {
		Datos dato = new Datos();
		dato.setContenido("prueba");
		dato.setIdConexion("cas");
		dato.setPeticion(Peticiones.p103a);		
		Municipio muni = new Municipio();
		muni.setDescripcion("sad");
		muni.setNombre("cas");
		muni.setIdMunicipio(1);
		ArrayList<Municipio> municipios = new ArrayList<Municipio>();
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
	
	@Test
	public void testFiltro() {
		Municipio muni = new Municipio();
		muni.setDescripcion("sad");
		muni.setNombre("cas");
		muni.setIdMunicipio(1);
		Provincia provincia = new Provincia();
		provincia.setIdProvincia(1);
		provincia.setNombre("casd");
		muni.setProvincia(provincia);
		Municipio muni2 = new Municipio();
		muni2.setDescripcion("sad");
		muni2.setNombre("cas");
		muni2.setIdMunicipio(1);
		muni2.setProvincia(provincia);
		ArrayList<Municipio> municipios = new ArrayList<Municipio>();
		municipios.add(muni);
		municipios.add(muni2);
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
		assertEquals(true, rs);
	}
}
