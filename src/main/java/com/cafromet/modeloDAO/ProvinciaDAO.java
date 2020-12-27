package com.cafromet.modeloDAO;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cafromet.modelo.Provincia;
import com.cafromet.util.HibernateUtil;

@SuppressWarnings("deprecation")
public class ProvinciaDAO {
	public static Session SESSION;
	private static String HQL;
	@SuppressWarnings("rawtypes")
	private static Query QUERY;
	
	public static void iniciarSesion() {
		System.out.println("\n\n ** CONECTADO A LA BBDD **\n"
		 		 + " -------------------------\n"); 
		SESSION = HibernateUtil.getSessionFactory().openSession();

	}
	public static void insertarRegistro(Provincia provincias) {
		SESSION.beginTransaction();		
		SESSION.save(provincias);
		SESSION.getTransaction().commit();	 
	}
		
	public static Provincia consultarRegistro(int id) {
		HQL = "from Provincia  where idProvincia = :id";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("id", id);
		Provincia provincia = (Provincia) QUERY.uniqueResult(); 
        System.out.printf(" REGISTRO(S) => %s || %d%n%n", provincia.getNombre(), provincia.getIdProvincia());
        return provincia;
	}
	
	public static void borrarRegistro(int id) {
		SESSION.beginTransaction();	
		HQL = "from Provincia  where idProvincia = :id";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("id", id);
		
		Provincia provincia = (Provincia) QUERY.uniqueResult(); 

		SESSION.delete(provincia);
		
		SESSION.getTransaction().commit();
		System.out.println("\n FILA(S) BORRADA(S)\n");
	}
}
