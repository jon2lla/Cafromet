package com.cafromet.modelodao;

import org.hibernate.Query;
import org.hibernate.Session;

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
		Fuente registro = consultarRegistroPorNombre(fuente.getNombre());
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
	
	public static Fuente consultarRegistroPorNombre(String nombre) {
		HQL = "from Fuente where nombre = :nombre";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("nombre", nombre);
		Fuente fuente = (Fuente) QUERY.uniqueResult(); 
        return fuente;
	}
	
	public static boolean borrarRegistro(String nombre) {
		SESSION.beginTransaction();	
		HQL = "from Fuente where nombre = :nombre";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("nombre", nombre);	
		Fuente fuente = (Fuente) QUERY.uniqueResult(); 
		SESSION.delete(fuente);
		SESSION.getTransaction().commit();
		System.out.println("\n FILA(S) BORRADA(S)\n");
		return true;
	}
}
