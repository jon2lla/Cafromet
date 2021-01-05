package com.cafromet.modeloDAO;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.TransactionException;
import org.hibernate.exception.ConstraintViolationException;

import com.cafromet.modelo.CentroMeteorologico;
import com.cafromet.modelo.Cliente;
import com.cafromet.modelo.EspacioNatural;
import com.cafromet.modelo.Fuente;
import com.cafromet.modelo.Municipio;
import com.cafromet.modelo.Municipio_EspacioNatural;
import com.cafromet.modelo.Municipio_EspacioNaturalId;
import com.cafromet.modelo.Provincia;
//Imports locales
import com.cafromet.util.HibernateUtil;

public class PruebasCRUD {
	
	/**
	 * Metodo que realiza una llamada a cada metodo de prueba, para comprobar el correcto funcionamiento de la BBDD 
	 * @return True
	 */
	public static boolean pruebas() {
    	try {   		
    		pruebaProvincia();
    		pruebaMunicipio();
			pruebaCentroMeteoroLogico();
    		pruebaCliente();
    		pruebaEspacioNatural();
    		pruebaFuente();
    		pruebaMunicipioEspacioNat();
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
		return true;      
	}

	/**
	 * Metodo que realiza una inserción, una selección y un borrado para comprobar el correcto funcionamiento de la tabla: Municipio
	 * @return True
	 */
	private static boolean pruebaMunicipio() {
		Municipio municipio = new Municipio();
		municipio.setNombre("MUNICIPIO BAKIO");
		municipio.setIdMunicipio(0);
		
		MunicipioDAO.iniciarSesion();
		MunicipioDAO.insertarRegistro(municipio);
		MunicipioDAO.consultarRegistro(0);
		MunicipioDAO.borrarRegistro(0);
		
		return true;
	}

	/**
	 * Metodo que realiza una inserción, una selección y un borrado para comprobar el correcto funcionamiento de la tabla: Cliente
	 * @return True
	 */
	private static boolean pruebaCliente() {
		Cliente cliente = new Cliente();
		cliente.setUsuario("CLIENTE CAFROMET");
		cliente.setPasswd("CAFROMET");
		
		ClienteDAO.iniciarSesion();
		ClienteDAO.insertarRegistro(cliente);
		ClienteDAO.consultarRegistro(1);
		ClienteDAO.borrarRegistro(1);
		
		return true;
	}

	/**
	 * Metodo que realiza una inserción, una selección y un borrado para comprobar el correcto funcionamiento de la tabla: EspacioNatural
	 * @return True
	 */
	private static boolean pruebaEspacioNatural() {
		EspacioNatural espacioNat = new EspacioNatural();
		espacioNat.setCategoria("PRUEBA ESPACIO NATURAL");
		espacioNat.setDescripcion("PRUEBA SPRINT1");
		espacioNat.setDireccion("ELORRIETA");
		espacioNat.setIdEspacio(2);
		espacioNat.setNombre("ESPACIO NATURAL CAFROMET");
		espacioNat.setTipo("PLAYA");
		
		EspacioNaturalDAO.iniciarSesion();
		EspacioNaturalDAO.insertarRegistro(espacioNat);
		EspacioNaturalDAO.consultarRegistro(1);
		EspacioNaturalDAO.borrarRegistro(1);
		
		return true;
	}

	/**
	 * Metodo que realiza una inserción, una selección y un borrado para comprobar el correcto funcionamiento de la tabla: Fuente
	 * @return True
	 */
	private static boolean pruebaFuente() {
		Fuente fuente = new Fuente();
		fuente.setId(2);
		fuente.setNombre("FUENTES CAFROMET");
		fuente.setUrl("www.CAFROMET.com");
		fuente.setFormato("XML");
		
		FuenteDAO.iniciarSesion();
		FuenteDAO.insertarRegistro(fuente);
		FuenteDAO.consultarRegistro(2);
		FuenteDAO.borrarRegistro(2);
		
		return true;
	}

	/**
	 * Metodo que realiza una inserción, una selección y un borrado para comprobar el correcto funcionamiento de la tabla: Provincia
	 * @return True
	 */
	private static boolean pruebaProvincia() {
		Provincia provincia = new  Provincia();
		provincia.setIdProvincia(9);
		provincia.setNombre("PROVINCIA CAFROMET");
		
		ProvinciaDAO.iniciarSesion();
		ProvinciaDAO.insertarRegistro(provincia);
		ProvinciaDAO.consultarRegistro(9);
		ProvinciaDAO.borrarRegistro(9);	
		
		return true;
	}

	/**
	 * Metodo que realiza una inserción, una selección y un borrado para comprobar el correcto funcionamiento de la tabla: CentroMeteorologico
	 * @return True
	 */
	private static boolean pruebaCentroMeteoroLogico() {
		CentroMeteorologico centro = new CentroMeteorologico();
		Municipio municipio = new Municipio();
		municipio.setNombre("MUNICIPIO BAKIO");
		municipio.setIdMunicipio(0);
		
		MunicipioDAO.iniciarSesion();
		MunicipioDAO.insertarRegistro(municipio);
		
		centro.setNombre("CENTRO CAFROMET");
		centro.setDireccion("elorrieta");
		centro.setMunicipio(municipio);
		
		CentroMeteorologicoDAO.iniciarSesion();
		CentroMeteorologicoDAO.insertarRegistro(centro);
		CentroMeteorologicoDAO.consultarRegistro(1);
		CentroMeteorologicoDAO.borrarRegistro(1);
		MunicipioDAO.borrarRegistro(0);
		
		return true;
	}	

	/**
	 * Metodo que realiza una inserción, una selección y un borrado para comprobar el correcto funcionamiento de la tabla: Municipio_EspacioNatural
	 * @return True
	 */
	private static boolean pruebaMunicipioEspacioNat() {
		

		Municipio municipio = new Municipio();
		municipio.setNombre("MUNICIPIO BAKIO");
		municipio.setIdMunicipio(7);
		
		MunicipioDAO.iniciarSesion();
		MunicipioDAO.insertarRegistro(municipio);
		
		EspacioNatural espacioNat = new EspacioNatural();
		espacioNat.setCategoria("PRUEBA ESPACIO NATURAL");
		espacioNat.setDescripcion("PRUEBA SPRINT1");
		espacioNat.setDireccion("ELORRIETA");
		espacioNat.setIdEspacio(2);
		espacioNat.setNombre("ESPACIO NATURAL CAFROMET");
		espacioNat.setTipo("PLAYA");
		
		EspacioNaturalDAO.iniciarSesion();
		EspacioNaturalDAO.insertarRegistro(espacioNat);
		
		Municipio_EspacioNaturalId municipio_EspacioNaturalId = new Municipio_EspacioNaturalId(espacioNat.getIdEspacio(), municipio.getIdMunicipio());
		Municipio_EspacioNatural municipio_EspacioNatural = new Municipio_EspacioNatural(municipio_EspacioNaturalId, espacioNat, municipio);		
		
		Municipio_EspacioNatDAO.iniciarSesion();
		Municipio_EspacioNatDAO.insertarRegistro(municipio_EspacioNatural);	
		Municipio_EspacioNatDAO.consultarRegistro(municipio_EspacioNaturalId);
		Municipio_EspacioNatDAO.borrarRegistro(municipio_EspacioNaturalId);

		MunicipioDAO.borrarRegistro(7);
		
		EspacioNaturalDAO.borrarRegistro(2);	
		
		return true;
	}
}
