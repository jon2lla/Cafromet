package com.cafromet.modelodao;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cafromet.modelo.EspacioNatural;
import com.cafromet.modelo.Medicion;
import com.cafromet.modelo.MedicionId;
import com.cafromet.modelo.Municipio;
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
	
	public synchronized static LinkedHashMap<Municipio, Medicion> consultarTopMunicipios() {
		iniciarSesion();
		MunicipioDAO.iniciarSesion();
		HQL="from Medicion order by tempAmbiente desc";
		
		QUERY = SESSION.createQuery(HQL);
		List<Medicion> mediciones = QUERY.list(); 
		
		LinkedHashMap<Municipio, Medicion> mapaMediciones = new LinkedHashMap<Municipio, Medicion>();
		mapaMediciones.clear();

		int contador = 0; 
		Medicion medicion;
		while(mapaMediciones.size() < 5) {
			medicion = new Medicion();
			medicion = mediciones.get(contador);
			if(!mapaMediciones.keySet().contains(medicion.getCentroMeteorologico().getMunicipio())
					&& medicion.getCentroMeteorologico().getMunicipio() != null) {
				mapaMediciones.put(MunicipioDAO.consultarMuni(medicion.getCentroMeteorologico().getMunicipio().getIdMunicipio()), medicion);
			}
			contador++;
		}
		
//		for (Map.Entry<Municipio, Medicion> entry : mapaMediciones.entrySet()) {
//			System.out.println("Key = " + entry.getKey().getNombre() + ", Value = " + entry.getValue().getCentroMeteorologico().getNombre());
//		}
		cerrarSesion();
		MunicipioDAO.cerrarSesion();
		return mapaMediciones;
	}
	
//	public static LinkedHashMap<Municipio, Medicion> consultarTopProvincias(int idProvincia) {
//		iniciarSesion();
//		MunicipioDAO.iniciarSesion();
//		
//		HQL="from Medicion order by tempAmbiente desc";
////		HQL="from Medicion where centroMeteorologico.municipio.provincia.idProvincia in :idProvincia order by tempAmbiente desc";
//		QUERY = SESSION.createQuery(HQL);
////		QUERY.setParameter("idProvincia", idProvincia);
//		List<Medicion> mediciones = QUERY.list(); 
//		
//		LinkedHashMap<Municipio, Medicion> mapaMediciones = new LinkedHashMap<Municipio, Medicion>();
//		mapaMediciones.clear();
//		int contador = 0; 
//		Medicion medicion;
//		while(mapaMediciones.size() < 5) {
//			medicion = new Medicion();
//			medicion = mediciones.get(contador);
//			if((!mapaMediciones.keySet().contains(medicion.getCentroMeteorologico().getMunicipio()))
//					&& (medicion.getCentroMeteorologico().getMunicipio() != null) && (String.valueOf(medicion.getCentroMeteorologico().getMunicipio().getProvincia().getIdProvincia()).equals(String.valueOf(idProvincia)))) {
//				System.out.println("\n TAMAÑO MAPA PROVINCIAS - > " + mapaMediciones.size());
//				mapaMediciones.put(MunicipioDAO.consultarMuni(medicion.getCentroMeteorologico().getMunicipio().getIdMunicipio()), medicion);
//			}
//			contador++;
//		}
//		
////		for (Map.Entry<Municipio, Medicion> entry : mapaMediciones.entrySet()) {
////			System.out.println("Key = " + entry.getKey().getNombre() + ", Value = " + entry.getValue().getCentroMeteorologico().getNombre());
////		}
//		
//		cerrarSesion();
//		MunicipioDAO.cerrarSesion();
//		return mapaMediciones;
//	}
	
	public static List<Medicion> consultarRegistroPorIdEspacio(int idCentroMet) {
		
		HQL = "from Medicion as medi where medi.centroMeteorologico.idCentroMet = :idCentroMet";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("idCentroMet", idCentroMet);
		List<Medicion> medi = QUERY.list(); 
	
        return medi;
	}
	
}
