package com.cafromet.modeloDAO;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cafromet.modelo.Provincias;
import com.cafromet.util.HibernateUtil;

public class ProvinciasDAO {
	public static Session SESSION;
	private static String HQL;
	private static Query QUERY;
	
	public static void iniciarSesion() {
		System.out.println("\n\n ** CONECTADO A LA BBDD **\n"
		 		 + " -------------------------\n"); 
		SESSION = HibernateUtil.getSessionFactory().openSession();

	}
	public static void insertarRegistro(Provincias provincias) {
		SESSION.beginTransaction();		
		SESSION.save(provincias);
		SESSION.getTransaction().commit();	 
	}
		
	public static Provincias consultarRegistro(int id) {
		HQL = "from Provincias  where idProvincia = :id";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("id", id);
		Provincias provincias = (Provincias) QUERY.uniqueResult(); 
        System.out.printf(" REGISTRO(S) => %s || %d%n%n", provincias.getNombre(), provincias.getIdProvincia());
        return provincias;
	}
	
	public static void borrarRegistro(int id) {
		SESSION.beginTransaction();	
		HQL = "from Provincias  where idProvincia = :id";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("id", id);
		
		Provincias provincias = (Provincias) QUERY.uniqueResult(); 

		SESSION.delete(provincias);
		
		SESSION.getTransaction().commit();
		System.out.println("\n FILA(S) BORRADA(S)\n");
	}
}
