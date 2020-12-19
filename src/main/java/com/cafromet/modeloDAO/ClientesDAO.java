package com.cafromet.modeloDAO;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cafromet.modelo.Clientes;
import com.cafromet.util.HibernateUtil;

public class ClientesDAO {
	public static Session SESSION;
	private static String HQL;
	private static Query QUERY;
	
	public static void iniciarSesion() {
		System.out.println("\n\n ** CONECTADO A LA BBDD **\n"
		 		 + " -------------------------\n"); 
		SESSION = HibernateUtil.getSessionFactory().openSession();

	}
	
	public static void insertarRegistro(Clientes clientes) {
		SESSION.beginTransaction();		
		SESSION.save(clientes);
		SESSION.getTransaction().commit();	 
	}
	
	public static Clientes consultarRegistro(int id) {
		HQL = "from Clientes where idCliente = :id";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("id", id);
		Clientes clientes= (Clientes) QUERY.uniqueResult(); 
        System.out.printf(" REGISTRO(S) => %s || %d%n%n", clientes.getUsuario(), clientes.getIdCliente());
        return clientes;
	}
	
	public static void borrarRegistro(int id) {
		SESSION.beginTransaction();	
		HQL = "from Clientes where idCliente = :id";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("id", id);
		
		Clientes clientes= (Clientes) QUERY.uniqueResult(); 
		
		SESSION.delete(clientes);
		
		SESSION.getTransaction().commit();
		System.out.println("\n FILA(S) BORRADA(S)\n");
	}
	
}
