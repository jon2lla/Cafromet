package com.cafromet.modelodao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cafromet.modelo.Cliente;
import com.cafromet.modelo.Municipio;
import com.cafromet.util.HibernateUtil;

@SuppressWarnings("deprecation")
public class ClienteDAO {
	public static Session SESSION;
	private static String HQL;
	@SuppressWarnings("rawtypes")
	private static Query QUERY;
	
	public static void iniciarSesion() {SESSION = HibernateUtil.getSessionFactory().openSession();}
	
	public static void cerrarSesion() {SESSION.close();}

	public static boolean duplicado(Cliente cliente) {
		Cliente registro = consultarRegistro(cliente.getUsuario());
		if (registro != null) {
			return true;
		} else if (cliente.equals(registro)) {
			return true;
		}
		return false;
	}

	public static boolean insertarRegistro(Cliente cliente) {
		if (duplicado(cliente)) {
			return false;
		}
		SESSION.beginTransaction();
		SESSION.save(cliente);
		SESSION.getTransaction().commit();
		return true;
	}
	
	public static Cliente consultarRegistro(String usuario) {
		HQL = "from Cliente where usuario = :usuario";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("usuario", usuario);
		Cliente cliente = (Cliente) QUERY.uniqueResult(); 
		if (cliente == null){
			return null;
		}
//        System.out.printf(" REGISTRO(S) => %s || %d%n%n", cliente.getUsuario(), cliente.getIdCliente());
        return cliente;
	}
	
	public static boolean borrarRegistro(String usuario) {
		SESSION.beginTransaction();	
		HQL = "from Cliente where usuario = :usuario";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("usuario", usuario);	
		Cliente cliente = (Cliente) QUERY.uniqueResult(); 		
		SESSION.delete(cliente);		
		SESSION.getTransaction().commit();
		System.out.println("\n >> REGISTRO BORRADO\n");
		return true;
	}
}
