package com.cafromet.modeloDAO;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cafromet.modelo.Fuentes;
import com.cafromet.util.HibernateUtil;

public class FuentesDAO {
	public static Session SESSION;
	private static String HQL;
	private static Query QUERY;
	
	public static void iniciarSesion() {
		System.out.println("\n\n ** CONECTADO A LA BBDD **\n"
		 		 + " -------------------------\n"); 
		SESSION = HibernateUtil.getSessionFactory().openSession();

	}
	
	public static void insertarRegistro(Fuentes fuentes) {
		SESSION.beginTransaction();		
		SESSION.save(fuentes);
		SESSION.getTransaction().commit();	 
	}
	
	public static Fuentes consultarRegistro(int id) {
		HQL = "from Fuentes where idIndex = :id";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("id", id);
		Fuentes fuente = (Fuentes) QUERY.uniqueResult(); 
        System.out.printf(" REGISTRO(S) => %s || %d%n%n", fuente.getNombre(), fuente.getIdIndex());
        return fuente;
	}
	
	public static void borrarRegistro(int id) {
		SESSION.beginTransaction();	
		HQL = "from Fuentes where idIndex = :id";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("id", id);
		
		Fuentes fuente = (Fuentes) QUERY.uniqueResult(); 

		SESSION.delete(fuente);
		
		SESSION.getTransaction().commit();
		System.out.println("\n FILA(S) BORRADA(S)\n");
	}
}
