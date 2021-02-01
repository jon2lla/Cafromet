package com.cafromet.modelodao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cafromet.modelo.CentroMeteorologico;
import com.cafromet.modelo.Municipio;
import com.cafromet.util.HibernateUtil;

@SuppressWarnings("deprecation")
public class CentroMeteorologicoDAO {
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
	
	public static boolean duplicado(CentroMeteorologico centroMeteorologico) {
		CentroMeteorologico registro = consultarRegistro(centroMeteorologico.getNombre());
		if(registro != null) {
			return true;
		}else if(centroMeteorologico.equals(registro)) {
			return true;
		}
		return false;
	}
	
	public synchronized static boolean insertarRegistro(CentroMeteorologico centroMeteorologico) {
		if(duplicado(centroMeteorologico)) {
			return false;
		}
		SESSION.beginTransaction();		
		SESSION.save(centroMeteorologico);
		SESSION.getTransaction().commit();	
		return true;
	}

	
	public static CentroMeteorologico consultarRegistro(String nombre) {
		HQL = "from CentroMeteorologico where nombre = :nombre";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("nombre", nombre);
		CentroMeteorologico centro =  (CentroMeteorologico) QUERY.uniqueResult(); 
        return centro;
	}
	
	public static List<CentroMeteorologico> consultarRegistros(int idMunicipio) {
		HQL = "from CentroMeteorologico where municipio.idMunicipio = :idMunicipio";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("idMunicipio", idMunicipio);
		List<CentroMeteorologico> centros = QUERY.list(); 
        return centros;
	}
	
	public static List<CentroMeteorologico> consultarRegistros() {
		HQL = "from CentroMeteorologico";
		QUERY = SESSION.createQuery(HQL);
		List<CentroMeteorologico> centros = QUERY.list(); 
        return centros;
	}
	
	public static boolean actualizarRegistro(CentroMeteorologico centroMeteorologico) {
		CentroMeteorologico registro = consultarRegistro(centroMeteorologico.getNombre()); 
		SESSION.beginTransaction();	
		if(registro!=null) {
			registro.setUrl(centroMeteorologico.getUrl());
			registro.setHash(centroMeteorologico.getHash());
			SESSION.update(registro);
			SESSION.getTransaction().commit();
			System.out.println("\n >> REGISTRO ACTUALIZADO");
			return true;
		}
		System.out.println("\n !ERROR AL ACTUALIZAR; CLASE => CENTROMETDAO");
		return false;
	}
	

	public static boolean borrarRegistro(String nombre) {
		SESSION.beginTransaction();	
		HQL = "from CentroMeteorologico where nombre = :nombre";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("nombre", nombre);	
		CentroMeteorologico centro =  (CentroMeteorologico) QUERY.uniqueResult(); 		
		SESSION.delete(centro);			
		SESSION.getTransaction().commit();
		System.out.println("\n >> REGISTRO BORRADO");
		return true;
	}
}
