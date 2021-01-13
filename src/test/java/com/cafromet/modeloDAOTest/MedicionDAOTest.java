package com.cafromet.modeloDAOTest;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.cafromet.modelo.CentroMeteorologico;
import com.cafromet.modelo.Medicion;
import com.cafromet.modelo.MedicionId;
import com.cafromet.modeloDAO.CentroMeteorologicoDAO;
import com.cafromet.modeloDAO.MedicionDAO;

public class MedicionDAOTest {
	
	 public Date fecha() {
		 Date date = new Date();
		 Date fecha = new Date();
		 try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2020");			
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		 return date;
	 }
	 public Date Hora() {
		 Date date = new Date();
		 try {
			 date = new SimpleDateFormat("HH:mm").parse("10:10"); 
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		 return date;
	 }
	     
	@Test
	public void test() {
		
		MedicionDAO.iniciarSesion();
		Medicion medicion = new Medicion();
		
		CentroMeteorologico centroMeteorologico = new CentroMeteorologico();
		centroMeteorologico.setNombre("cafromet");
		centroMeteorologico.setIdCentroMet(5000);
		CentroMeteorologicoDAO.iniciarSesion();
		CentroMeteorologicoDAO.insertarRegistro(centroMeteorologico);
		CentroMeteorologicoDAO.consultarRegistro("cafromet");
		
		medicion.setCentroMeteorologico(centroMeteorologico);
		MedicionId id = new MedicionId();
		Date fecha = fecha();          
		Date hora = Hora();
		id.setFecha(fecha);
		id.setHora(hora);
		id.setIdCentroMet(centroMeteorologico.getIdCentroMet());
		medicion.setId(id);
		
		MedicionDAO.insertarRegistro(medicion);		
		MedicionDAO.consultarRegistro(id.getIdCentroMet());
		
		MedicionDAO.borrarRegistro(id.getIdCentroMet());
		MedicionDAO.cerrarSesion();
		
		CentroMeteorologicoDAO.borrarRegistro("cafromet");
		CentroMeteorologicoDAO.cerrarSesion();
		
	}

}
