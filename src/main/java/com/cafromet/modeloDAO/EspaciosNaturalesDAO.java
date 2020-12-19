package com.cafromet.modeloDAO;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cafromet.modelo.EspaciosNaturales;
import com.cafromet.util.HibernateUtil;

public class EspaciosNaturalesDAO {
	public static Session SESSION;
	private static String HQL;
	private static Query QUERY;
	
	public static void iniciarSesion() {
		System.out.println("\n\n ** CONECTADO A LA BBDD **\n"
		 		 + " -------------------------\n"); 
		SESSION = HibernateUtil.getSessionFactory().openSession();

	}
	public static void insertarRegistro(EspaciosNaturales espaciosNaturales) {
		SESSION.beginTransaction();		
		SESSION.save(espaciosNaturales);
		SESSION.getTransaction().commit();	 
	}
	
	public static EspaciosNaturales consultarRegistro(int id) {
		HQL = "from EspaciosNaturales where idEspacio = :id";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("id", id);
		EspaciosNaturales espaciosNat =  (EspaciosNaturales) QUERY.uniqueResult(); 
        System.out.printf(" REGISTRO(S) => %s || %d%n%n", espaciosNat.getNombre(), espaciosNat.getIdEspacio());
        return espaciosNat;
	}
	public static void borrarRegistro(int id) {
		SESSION.beginTransaction();	
		HQL = "from EspaciosNaturales where idEspacio = :id";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("id", id);
		
		EspaciosNaturales espaciosNat =  (EspaciosNaturales) QUERY.uniqueResult(); 

		SESSION.delete(espaciosNat);
		
		SESSION.getTransaction().commit();
		System.out.println("\n FILA(S) BORRADA(S)\n");
	}
}
