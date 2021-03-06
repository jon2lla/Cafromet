package com.cafromet.modelodao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cafromet.modelo.EspacioNatural;
import com.cafromet.modelo.Municipio;
import com.cafromet.modelo.Municipio_EspacioNatural;
import com.cafromet.modelo.Municipio_EspacioNaturalId;
import com.cafromet.util.HibernateUtil;

@SuppressWarnings("deprecation")
public class Municipio_EspacioDAO {
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
	
	public static boolean duplicado(Municipio_EspacioNatural mun_esp) {
		if(consultarRegistro(mun_esp) != null) {
			return true;
		}
		return false;
	}
	
	public static boolean insertarRegistro(Municipio_EspacioNatural mun_esp) {
		if(duplicado(mun_esp)) {
			return false;
		}
		SESSION.beginTransaction();		
		SESSION.save(mun_esp);
		SESSION.getTransaction().commit();	
		return true;
	}

	
	public static Municipio_EspacioNatural consultarRegistro(Municipio_EspacioNatural mun_esp) {
		HQL = "from Municipio_EspacioNatural as munesp where (munesp.id.idMunicipio = :idMunicipio and munesp.id.idEspacio = :idEspacio)";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("idMunicipio", mun_esp.getId().getIdMunicipio());
		QUERY.setParameter("idEspacio", mun_esp.getId().getIdEspacio());
		Municipio_EspacioNatural mun_esp2 =  (Municipio_EspacioNatural) QUERY.uniqueResult(); 
//        System.out.printf(" REGISTRO(S) => %s || %d%n%n", mun_espId2.getIdMunicipio(), mun_espId2.getIdEspacio());
        return mun_esp2;
	}
	
	public static Municipio_EspacioNatural consultarRegistro(Municipio_EspacioNaturalId id) {
		HQL = "from Municipio_EspacioNatural as mun_es where mun_es.id = :id";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("id", id);
		Municipio_EspacioNatural municipio_EspacionNatural =  (Municipio_EspacioNatural) QUERY.uniqueResult(); 
//        System.out.printf(" REGISTRO(S) => %s || %s%n%n", municipio_EspacionNatural.getMunicipio().getNombre(), municipio_EspacionNatural.getEspacioNatural().getNombre());
        return municipio_EspacionNatural;
	}
	
	public static List<Municipio_EspacioNatural> consultarRegistroPorEspacio(int idEspacio) {
		HQL = "from Municipio_EspacioNatural as mun_es where mun_es.id.idEspacio = :idEspacio";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("idEspacio", idEspacio);
		List<Municipio_EspacioNatural> municipios_EspaciosNaturales = QUERY.list(); 
		return municipios_EspaciosNaturales;
	}
	
}
