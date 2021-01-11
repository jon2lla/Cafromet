package com.cafromet.modeloDAO;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cafromet.modelo.CentroMeteorologico;
import com.cafromet.modelo.Municipio;
import com.cafromet.util.HibernateUtil;

@SuppressWarnings("deprecation")
public class CentroMeteorologicoDAO {
	public static Session SESSION;
	private static String HQL;
	@SuppressWarnings("rawtypes")
	private static Query QUERY;
	
	public static void iniciarSesion() {
		SESSION = HibernateUtil.getSessionFactory().openSession();

	}
	
	public static void cerrarSesion() {
		SESSION.close();
	}
	
	public static boolean duplicado(CentroMeteorologico centroMeteorologico) {
		CentroMeteorologico registro = consultarRegistro(centroMeteorologico.getNombre());
		if(registro != null) {
			return true;
		}else if(centroMeteorologico.equals(registro)) {
			return true;
		}
		return false;
	}
	
	public static boolean insertarRegistro(CentroMeteorologico centroMeteorologico) {
		if(duplicado(centroMeteorologico)) {
			return false;
		}
		SESSION.beginTransaction();		
		SESSION.save(centroMeteorologico);
		SESSION.getTransaction().commit();	
		return true;
	}
	
	
	public static CentroMeteorologico consultarRegistro(int id) {
		HQL = "from CentroMeteorologico where idCentroMet = :id";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("id", id);
		CentroMeteorologico centro =  (CentroMeteorologico) QUERY.uniqueResult(); 
        return centro;
	}
	
	public static CentroMeteorologico consultarRegistro(String nombre) {
		HQL = "from CentroMeteorologico where nombre = :nombre";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("nombre", nombre);
		CentroMeteorologico centro =  (CentroMeteorologico) QUERY.uniqueResult(); 
        return centro;
	}
	
	public static void borrarRegistro(int id) {
		SESSION.beginTransaction();	
		HQL = "from CentroMeteorologico where idCentroMet = :id";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("id", id);
		
		CentroMeteorologico centro =  (CentroMeteorologico) QUERY.uniqueResult(); 
		
		SESSION.delete(centro);	
		
		SESSION.getTransaction().commit();
		System.out.println("\n FILA(S) BORRADA(S)\n");
	}
}
