package com.cafromet.modelodao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cafromet.modelo.Municipio;
import com.cafromet.modelo.Provincia;
import com.cafromet.util.HibernateUtil;

@SuppressWarnings("deprecation")
public class MunicipioDAO {
	public static Session SESSION;
	private static String HQL;
	@SuppressWarnings("rawtypes")
	private static Query QUERY;
	
	public synchronized static void iniciarSesion() {SESSION = HibernateUtil.getSessionFactory().openSession();}
	
	public synchronized static void cerrarSesion() {SESSION.close();}
	
	public synchronized static boolean duplicado(Municipio municipio) {
		Municipio registro = consultarRegistro(municipio.getNombre());
		if(registro != null) {
			return true;
		}else if(municipio.equals(registro)) {
			return true;
		}
		return false;
	}
	
	public synchronized static boolean insertarRegistro(Municipio municipio) {
		if(duplicado(municipio)) {
			return false;
		}
		SESSION.beginTransaction();		
		SESSION.save(municipio);
		SESSION.getTransaction().commit();
		return true;
	}
		
//	public static Municipio consultarRegistro(int id) {
//		HQL = "from Municipio as mun where mun.idMunicipio = :id";
//		QUERY = SESSION.createQuery(HQL);
//		QUERY.setParameter("id", id);
//		Municipio municipio = (Municipio) QUERY.uniqueResult(); 
//        return municipio;
//	}
	
	public synchronized static Municipio consultarRegistro(String nombre) {
		HQL = "from Municipio as mun where mun.nombre = :nombre";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("nombre", nombre);
		Municipio municipio = (Municipio) QUERY.uniqueResult(); 
		return municipio;
	}
	
	public synchronized static Municipio consultarRegistro(int codMunicipio, int idProvincia) {
		HQL = "from Municipio as mun where mun.codMunicipio = :codMunicipio and mun.provincia.idProvincia = :idProvincia";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("codMunicipio", codMunicipio);
		QUERY.setParameter("idProvincia", idProvincia);
		Municipio municipio = (Municipio) QUERY.uniqueResult(); 
		return municipio;		
	}
	
//	public static Municipio consultarRegistro(String nombre, String nombreProvincia) {
//		System.out.println("N: " + nombre + ", P: " + nombreProvincia);
//		HQL = "from Municipio as mun where mun.nombre = :nombre and mun.provincia.nombre = :nombreProvincia";
//		QUERY = SESSION.createQuery(HQL);
//		QUERY.setParameter("nombre", nombre);
//		QUERY.setParameter("nombreProvincia", nombreProvincia);
//		Municipio municipio = (Municipio) QUERY.uniqueResult(); 
//		System.out.println(municipio.getNombre());
//		return municipio;
//	}
	
	public synchronized static List<Municipio> consultarRegistros() {
        SESSION.beginTransaction();
        String hql = "from Municipio";
        Query q = SESSION.createQuery(hql);
        List<Municipio> filasMuni = q.list();
        return filasMuni;
    }

	
//	public static boolean borrarRegistro(int id) {
//		boolean correcto=false;
//		SESSION.beginTransaction();	
//		HQL = "from Municipio as mun where mun.idMunicipio = :id";
//		QUERY = SESSION.createQuery(HQL);
//		QUERY.setParameter("id", id);
//		
//		Municipio municipio = (Municipio) QUERY.uniqueResult(); 
//
//		SESSION.delete(municipio);
//		
//		SESSION.getTransaction().commit();
//		System.out.println("\n FILA(S) BORRADA(S)\n");
//		correcto=true;
//		return correcto;
//	}
	public synchronized static boolean borrarRegistro(String nombre) {
		SESSION.beginTransaction();	
		HQL = "from Municipio as mun where mun.nombre = :nombre";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("nombre", nombre);	
		Municipio municipio = (Municipio) QUERY.uniqueResult(); 
		SESSION.delete(municipio);		
		SESSION.getTransaction().commit();
		System.out.println("\n FILA(S) BORRADA(S)\n");
		return true;
	}
}
