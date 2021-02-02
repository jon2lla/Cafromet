package com.cafromet.modelodao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cafromet.modelo.EspacioNatural;
import com.cafromet.modelo.Medicion;
import com.cafromet.modelo.MedicionId;
import com.cafromet.util.HibernateUtil;

public class MedicionDAO {
	
	public static Session SESSION;
	private static String HQL;
	@SuppressWarnings("rawtypes")
	private static Query QUERY;
	
	public static void iniciarSesion() {SESSION = HibernateUtil.getSessionFactory().openSession();}
	
	public static void cerrarSesion() {SESSION.close();}
	
	public synchronized static boolean duplicado(Medicion medicion) {
		Medicion registro = null;
		registro = consultarRegistro(medicion);

		if(registro != null) {
			return true;
		} else if(medicion.equals(registro)) {
			return true;
		}
		return false;
	}
	
	public static synchronized boolean insertarRegistro(Medicion medicion) {
		iniciarSesion();
		if(duplicado(medicion)) {
			return false;
		}

		SESSION.beginTransaction();		
		SESSION.save(medicion);
		SESSION.getTransaction().commit();	
		cerrarSesion();
		return true;
	}
	
	
	public static Medicion consultarRegistro(Medicion medicion) {
		HQL = "from Medicion where id.idCentroMet = :idCentro and id.fecha = :fecha and id.hora = :hora";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("idCentro", medicion.getId().getIdCentroMet());
		QUERY.setParameter("fecha", medicion.getId().getFecha());
		QUERY.setParameter("hora", medicion.getId().getHora());
		Medicion medicion2 =  (Medicion) QUERY.uniqueResult(); 
        return medicion2;
	}
	
	public static List<Medicion> consultarRegistros(int idCentro) {
		HQL = "from Medicion where centroMeteorologico.idCentroMet = :idCentro";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("idCentro", idCentro);
		List<Medicion> mediciones = QUERY.list(); 
        return mediciones;
	}
	
	public static List<Medicion> consultarRegistros() {
		HQL = "from Medicion";
		QUERY = SESSION.createQuery(HQL);
		List<Medicion> mediciones = QUERY.list(); 
        return mediciones;
	}
	
	public static List<Medicion> consultarTop() {
//		HQL = "SELECT max(precip) FROM Medicion "
//				+ "WHERE Medicion.MedicionId.idCentroMet = CentroMeteorologico.idCentroMet "
//				+ "and CentroMeteorologico.municipio.idMunicipio = CentroMeteorologico.municipio.idMunicipio "
//				+ "having max(precip) > 1 ";		
		HQL="SELECT DISTINCT muni.NOMBRE,med.ID_CENTRO_MET,MAX(med.RAD_SOLAR) "
				+ "FROM mediciones_centro_met as med "
				+ "Inner join centros_meteorologicos as cen "
				+ "ON cen.ID_CENTRO_MET=med.ID_CENTRO_MET "
				+ "INNER JOIN municipios as muni "
				+ "ON muni.ID_MUNICIPIO = cen.ID_MUNICIPIO "
				+ "GROUP By ID_CENTRO_MET HAVING MAX(RAD_SOLAR) >1 LIMIT 4";
		
//		QUERY = SESSION.createQuery(HQL);
		QUERY = SESSION.createSQLQuery(HQL);
		List<Medicion> mediciones = QUERY.list(); 
		
        return mediciones;
	}
	
	public static List<Medicion> consultarRegistroPorIdEspacio(int idCentroMet) {
		
		HQL = "from Medicion as medi where medi.centroMeteorologico.idCentroMet = :idCentroMet";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("idCentroMet", idCentroMet);
		List<Medicion> medi = QUERY.list(); 
	
        return medi;
	}
	
}
