package com.cafromet.modeloDAO;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cafromet.modelo.Medicion;
import com.cafromet.util.HibernateUtil;

public class MedicionDAO {
	
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
	
	public static boolean duplicado(Medicion medicion) {
		Medicion registro = consultarRegistro(medicion.getId().getIdCentroMet());
		if(registro != null) {
			return true;
		}else if(medicion.equals(registro)) {
			return true;
		}
		return false;
	}
	
	public static boolean insertarRegistro(Medicion medicion) {
		if(duplicado(medicion)) {
			return false;
		}
		SESSION.beginTransaction();		
		SESSION.save(medicion);
		SESSION.getTransaction().commit();	
		return true;
	}
	
	
	public static Medicion consultarRegistro(int idCentro) {
		HQL = "from Medicion where idCentro = :idCentro";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("idCentro", idCentro);
		Medicion medicion =  (Medicion) QUERY.uniqueResult(); 
        return medicion;
	}
	
	public static Medicion consultarRegistro(int idCentro, Date fecha, Date hora) {
		HQL = "from Medicion where id = :idCentro and fecha = :fecha and hora = :hora";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("idCentro", idCentro);
		QUERY.setParameter("fecha", fecha);
		QUERY.setParameter("hora", hora);
		Medicion medicion =  (Medicion) QUERY.uniqueResult(); 
        return medicion;
	}
	
	public static boolean borrarRegistro(int id) {
		SESSION.beginTransaction();	
		HQL = "from CentroMeteorologico where idCentroMet = :id";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("id", id);
		
		Medicion medicion =  (Medicion) QUERY.uniqueResult(); 
		
		SESSION.delete(medicion);	
		
		SESSION.getTransaction().commit();
		System.out.println("\n FILA(S) BORRADA(S)\n");
		return true;
	}

}
