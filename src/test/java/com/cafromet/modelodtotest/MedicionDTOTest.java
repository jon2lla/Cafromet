package com.cafromet.modelodtotest;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelodto.MedicionDTO;

public class MedicionDTOTest {

	MedicionDTO medicionDTO;
	MedicionDTO medicionDTO2;
	
	@Before
	public void setUp() {
		medicionDTO = new MedicionDTO();
		medicionDTO2 = new MedicionDTO();
	}

	@Test
	public void testConstructor() {
		Float PAtmosferica = null;
		Float precip = null;
		Float radSolar = null;
		Float tempAmbiente = null;
		Float VViento = null;
		Date fecha = null,hora = null;
		medicionDTO = new MedicionDTO(5, "centroMeteorologico", 2, 2, PAtmosferica, precip, radSolar, tempAmbiente, VViento, "ica");
		medicionDTO.setCentroMeteorologico("centroMeteorologico");
		medicionDTO.setDirViento(2);	
		medicionDTO.setFecha(fecha);
		medicionDTO.setHora(hora);
		medicionDTO.setHRelativa(2);
		medicionDTO.setIca("ica");
		medicionDTO.setId(1);
		medicionDTO.setPAtmosferica(PAtmosferica);
		medicionDTO.setPrecip(precip);
		medicionDTO.setRadSolar(radSolar);
		medicionDTO.setTempAmbiente(tempAmbiente);
		medicionDTO.setVViento(VViento);
		assertNotEquals(medicionDTO, medicionDTO2);
	}
	@Test
	public void testConstructor2() {
		medicionDTO = new MedicionDTO(2, "centroMeteorologico");
		medicionDTO.getCentroMeteorologico();
		medicionDTO.getDirViento();
		medicionDTO.getFecha();
		medicionDTO.getHora();
		medicionDTO.getHRelativa();
		medicionDTO.getIca();
		medicionDTO.getId();
		medicionDTO.getPAtmosferica();
		medicionDTO.getPrecip();
		medicionDTO.getRadSolar();
		medicionDTO.getTempAmbiente();
		medicionDTO.getVViento();
		assertNotEquals(medicionDTO, medicionDTO2);
	}
}
