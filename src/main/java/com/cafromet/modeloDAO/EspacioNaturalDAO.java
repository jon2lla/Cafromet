package com.cafromet.modeloDAO;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cafromet.modelo.EspacioNatural;
import com.cafromet.util.HibernateUtil;

@SuppressWarnings("deprecation")
public class EspacioNaturalDAO {
	public static Session SESSION;
	private static String HQL;
	@SuppressWarnings("rawtypes")
	private static Query QUERY;
	
	public static void iniciarSesion() {
		System.out.println("\n\n ** CONECTADO A LA BBDD **\n"
		 		 + " -------------------------\n"); 
		SESSION = HibernateUtil.getSessionFactory().openSession();

	}
	public static void insertarRegistro(EspacioNatural espacioNatural) {
		SESSION.beginTransaction();		
		SESSION.save(espacioNatural);
		SESSION.getTransaction().commit();	 
	}
	
	public static EspacioNatural consultarRegistro(int id) {
		HQL = "from EspacioNatural where idEspacio = :id";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("id", id);
		EspacioNatural espacioNat =  (EspacioNatural) QUERY.uniqueResult(); 
        System.out.printf(" REGISTRO(S) => %s || %d%n%n", espacioNat.getNombre(), espacioNat.getIdEspacio());
        return espacioNat;
	}
	public static void borrarRegistro(int id) {
		SESSION.beginTransaction();	
		HQL = "from EspacioNatural where idEspacio = :id";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("id", id);
		
		EspacioNatural espacioNat =  (EspacioNatural) QUERY.uniqueResult(); 

		SESSION.delete(espacioNat);
		
		SESSION.getTransaction().commit();
		System.out.println("\n FILA(S) BORRADA(S)\n");
	}
}
