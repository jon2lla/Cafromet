package com.cafromet.modelotest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelo.Medicion;
import com.cafromet.modelo.MedicionId;

public class MedicionTest {
	MedicionId medId;
	Medicion med;
	Medicion medicion;
	Medicion medicion2;
	@Before 
	public void onLoad() {
		med = new Medicion();
		medId = new MedicionId();
		medId.setIdCentroMet(1);
		medicion =  new Medicion(medId, null);
		medicion2 = new Medicion(medId, null, null, null, null, null, null, null, null, null);
	}
	
	@Test
	public void testConstructorMedicion() {
		assertEquals(medicion.getId(), medicion2.getId());	
	}

	@Test
	public void testSetMedicion() {
		medicion.setId(medId);
		medicion.setCentroMeteorologico(null);
		medicion.setDirViento(null);
		medicion.setHRelativa(null); 
		medicion.setPAtmosferica(null);
		medicion.setPrecip(null);
		medicion.setRadSolar(null);
		medicion.setTempAmbiente(null);
		medicion.setVViento(null);
		medicion.setIca(null);
		
		assertEquals(medicion.getId(), medicion2.getId());
	}
	
	@Test
	public void testGetMedicion() {
		medicion.getId();
		medicion.getCentroMeteorologico();
		medicion.getDirViento();
		medicion.getHRelativa(); 
		medicion.getPAtmosferica();
		medicion.getPrecip();
		medicion.getRadSolar();
		medicion.getTempAmbiente();
		medicion.getVViento();
		medicion.getIca();
		
		assertEquals(medicion.getId(), medicion2.getId());
	}
	
	@Test
	public void testHash() {
		int num1 = medicion.hashCode();
		int num2 = medicion.hashCode();
		assertEquals(num1,num2);
	}
	@Test
	public void testEquals() {	
		assertEquals(medicion,medicion2);
	}
	
}
