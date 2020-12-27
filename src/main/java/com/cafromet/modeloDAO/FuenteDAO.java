package com.cafromet.modeloDAO;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cafromet.modelo.Fuente;
import com.cafromet.util.HibernateUtil;

@SuppressWarnings("deprecation")
public class FuenteDAO {
	public static Session SESSION;
	private static String HQL;
	@SuppressWarnings("rawtypes")
	private static Query QUERY;
	
	public static void iniciarSesion() {
		System.out.println("\n\n ** CONECTADO A LA BBDD **\n"
		 		 + " -------------------------\n"); 
		SESSION = HibernateUtil.getSessionFactory().openSession();

	}
	
	public static void insertarRegistro(Fuente fuentes) {
		SESSION.beginTransaction();		
		SESSION.save(fuentes);
		SESSION.getTransaction().commit();	 
	}
	
	public static Fuente consultarRegistro(int id) {
		HQL = "from Fuente where id = :id";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("id", id);
		Fuente fuente = (Fuente) QUERY.uniqueResult(); 
        System.out.printf(" REGISTRO(S) => %s || %d%n%n", fuente.getNombre(), fuente.getId());
        return fuente;
	}
	
	public static void borrarRegistro(int id) {
		SESSION.beginTransaction();	
		HQL = "from Fuente where id = :id";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("id", id);
		
		Fuente fuente = (Fuente) QUERY.uniqueResult(); 

		SESSION.delete(fuente);
		
		SESSION.getTransaction().commit();
		System.out.println("\n FILA(S) BORRADA(S)\n");
	}
}
