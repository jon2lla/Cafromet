package com.cafromet.modeloDAO;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cafromet.modelo.Municipios;
import com.cafromet.util.HibernateUtil;

public class MunicipiosDAO {
	public static Session SESSION;
	private static String HQL;
	private static Query QUERY;
	
	public static void iniciarSesion() {
		System.out.println("\n\n ** CONECTADO A LA BBDD **\n"
		 		 + " -------------------------\n"); 
		SESSION = HibernateUtil.getSessionFactory().openSession();

	}
	public static void insertarRegistro(Municipios municipio) {
		SESSION.beginTransaction();		
		SESSION.save(municipio);
		SESSION.getTransaction().commit();	 
	}
		
	public static Municipios consultarRegistro(int id) {
		HQL = "from Municipios as mun where mun.idMunicipio = :id";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("id", id);
		Municipios municipio = (Municipios) QUERY.uniqueResult(); 
        System.out.printf(" REGISTRO(S) => %s || %d%n%n", municipio.getNombre(), municipio.getIdMunicipio());
        return municipio;
	}
	
	public static void borrarRegistro(int id) {
		SESSION.beginTransaction();
		
		HQL = "from Municipios as mun where mun.idMunicipio = :id";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("id", id);
		Municipios municipio = (Municipios) QUERY.uniqueResult(); 

		SESSION.delete(municipio);
		
		SESSION.getTransaction().commit();
		System.out.println("\n FILA(S) BORRADA(S)\n");
	}
}
