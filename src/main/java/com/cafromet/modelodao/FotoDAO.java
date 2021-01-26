package com.cafromet.modelodao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cafromet.modelo.Cliente;
import com.cafromet.modelo.Fotos;
import com.cafromet.modelo.Medicion;
import com.cafromet.util.HibernateUtil;

public class FotoDAO {
	public static Session SESSION;
	private static String HQL;
	@SuppressWarnings("rawtypes")
	private static Query QUERY;
	
	public static void iniciarSesion() {SESSION = HibernateUtil.getSessionFactory().openSession();}
	
	public static void cerrarSesion() {SESSION.close();}

	public static boolean duplicado(Fotos foto) {
		Fotos registro = consultarRegistro(foto.getIdFoto());
		if (registro != null) {
			return true;
		} else if (foto.equals(registro)) {
			return true;
		}
		return false;
	}		
	
	public static boolean insertarRegistro(Fotos foto) {
		if (duplicado(foto)) {
			return false;
		}
		SESSION.beginTransaction();
		SESSION.save(foto);
		SESSION.getTransaction().commit();
		return true;
	}
	
	public static Fotos consultarRegistro(String idFoto) {
		HQL = "from Fotos where idFoto = :idFoto";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("idFoto", idFoto);
		Fotos registro = (Fotos) QUERY.uniqueResult(); 
		if (registro == null){
			return null;
		}
//        System.out.printf(" REGISTRO(S) => %s || %d%n%n", cliente.getUsuario(), cliente.getIdCliente());
        return registro;
	}
	
	public static List<Fotos> consultarRegistros(int idCliente, int idEspacio) {
		HQL = "from Fotos where cliente.idCliente = :idCliente and espacioNatural.idEspacio = :idEspacio";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("idCliente", idCliente);
		QUERY.setParameter("idEspacio", idEspacio);
		List<Fotos> fotos = QUERY.list(); 
        return fotos;
	}
	
	public static boolean borrarRegistro(Fotos foto) {
		SESSION.beginTransaction();	
		HQL = "from Fotos where idFoto = :idFoto";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("idFoto", foto.getIdFoto());	
		Fotos registro = (Fotos) QUERY.uniqueResult(); 		
		SESSION.delete(registro);		
		SESSION.getTransaction().commit();
		System.out.println("\n >> REGISTRO BORRADO");
		return true;
	}
}
