package com.cafromet.modeloDAO;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cafromet.modelo.CentrosMeteorologicos;
import com.cafromet.util.HibernateUtil;

public class CentrosMeteorologicosDAO {
	public static Session SESSION;
	private static String HQL;
	private static Query QUERY;
	
	public static void iniciarSesion() {
		System.out.println("\n\n ** CONECTADO A LA BBDD **\n"
		 		 + " -------------------------\n"); 
		SESSION = HibernateUtil.getSessionFactory().openSession();

	}
	
	public static void insertarRegistro(CentrosMeteorologicos centrosMeteorologicos) {
		SESSION.beginTransaction();		
		SESSION.save(centrosMeteorologicos);
		SESSION.getTransaction().commit();	 
	}
	
	public static CentrosMeteorologicos consultarRegistro(int id) {
		HQL = "from CentrosMeteorologicos where idCentroMet = :id";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("id", id);
		CentrosMeteorologicos centros =  (CentrosMeteorologicos) QUERY.uniqueResult(); 
        System.out.printf(" REGISTRO(S) => %s || %d%n%n", centros.getNombre(), centros.getIdCentroMet());
        return centros;
	}
	
	public static void borrarRegistro(int id) {
		SESSION.beginTransaction();	
		HQL = "from centrosMeteorologicos where idCentroMet = :id";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("id", id);
		
		CentrosMeteorologicos centros =  (CentrosMeteorologicos) QUERY.uniqueResult(); 
		
		SESSION.delete(centros);	
		
		SESSION.getTransaction().commit();
		System.out.println("\n FILA(S) BORRADA(S)\n");
	}
}
