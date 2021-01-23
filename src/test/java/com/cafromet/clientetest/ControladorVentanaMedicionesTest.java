package com.cafromet.clientetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.cafromet.cliente.ControladorVentanaMediciones;
import com.cafromet.cliente.VentanaMediciones;
import com.cafromet.modelo.CentroMeteorologico;
import com.cafromet.modelo.Medicion;
import com.cafromet.modelo.MedicionId;
import com.cafromet.modelo.Municipio;
import com.cafromet.modelo.Provincia;
import com.cafromet.server.Datos;
import com.cafromet.server.Peticiones;

public class ControladorVentanaMedicionesTest {

	VentanaMediciones ventana;
	ControladorVentanaMediciones controlador;
	
	@Before
	public void setup() {
		ventana = new VentanaMediciones();
		controlador = new ControladorVentanaMediciones();
	}
	
	@Test
	public void testLlenarComboBoxMunicipios() {
		Municipio muni = new Municipio();
		Provincia provincia = new Provincia();
		provincia.setIdProvincia(1);
		provincia.setNombre("prueba2");
		muni.setIdMunicipio(1);
		muni.setNombre("prueba");
		muni.setProvincia(provincia);
		ArrayList<Municipio> municipios = new ArrayList<Municipio>();
		municipios.add(muni);
		assertTrue(controlador.llenarComboBoxMunicipios(municipios));	
	}
	
	@Test
	public void testLlenarComboBoxCentros() {
		CentroMeteorologico cent = new CentroMeteorologico();
		cent.setDireccion("casd");
		cent.setNombre("asfas");
		cent.setIdCentroMet(2);
		ArrayList<CentroMeteorologico> centros = new ArrayList<CentroMeteorologico>(); 
		centros.add(cent);
		assertTrue(controlador.llenarComboBoxCentros(centros));	
	}
	
	@Test
	public void enviarPeticion() {
		controlador = Mockito.spy(controlador);
		Mockito.doReturn(true).when(controlador).procesarRecepcion();
		boolean result =controlador.enviarPeticion("prueba", Peticiones.p103a);
		assertTrue(result);
	}
	
	@Test
	public void testProcesarMunicipios() {
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
			field = ControladorVentanaMediciones.class.getDeclaredField("municipios");
			field.setAccessible(true);
			field.set(controlador, municipios);
			field = ControladorVentanaMediciones.class.getDeclaredField("datos");
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
	public void testProcesarCentosMeteorologicos() {
		Datos dato = new Datos();
		dato.setContenido("prueba");
		dato.setIdConexion("cas");
		dato.setPeticion(Peticiones.p105c);		
		CentroMeteorologico cent = new CentroMeteorologico();
		cent.setDireccion("casd");
		cent.setNombre("asfas");
		cent.setIdCentroMet(2);
		ArrayList<CentroMeteorologico> centros = new ArrayList<CentroMeteorologico>(); 
		centros.add(cent);
		dato.setObjeto(centros);
		Field field;
		try {
			field = ControladorVentanaMediciones.class.getDeclaredField("centroMeteorologicos");
			field.setAccessible(true);
			field.set(controlador, centros);
			field = ControladorVentanaMediciones.class.getDeclaredField("datos");
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
	public void testProcesarMedicion() {
		Datos dato = new Datos();
		dato.setContenido("prueba");
		dato.setIdConexion("cas");
		dato.setPeticion(Peticiones.p106a);		
		Medicion medicion = new Medicion();
		medicion.setDirViento(1);
		MedicionId MedicionId = new MedicionId();
		MedicionId.setIdCentroMet(1);
		medicion.setId(MedicionId);
		medicion.setIca("prueba");
		ArrayList<Medicion> mediciones = new ArrayList<Medicion>();
		mediciones.add(medicion);
		dato.setObjeto(mediciones);
		Field field;
		try {
			field = ControladorVentanaMediciones.class.getDeclaredField("mediciones");
			field.setAccessible(true);
			field.set(controlador, mediciones);
			field = ControladorVentanaMediciones.class.getDeclaredField("datos");
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
		Datos dato = new Datos();
		dato.setContenido("prueba");
		dato.setIdConexion("cas");
		dato.setPeticion(Peticiones.p106a);		
		Medicion medicion = new Medicion();
		medicion.setDirViento(1);
		MedicionId MedicionId = new MedicionId();
		MedicionId.setIdCentroMet(1);
		medicion.setId(MedicionId);
		medicion.setIca("prueba");
		ArrayList<Medicion> mediciones = new ArrayList<Medicion>();
		mediciones.add(medicion);
		dato.setObjeto(mediciones);
		Field field;
		try {
			field = ControladorVentanaMediciones.class.getDeclaredField("mediciones");
			field.setAccessible(true);
			field.set(controlador, mediciones);
			field = ControladorVentanaMediciones.class.getDeclaredField("datos");
			field.setAccessible(true);
			field.set(controlador, dato);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}	
		controlador.mLimpiarTabla();
		boolean rs=controlador.llenarTabla(mediciones);
		assertEquals(true,rs);
	}
	
	@Test
	public void testLlenarTabla2() {
		Datos dato = new Datos();
		dato.setContenido("prueba");
		dato.setIdConexion("cas");
		dato.setPeticion(Peticiones.p106a);
		CentroMeteorologico cent = new CentroMeteorologico();
		cent.setDireccion("casd");
		cent.setNombre("asfas");
		cent.setIdCentroMet(2);
		Medicion medicion = new Medicion();		
		MedicionId MedicionId = new MedicionId();
		MedicionId.setIdCentroMet(1);
		medicion.setId(MedicionId);
		medicion.setCentroMeteorologico(cent);
		medicion.setHRelativa(1);
		medicion.setPAtmosferica((float) 2);
		medicion.setPrecip((float) 2);
		medicion.setRadSolar((float) 2);
		medicion.setTempAmbiente((float) 2);
		medicion.setVViento((float) 2);
		ArrayList<Medicion> mediciones = new ArrayList<Medicion>();
		mediciones.add(medicion);
		dato.setObjeto(mediciones);
		Field field;
		try {
			field = ControladorVentanaMediciones.class.getDeclaredField("mediciones");
			field.setAccessible(true);
			field.set(controlador, mediciones);
			field = ControladorVentanaMediciones.class.getDeclaredField("datos");
			field.setAccessible(true);
			field.set(controlador, dato);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}	
		controlador.mLimpiarTabla();
		boolean rs=controlador.llenarTabla(mediciones);
		assertEquals(true,rs);
	}
}
