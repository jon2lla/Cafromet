package com.cafromet.clientetest;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.cafromet.cliente.ControladorVentanMunicipio;
import com.cafromet.cliente.ControladorVentanaMediciones;
import com.cafromet.cliente.ControladorVentanaPlayas;
import com.cafromet.modelo.CentroMeteorologico;
import com.cafromet.modelo.EspacioNatural;
import com.cafromet.modelo.Medicion;
import com.cafromet.modelo.MedicionId;
import com.cafromet.modelo.Municipio;
import com.cafromet.modelo.Provincia;
import com.cafromet.server.Datos;
import com.cafromet.server.Peticiones;

public class ControladorVentanaPlayasTest {
	
	Datos dato;
	Medicion medicion;
	Municipio municipio;
	CentroMeteorologico centroMeteorologicos;
	EspacioNatural espacioNatural;
	ControladorVentanaPlayas controlador; 
	Provincia provincia;
	MedicionId MedicionId;
	@Before
	public void setUp(){
		dato = new Datos();
		municipio = new Municipio();
		centroMeteorologicos = new CentroMeteorologico();
		espacioNatural = new EspacioNatural();	
		provincia = new Provincia();
		medicion = new Medicion();
		MedicionId = new MedicionId(); 
		controlador = new ControladorVentanaPlayas();
	}

	@Test
	public void enviarPeticion() {
		controlador = Mockito.spy(controlador);
		Mockito.doReturn(true).when(controlador).procesarRecepcion();
		boolean result =controlador.enviarPeticion("prueba", Peticiones.p104c);
		assertTrue(result);
	}
	@Test
	public void testLlenarComboBoxMunicipios() {
		provincia.setIdProvincia(1);
		provincia.setNombre("prueba2");
		municipio.setIdMunicipio(1);
		municipio.setNombre("prueba");
		municipio.setProvincia(provincia);
		ArrayList<Municipio> municipios = new ArrayList<Municipio>();
		municipios.add(municipio);
		assertTrue(controlador.llenarComboBoxMunicipios(municipios));	
	}
	
	@Test
	public void testLlenarComboBoxCentros() {		
		centroMeteorologicos.setDireccion("casd");
		centroMeteorologicos.setNombre("asfas");
		centroMeteorologicos.setIdCentroMet(2);
		ArrayList<CentroMeteorologico> centros = new ArrayList<CentroMeteorologico>(); 
		centros.add(centroMeteorologicos);
		assertTrue(controlador.llenarComboBoxCentros(centros));	
	}
	
	@Test
	public void testLlenarComboEspacios() {
		espacioNatural.setCategoria("casd");
		espacioNatural.setDescripcion("descripcion");
		espacioNatural.setIdEspacio(2);
		espacioNatural.setNombre("nombre");
		ArrayList<EspacioNatural> espaciosNaturales = new ArrayList<EspacioNatural>(); 
		espaciosNaturales.add(espacioNatural);
		assertTrue(controlador.llenarComboBoxEspacios(espaciosNaturales));	
	}
	@Test
	public void testLlenarTabla() {
				
		dato.setContenido(String.valueOf(9));
		dato.setIdConexion("cas");
		dato.setPeticion(Peticiones.p106a);		

		MedicionId.setIdCentroMet(1);
		medicion.setId(MedicionId);
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
	
	@Test
	public void testLlenarTabla2() {
				
		dato.setContenido(String.valueOf(9));
		dato.setIdConexion("cas");
		dato.setPeticion(Peticiones.p106a);		

		MedicionId.setIdCentroMet(1);
		medicion.setId(MedicionId);
		medicion.setCentroMeteorologico(centroMeteorologicos);
		medicion.setDirViento(1);
		medicion.setHRelativa(1); 
		medicion.setPAtmosferica((float) 1);
		medicion.setPrecip((float) 1);
		medicion.setRadSolar((float) 1);
		medicion.setTempAmbiente((float) 1);
		medicion.setVViento((float) 1);
		medicion.setIca("bien");
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
	
	@Test
	public void testProcesarMunicipios() {
		
		dato.setContenido(String.valueOf(9));
		dato.setIdConexion("cas");
		dato.setPeticion(Peticiones.p117);		

		provincia.setIdProvincia(1);
		provincia.setNombre("prueba2");
		municipio.setIdMunicipio(1);
		municipio.setNombre("prueba");
		municipio.setProvincia(provincia);
		ArrayList<Municipio>municipios = new ArrayList<Municipio>();
		municipios.add(municipio);
		dato.setObjeto(municipios);
		Field field;
		try {
			field = ControladorVentanaPlayas.class.getDeclaredField("municipios");
			field.setAccessible(true);
			field.set(controlador, municipios);
			field = ControladorVentanaPlayas.class.getDeclaredField("datos");
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

		dato.setContenido("prueba");
		dato.setIdConexion("cas");
		dato.setPeticion(Peticiones.p105c);		

		centroMeteorologicos.setDireccion("casd");
		centroMeteorologicos.setNombre("asfas");
		centroMeteorologicos.setIdCentroMet(2);
		ArrayList<CentroMeteorologico> centros = new ArrayList<CentroMeteorologico>(); 
		centros.add(centroMeteorologicos);
		dato.setObjeto(centros);
		Field field;
		try {
			field = ControladorVentanaPlayas.class.getDeclaredField("centroMeteorologicos");
			field.setAccessible(true);
			field.set(controlador, centros);
			field = ControladorVentanaPlayas.class.getDeclaredField("datos");
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

		dato.setContenido("prueba");
		dato.setIdConexion("cas");
		dato.setPeticion(Peticiones.p106a);		

		medicion.setDirViento(1);
		
		MedicionId.setIdCentroMet(1);
		medicion.setId(MedicionId);
		medicion.setIca("prueba");
		ArrayList<Medicion> mediciones = new ArrayList<Medicion>();
		mediciones.add(medicion);
		dato.setObjeto(mediciones);
		Field field;
		try {
			field = ControladorVentanaPlayas.class.getDeclaredField("mediciones");
			field.setAccessible(true);
			field.set(controlador, mediciones);
			field = ControladorVentanaPlayas.class.getDeclaredField("datos");
			field.setAccessible(true);
			field.set(controlador, dato);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}	
		boolean rs = controlador.procesarRecepcion();
		assertEquals(true, rs);
	}
}
