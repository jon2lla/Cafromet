package com.cafromet.modeloDAO;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cafromet.modelo.CentroMeteorologico;
import com.cafromet.util.HibernateUtil;

@SuppressWarnings("deprecation")
public class CentroMeteorologicoDAO {
	public static Session SESSION;
	private static String HQL;
	@SuppressWarnings("rawtypes")
	private static Query QUERY;
	
	public static void iniciarSesion() {
		System.out.println("\n\n ** CONECTADO A LA BBDD **\n"
		 		 + " -------------------------\n"); 
		SESSION = HibernateUtil.getSessionFactory().openSession();

	}
	
	public static void insertarRegistro(CentroMeteorologico centroMeteorologico) {
		SESSION.beginTransaction();		
		SESSION.save(centroMeteorologico);
		SESSION.getTransaction().commit();	 
	}
	
	public static CentroMeteorologico consultarRegistro(int id) {
		HQL = "from CentroMeteorologico where idCentroMet = :id";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("id", id);
		CentroMeteorologico centro =  (CentroMeteorologico) QUERY.uniqueResult(); 
        System.out.printf(" REGISTRO(S) => %s || %d%n%n", centro.getNombre(), centro.getIdCentroMet());
        return centro;
	}
	
	public static void borrarRegistro(int id) {
		SESSION.beginTransaction();	
		HQL = "from CentroMeteorologico where idCentroMet = :id";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("id", id);
		
		CentroMeteorologico centro =  (CentroMeteorologico) QUERY.uniqueResult(); 
		
		SESSION.delete(centro);	
		
		SESSION.getTransaction().commit();
		System.out.println("\n FILA(S) BORRADA(S)\n");
	}
}
