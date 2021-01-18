package com.cafromet.modelodaotest;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.cafromet.modelo.CentroMeteorologico;
import com.cafromet.modelo.Medicion;
import com.cafromet.modelo.MedicionId;
import com.cafromet.modelodao.CentroMeteorologicoDAO;
import com.cafromet.modelodao.MedicionDAO;

public class MedicionDAOTest {
	
	Medicion medicion;
	Medicion medicion2;
	Date fecha;
	Date hora;
	
	@Before 
	public void setUp() {
		 fecha = new Date();
		 hora = new Date();
		 try {
			 fecha = new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2020");	
			 hora = new SimpleDateFormat("HH:mm").parse("10:10"); 
		} catch (ParseException e) {
			System.out.println("\n !ERROR => PARSEEXCEPTION || CLASE => MEDICIONDAOTEST");
		}   
	}
	     
	@Test
	public void test() {
		
		MedicionDAO.iniciarSesion();
		medicion = new Medicion();
		
		CentroMeteorologico centroMeteorologico = new CentroMeteorologico();
		centroMeteorologico.setNombre("cafromet");
		centroMeteorologico.setIdCentroMet(5000);
		CentroMeteorologicoDAO.iniciarSesion();
		CentroMeteorologicoDAO.insertarRegistro(centroMeteorologico);
		centroMeteorologico = CentroMeteorologicoDAO.consultarRegistro("cafromet");
		
		MedicionId id = new MedicionId();
		id.setFecha(fecha);
		id.setHora(hora);
		id.setIdCentroMet(centroMeteorologico.getIdCentroMet());
		medicion.setCentroMeteorologico(centroMeteorologico);
		medicion.setId(id);
		MedicionDAO.insertarRegistro(medicion);		
//		medicion = MedicionDAO.consultarRegistro(medicion);
		medicion = new Medicion(id, centroMeteorologico);	
		medicion2 = new Medicion(id, centroMeteorologico);
		
		assertEquals(medicion, medicion2);
	
		CentroMeteorologicoDAO.borrarRegistro("cafromet");
//		MedicionDAO.cerrarSesion();
//		CentroMeteorologicoDAO.cerrarSesion();
		
	}
	
	@Test
	public void consultar() {
		
		MedicionDAO.iniciarSesion();
		medicion = new Medicion();
		
		CentroMeteorologico centroMeteorologico = new CentroMeteorologico();
		centroMeteorologico.setNombre("cafromet");
		centroMeteorologico.setIdCentroMet(5000);
		CentroMeteorologicoDAO.iniciarSesion();
		CentroMeteorologicoDAO.insertarRegistro(centroMeteorologico);
		CentroMeteorologicoDAO.consultarRegistro("cafromet");
			
		MedicionId id = new MedicionId();

		id.setFecha(fecha);
		id.setHora(hora);
		id.setIdCentroMet(centroMeteorologico.getIdCentroMet());
		
		medicion.setId(id);
		medicion.setCentroMeteorologico(centroMeteorologico);
		MedicionDAO.insertarRegistro(medicion);
		medicion2 = new Medicion(id, centroMeteorologico);
		MedicionDAO.insertarRegistro(medicion2);
//		MedicionDAO.consultarRegistro(medicion);
		boolean result = MedicionDAO.duplicado(medicion);
		

		assertEquals(true, result);
		
		CentroMeteorologicoDAO.borrarRegistro("cafromet");
//		MedicionDAO.cerrarSesion();
//		CentroMeteorologicoDAO.cerrarSesion();
	}

}
