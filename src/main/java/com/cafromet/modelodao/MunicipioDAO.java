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
	
	public static void iniciarSesion() {
		SESSION = HibernateUtil.getSessionFactory().openSession();

	}
	
	public static void cerrarSesion() {
		SESSION.close();
	}
	
	public static boolean duplicado(Municipio municipio) {
		Municipio registro = consultarRegistro(municipio.getNombre());
		if(registro != null) {
			return true;
		}else if(municipio.equals(registro)) {
			return true;
		}
		return false;
	}
	
	public static boolean insertarRegistro(Municipio municipio) {
		if(duplicado(municipio)) {
			return false;
		}
		SESSION.beginTransaction();		
		SESSION.save(municipio);
		SESSION.getTransaction().commit();	
		return true;
	}
	
	public static Municipio consultarRegistro(String nombre) {
		HQL = "from Municipio as mun where mun.nombre = :nombre";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("nombre", nombre);
		Municipio municipio = (Municipio) QUERY.uniqueResult(); 
		return municipio;
	}
	
	public static Municipio consultarRegistro(int codMunicipio, int idProvincia) {
		HQL = "from Municipio as mun where mun.codMunicipio = :codMunicipio and mun.provincia.idProvincia = :idProvincia";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("codMunicipio", codMunicipio);
		QUERY.setParameter("idProvincia", idProvincia);
		Municipio municipio = (Municipio) QUERY.uniqueResult(); 
		return municipio;		
	}
	
	public static Municipio consultarRegistro(String nomMunicipio, int idProvincia) {
		HQL = "from Municipio as mun where mun.nombre = :nomMunicipio and mun.provincia.idProvincia = :idProvincia";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("nomMunicipio", nomMunicipio);
		QUERY.setParameter("idProvincia", idProvincia);
		Municipio municipio = (Municipio) QUERY.uniqueResult(); 
		return municipio;		
	}
	
	public static List<Municipio> consultarRegistros() {
        String hql = "from Municipio";
        QUERY = SESSION.createQuery(hql);
        List<Municipio> filasMuni = QUERY.list();

        return filasMuni;
    }

	public static boolean borrarRegistro(String nombre) {
		boolean correcto = false;
		SESSION.beginTransaction();	
		HQL = "from Municipio as mun where mun.nombre = :nombre";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("nombre", nombre);	
		Municipio municipio = (Municipio) QUERY.uniqueResult(); 
		SESSION.delete(municipio);		
		SESSION.getTransaction().commit();
		System.out.println("\n >> REGISTRO BORRADO");
		correcto = true;
		return correcto;
	}
	public static Municipio consultarMuni(int idMunicipio) {
		HQL = "from Municipio as mun where mun.idMunicipio = :idMunicipio ";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("idMunicipio", idMunicipio);
		Municipio municipio = (Municipio) QUERY.uniqueResult(); 
		return municipio;		
	}
}
