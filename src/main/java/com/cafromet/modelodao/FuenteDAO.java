package com.cafromet.modelodao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cafromet.modelo.CentroMeteorologico;
import com.cafromet.modelo.Fuente;
import com.cafromet.modelo.Municipio;
import com.cafromet.util.HibernateUtil;

@SuppressWarnings("deprecation")
public class FuenteDAO {
	public static Session SESSION;
	private static String HQL;
	@SuppressWarnings("rawtypes")
	private static Query QUERY;
	
	public static void iniciarSesion() {
		SESSION = HibernateUtil.getSessionFactory().openSession();
	}
	
	public static void cerrarSesion() {
		SESSION.close();
	}
	
	public static boolean duplicado(Fuente fuente) {
		Fuente registro = consultarRegistro(fuente.getNombre());
		if(registro != null) {
			return true;
		}else if(fuente.equals(registro)) {
			return true;
		}
		return false;
	}
	
	public static boolean insertarRegistro(Fuente fuente) {
		if(duplicado(fuente)) {
			return false;
		}
		SESSION.beginTransaction();		
		SESSION.save(fuente);
		SESSION.getTransaction().commit();	
		return true;
	}
	
	public static Fuente consultarRegistro(String nombre) {
		HQL = "from Fuente where nombre = :nombre";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("nombre", nombre);
		Fuente fuente = (Fuente) QUERY.uniqueResult(); 
        return fuente;
	}
	
	public static boolean actualizarRegistro(Fuente fuente) {
		Fuente registro = consultarRegistro(fuente.getNombre()); 
		SESSION.beginTransaction();	
		if(registro!=null) {
			registro.setUrl(fuente.getUrl());
			registro.setHash(fuente.getHash());
			SESSION.update(registro);
			SESSION.getTransaction().commit();
			System.out.println("\n >> REGISTRO ACTUALIZADO\n");
			return true;
		}
		System.out.println("\n !ERROR AL ACTUALIZAR; CLASE => FUENTEDAO\n");
		return false;
	}
	
	public static boolean borrarRegistro(String nombre) {
		SESSION.beginTransaction();	
		HQL = "from Fuente where nombre = :nombre";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("nombre", nombre);	
		Fuente fuente = (Fuente) QUERY.uniqueResult(); 
		SESSION.delete(fuente);
		SESSION.getTransaction().commit();
		System.out.println("\n >> REGISTRO BORRADO\n");
		return true;
	}
}
