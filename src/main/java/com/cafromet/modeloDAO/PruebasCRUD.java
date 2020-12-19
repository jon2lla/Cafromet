package com.cafromet.modeloDAO;

//Imports externos
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.TransactionException;
import org.hibernate.exception.ConstraintViolationException;

import com.cafromet.modelo.CentrosMeteorologicos;
import com.cafromet.modelo.Clientes;
import com.cafromet.modelo.EspaciosNaturales;
import com.cafromet.modelo.Fuentes;
import com.cafromet.modelo.Municipios;
import com.cafromet.modelo.Provincias;
//Imports locales
import com.cafromet.util.HibernateUtil;

public class PruebasCRUD {
	
	public static void pruebas() {
        
    	try {   		
    		pruebaMunicipios();
			pruebaCentrosMeteoroLogicos();
    		pruebaCliente();
    		pruebaEspaciosNaturales();
    		pruebaFuentes();
    		pruebaProvincia();
		} catch (ObjectNotFoundException o) {
			System.out.println("ERROR: OBJECT NOT FOUND");
		} catch (ConstraintViolationException cve) {
			if (MunicipiosDAO.SESSION.getTransaction() != null) {
				MunicipiosDAO.SESSION.getTransaction().rollback();
			}
			System.out.println("ERROR: CONSTRAINT VIOLATION");

		} catch (TransactionException e) {

			System.out.println("ERROR: TRANSACTION EXCEPTION");

		}  catch (Exception e) {
			System.out.println("EXCEPCION SIN CONTROLAR");
			e.printStackTrace();
		} finally {
			HibernateUtil.shutdown();
		}      
	}

	//metodos para probrar las BBDD
	private static void pruebaMunicipios() {
		Municipios municipio = new Municipios();
		municipio.setNombre("MUNICIPIO BAKIO");
		municipio.setIdMunicipio(7);
		MunicipiosDAO.iniciarSesion();
		MunicipiosDAO.insertarRegistro(municipio);
		MunicipiosDAO.consultarRegistro(7);
		MunicipiosDAO.borrarRegistro(7);
	}

	private static void pruebaCliente() {
		Clientes clientes = new Clientes();
		clientes.setIdCliente(4);
		clientes.setUsuario("CLIENTE CAFROMET");
		clientes.setPasswd("CAFROMET");
		ClientesDAO.iniciarSesion();
		ClientesDAO.insertarRegistro(clientes);
		ClientesDAO.consultarRegistro(4);
		ClientesDAO.borrarRegistro(4);
	}
	
	private static void pruebaEspaciosNaturales() {
		EspaciosNaturales espacioNat = new EspaciosNaturales();
		espacioNat.setCategoria("PRUEBA ESPACIO NATURAL");
		espacioNat.setDescripcion("PRUEBA SPRINT1");
		espacioNat.setDireccion("ELORRIETA");
		espacioNat.setIdEspacio(2);
		espacioNat.setNombre("ESPACIO NATURAL CAFROMET");
		espacioNat.setTipo("PLAYA");
		EspaciosNaturalesDAO.iniciarSesion();
		EspaciosNaturalesDAO.insertarRegistro(espacioNat);
		EspaciosNaturalesDAO.consultarRegistro(2);
		EspaciosNaturalesDAO.borrarRegistro(2);
	}
	
	private static void pruebaFuentes() {
		Fuentes fuentes = new Fuentes();
		fuentes.setIdIndex(2);
		fuentes.setNombre("FUENTES CAFROMET");
		fuentes.setUrl("www.CAFROMET.com");
		fuentes.setFormato("XML");
		FuentesDAO.iniciarSesion();
		FuentesDAO.insertarRegistro(fuentes);
		FuentesDAO.consultarRegistro(2);
		FuentesDAO.borrarRegistro(2);
	}
	
	private static void pruebaProvincia() {
		Provincias provincias = new  Provincias();
		provincias.setIdProvincia(9);
		provincias.setNombre("PROVINCIA CAFROMET");
		ProvinciasDAO.iniciarSesion();
		ProvinciasDAO.insertarRegistro(provincias);
		ProvinciasDAO.consultarRegistro(9);
		ProvinciasDAO.borrarRegistro(9);	
	}
	
	private static void pruebaCentrosMeteoroLogicos() {
	CentrosMeteorologicos centros = new CentrosMeteorologicos();
	Municipios municipio = new Municipios();
	municipio.setIdMunicipio(4);
	MunicipiosDAO.insertarRegistro(municipio);
	centros.setNombre("CENTRO CAFROMET");
	centros.setDireccion("elorrieta");
	centros.setIdCentroMet(8);
	centros.setMunicipios(municipio);
	CentrosMeteorologicosDAO.iniciarSesion();
	CentrosMeteorologicosDAO.insertarRegistro(centros);
	CentrosMeteorologicosDAO.consultarRegistro(8);
	CentrosMeteorologicosDAO.borrarRegistro(8);
}
	
}
