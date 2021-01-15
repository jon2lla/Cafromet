package com.cafromet.modelodao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cafromet.modelo.Municipio_EspacioNatural;
import com.cafromet.modelo.Municipio_EspacioNaturalId;
import com.cafromet.util.HibernateUtil;

@SuppressWarnings("deprecation")
public class Municipio_EspacioNatDAO{
	public static Session SESSION;
	private static String HQL;
	@SuppressWarnings("rawtypes")
	private static Query QUERY;
	
	public static void iniciarSesion() {SESSION = HibernateUtil.getSessionFactory().openSession();}
	
	public static void cerrarSesion() {SESSION.close();}
	
	public static boolean duplicado(Municipio_EspacioNatural municipio_EspacionNatural) {
		if(consultarRegistro(municipio_EspacionNatural.getId()) != null) {
			return true;
		}
		return false;
	}
	

	
	public synchronized static boolean insertarRegistro(Municipio_EspacioNatural municipio_EspacionNatural) {
		if(duplicado(municipio_EspacionNatural)) {
			return false;
		}
		iniciarSesion();
		SESSION.beginTransaction();		
		SESSION.save(municipio_EspacionNatural);
		SESSION.getTransaction().commit();	 
		cerrarSesion();
		return true;
	}
	
	public static Municipio_EspacioNatural consultarRegistro(Municipio_EspacioNaturalId id) {
		HQL = "from Municipio_EspacioNatural as mun_es where mun_es.id = :id";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("id", id);
		Municipio_EspacioNatural municipio_EspacionNatural =  (Municipio_EspacioNatural) QUERY.uniqueResult(); 
        System.out.printf(" REGISTRO(S) => %s || %s%n%n", municipio_EspacionNatural.getMunicipio().getNombre(), municipio_EspacionNatural.getEspacioNatural().getNombre());
        return municipio_EspacionNatural;
	}
	
//	public static void borrarRegistro(Municipio_EspacioNaturalId id) {
//		SESSION.beginTransaction();	
//		HQL = "from Municipio_EspacioNatural as mun_es where mun_es.id = :id";
//		QUERY = SESSION.createQuery(HQL);
//		QUERY.setParameter("id", id);
//		
//		Municipio_EspacioNatural municipio_EspacionNatural =  (Municipio_EspacioNatural) QUERY.uniqueResult(); 
//		
//		SESSION.delete(municipio_EspacionNatural);	
//		
//		SESSION.getTransaction().commit();
//		System.out.println("\n FILA(S) BORRADA(S)\n");
//	}
}
