package com.cafromet.clientetest;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.cafromet.cliente.ControladorVentanMunicipio;
import com.cafromet.cliente.ControladorVentanaMediciones;
import com.cafromet.cliente.ControladorVentanaTop;
import com.cafromet.cliente.VentanaTop;
import com.cafromet.modelo.Medicion;
import com.cafromet.modelo.MedicionId;
import com.cafromet.modelo.Municipio;
import com.cafromet.server.Datos;
import com.cafromet.server.Peticiones;

public class ControladorVentanaTopTest {

	ControladorVentanaTop controlador;
	VentanaTop ventanaTop;
	Medicion medicion;
	Datos dato;
	MedicionId MedicionId;
	@Before
	public void setUp() {
		MedicionId = new MedicionId();
		medicion = new Medicion();
		dato = new Datos();
		controlador = new ControladorVentanaTop();
	}

	@Test
	public void testEnviarPeticion() {
		controlador = Mockito.spy(controlador);
		Mockito.doReturn(true).when(controlador).procesarRecepcion();
		boolean result =controlador.enviarPeticion("prueba", Peticiones.p120);
		assertTrue(result);
	}
	
	@Test
	public void testProcesar() {

		dato.setContenido("top");
		dato.setIdConexion("cas");
		dato.setPeticion(Peticiones.p120);		

		MedicionId = new MedicionId();
		Date fecha = null,hora = null;
		MedicionId.setFecha(fecha);
		MedicionId.setHora(hora);
		MedicionId.setIdCentroMet(2);
		medicion.setId(MedicionId);
		medicion.setDirViento(1);
		medicion.setHRelativa(2);
		ArrayList<Medicion>mediciones = new ArrayList<Medicion>();
		mediciones.add(medicion);
		dato.setObjeto(mediciones);
		Field field;
		try {
			field = ControladorVentanaTop.class.getDeclaredField("mediciones");
			field.setAccessible(true);
			field.set(controlador, mediciones);
			field = ControladorVentanaTop.class.getDeclaredField("datos");
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
	public void testLlenarTabla() {
				
		dato.setContenido(String.valueOf(9));
		dato.setIdConexion("cas");
		dato.setPeticion(Peticiones.p106a);		

		medicion.setPrecip((float) 1);
		ArrayList<Medicion> mediciones = new ArrayList<Medicion>();
		mediciones.add(medicion);
		dato.setObjeto(mediciones);
		Field field;
		try {
			field = ControladorVentanaMediciones.class.getDeclaredField("mediciones");
			field.setAccessible(true);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} 
		controlador.mLimpiarTabla();
		boolean rs=controlador.llenarTabla(mediciones);
		assertEquals(true,rs);
	}
}
