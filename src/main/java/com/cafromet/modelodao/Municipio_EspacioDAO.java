package com.cafromet.modelodao;

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
	
	public synchronized static void iniciarSesion() {SESSION = HibernateUtil.getSessionFactory().openSession();}
	
	public synchronized static void cerrarSesion() {SESSION.close();}
	
	public synchronized static boolean duplicado(Municipio_EspacioNatural mun_esp) {
		if(consultarRegistro(mun_esp) != null) {
			return true;
		}
		return false;
	}
	
	public synchronized static boolean insertarRegistro(Municipio_EspacioNatural mun_esp) {
		if(duplicado(mun_esp)) {
			return false;
		}
		SESSION.beginTransaction();		
		SESSION.save(mun_esp);
		SESSION.getTransaction().commit();	
		return true;
	}

	
	public synchronized static Municipio_EspacioNatural consultarRegistro(Municipio_EspacioNatural mun_esp) {
		HQL = "from Municipio_EspacioNatural as munesp where (munesp.id.idMunicipio = :idMunicipio and munesp.id.idEspacio = :idEspacio)";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("idMunicipio", mun_esp.getId().getIdMunicipio());
		QUERY.setParameter("idEspacio", mun_esp.getId().getIdEspacio());
		Municipio_EspacioNatural mun_esp2 =  (Municipio_EspacioNatural) QUERY.uniqueResult(); 
        return mun_esp2;
	}
	
	
	
}
