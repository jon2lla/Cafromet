package com.cafromet.modeloDAO;

//Imports externos
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.TransactionException;
import org.hibernate.exception.ConstraintViolationException;

import com.cafromet.modelo.Municipios;
//Imports locales
import com.cafromet.util.HibernateUtil;

public class PruebasCRUD {
	public static void pruebas() {
        
    	try {
    		
            
    		Municipios municipio = new Municipios();
    		municipio.setNombre("BAKIO");
    		municipio.setIdMunicipio(5);
    		MunicipiosDAO.iniciarSesion();
    		MunicipiosDAO.insertarRegistro(municipio);
    		MunicipiosDAO.consultarRegistro(5);
    		MunicipiosDAO.borrarRegistro(5);
                		      
		} catch (ObjectNotFoundException o) {
			System.out.println("ERROR: OBJECT NOT FOUND");
		} catch (ConstraintViolationException cve) {
			if (MunicipiosDAO.SESSION.getTransaction() != null) {
				MunicipiosDAO.SESSION.getTransaction().rollback();
			}
			System.out.println("ERROR: CONSTRAINT VIOLATION");

		} catch (TransactionException e) {

			System.out.println("ERROR: TRANSACTION EXCEPTION");

		} catch (HibernateException e) {

			if (MunicipiosDAO.SESSION.getTransaction() != null) {
				MunicipiosDAO.SESSION.getTransaction().rollback();
			}
			System.out.println("EXCEPCION EN HIBERNATE SIN CONTROLAR");
			e.printStackTrace();
		} catch (Exception e) {
			if (MunicipiosDAO.SESSION.getTransaction() != null) {
				MunicipiosDAO.SESSION.getTransaction().rollback();
			}
			System.out.println("EXCEPCION SIN CONTROLAR");
			e.printStackTrace();
		} finally {
			HibernateUtil.shutdown();
		}      
	}

}
