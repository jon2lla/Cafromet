package com.cafromet.modeloDAO;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.TransactionException;
import org.hibernate.exception.ConstraintViolationException;

import com.cafromet.modelo.CentroMeteorologico;
import com.cafromet.modelo.Cliente;
import com.cafromet.modelo.EspacioNatural;
import com.cafromet.modelo.Fuente;
import com.cafromet.modelo.Municipio;
import com.cafromet.modelo.Provincia;
//Imports locales
import com.cafromet.util.HibernateUtil;

public class PruebasCRUD {
	
	public static boolean pruebas() {
        boolean correcto=false;
    	try {   		
    		pruebaMunicipio();
			pruebaCentroMeteoroLogico();
    		pruebaCliente();
    		pruebaEspacioNatural();
    		pruebaFuente();
    		pruebaProvincia();
    		correcto = true;
		} catch (ObjectNotFoundException o) {
			System.out.println("ERROR: OBJECT NOT FOUND");
		} catch (ConstraintViolationException cve) {
			
			System.out.println("ERROR: CONSTRAINT VIOLATION");
		} catch (TransactionException e) {
			System.out.println("ERROR: TRANSACTION EXCEPTION");
		}  catch (Exception e) {
			System.out.println("EXCEPCION SIN CONTROLAR");
			e.printStackTrace();
		} finally {
			HibernateUtil.shutdown();
		}
		return correcto;      
	}

	//metodos para probrar las BBDD
	private static boolean pruebaMunicipio() {
		boolean correcto = false;
		Municipio municipio = new Municipio();
		municipio.setNombre("MUNICIPIO BAKIO");
		municipio.setIdMunicipio(7);
		MunicipioDAO.iniciarSesion();
		MunicipioDAO.insertarRegistro(municipio);
		MunicipioDAO.consultarRegistro(7);
		MunicipioDAO.borrarRegistro(7);
		correcto=true;
		return correcto;
	}

	private static void pruebaCliente() {
		Cliente cliente = new Cliente();
		cliente.setIdCliente(4);
		cliente.setUsuario("CLIENTE CAFROMET");
		cliente.setPasswd("CAFROMET");
		ClienteDAO.iniciarSesion();
		ClienteDAO.insertarRegistro(cliente);
		ClienteDAO.consultarRegistro(4);
		ClienteDAO.borrarRegistro(4);
	}
	
	private static void pruebaEspacioNatural() {
		EspacioNatural espacioNat = new EspacioNatural();
		espacioNat.setCategoria("PRUEBA ESPACIO NATURAL");
		espacioNat.setDescripcion("PRUEBA SPRINT1");
		espacioNat.setDireccion("ELORRIETA");
		espacioNat.setIdEspacio(2);
		espacioNat.setNombre("ESPACIO NATURAL CAFROMET");
		espacioNat.setTipo("PLAYA");
		EspacioNaturalDAO.iniciarSesion();
		EspacioNaturalDAO.insertarRegistro(espacioNat);
		EspacioNaturalDAO.consultarRegistro(2);
		EspacioNaturalDAO.borrarRegistro(2);
	}
	
	private static void pruebaFuente() {
		Fuente fuente = new Fuente();
		fuente.setId(2);
		fuente.setNombre("FUENTES CAFROMET");
		fuente.setUrl("www.CAFROMET.com");
		fuente.setFormato("XML");
		FuenteDAO.iniciarSesion();
		FuenteDAO.insertarRegistro(fuente);
		FuenteDAO.consultarRegistro(2);
		FuenteDAO.borrarRegistro(2);
	}
	
	private static void pruebaProvincia() {
		Provincia provincia = new  Provincia();
		provincia.setIdProvincia(9);
		provincia.setNombre("PROVINCIA CAFROMET");
		ProvinciaDAO.iniciarSesion();
		ProvinciaDAO.insertarRegistro(provincia);
		ProvinciaDAO.consultarRegistro(9);
		ProvinciaDAO.borrarRegistro(9);	
	}
	
	private static void pruebaCentroMeteoroLogico() {
	CentroMeteorologico centro = new CentroMeteorologico();
	Municipio municipio = new Municipio();
	municipio.setIdMunicipio(4);
	MunicipioDAO.insertarRegistro(municipio);
	centro.setNombre("CENTRO CAFROMET");
	centro.setDireccion("elorrieta");
	centro.setIdCentroMet(8);
	centro.setMunicipio(municipio);
	CentroMeteorologicoDAO.iniciarSesion();
	CentroMeteorologicoDAO.insertarRegistro(centro);
	CentroMeteorologicoDAO.consultarRegistro(8);
	CentroMeteorologicoDAO.borrarRegistro(8);
	MunicipioDAO.borrarRegistro(4);
}
	
}
