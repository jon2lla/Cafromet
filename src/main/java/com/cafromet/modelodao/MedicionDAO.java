package com.cafromet.modelodao;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cafromet.modelo.Medicion;
import com.cafromet.modelo.MedicionId;
import com.cafromet.util.HibernateUtil;

public class MedicionDAO {
	
	public static Session SESSION;
	private static String HQL;
	@SuppressWarnings("rawtypes")
	private static Query QUERY;
	
	public static void iniciarSesion() {SESSION = HibernateUtil.getSessionFactory().openSession();}
	
	public static void cerrarSesion() {SESSION.close();}
	
	public static boolean duplicado(Medicion medicion) {
		Medicion registro = null;
		registro = consultarRegistro(medicion);

		if(registro != null) {
			return true;
		} else if(medicion.equals(registro)) {
			return true;
		}
		return false;
	}
	
	public static synchronized boolean insertarRegistro(Medicion medicion) {
		iniciarSesion();
		if(duplicado(medicion)) {
			return false;
		}
		SESSION.beginTransaction();		
		SESSION.save(medicion);
		SESSION.getTransaction().commit();	
		cerrarSesion();
		return true;
	}
	
	
	public static Medicion consultarRegistro(Medicion medicion) {
		HQL = "from Medicion where id.idCentroMet = :idCentro and id.fecha = :fecha and id.hora = :hora";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("idCentro", medicion.getId().getIdCentroMet());
		QUERY.setParameter("fecha", medicion.getId().getFecha());
		QUERY.setParameter("hora", medicion.getId().getHora());
		Medicion medicion2 =  (Medicion) QUERY.uniqueResult(); 
        return medicion2;
	}
	

	
	public static boolean borrarRegistro(MedicionId id) {
		iniciarSesion();
		SESSION.beginTransaction();	
		HQL = "from CentroMeteorologico where idCentroMet = :id";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("id", id);	
		Medicion medicion =  (Medicion) QUERY.uniqueResult(); 
		SESSION.delete(medicion);	
		SESSION.getTransaction().commit();
		cerrarSesion();
		System.out.println("\n FILA(S) BORRADA(S)\n");
		return true;
	}
}
