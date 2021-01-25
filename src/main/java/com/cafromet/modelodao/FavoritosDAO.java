package com.cafromet.modelodao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.cafromet.modelo.Favoritos;
import com.cafromet.modelo.Municipio;
import com.cafromet.util.HibernateUtil;

@SuppressWarnings("deprecation")
public class FavoritosDAO {
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
	
	public static boolean duplicado(Favoritos favorito) {
		Favoritos registro = consultarRegistro(favorito);
		if(registro != null) {
			return true;
		}else if(favorito.equals(registro)) {
			return true;
		}
		return false;
	}
	
	public static boolean insertarRegistro(Favoritos favorito) {
		if(duplicado(favorito)) {
			return false;
		}
		SESSION.beginTransaction();		
		SESSION.save(favorito);
		SESSION.getTransaction().commit();	
		return true;
	}
	
	public static Favoritos consultarRegistro(Favoritos favorito) {
		HQL = "from Favoritos as fav where fav.cliente.idCliente = :idCliente and fav.espacioNatural.idEspacio = :idEspacio";
		QUERY = SESSION.createQuery(HQL);
		QUERY.setParameter("idCliente", favorito.getCliente().getIdCliente());
		QUERY.setParameter("idEspacio", favorito.getEspacioNatural().getIdEspacio());
		Favoritos registro = (Favoritos) QUERY.uniqueResult(); 
        return registro;
	}
	
	public static List<Favoritos> consultarRegistros(int idCliente) {
        String hql = "from Favoritos as fav where fav.cliente.idCliente = :idCliente ";
        QUERY = SESSION.createQuery(hql);
		QUERY.setParameter("idCliente", idCliente);
        List<Favoritos> listaFavoritos = QUERY.list();
        return listaFavoritos;
    }
	
	public static boolean actualizarRegistro(Favoritos favorito) {
		Favoritos registro = consultarRegistro(favorito); 
		SESSION.beginTransaction();	
		if(registro != null) {
			registro.setFavorito(favorito.getFavorito());
			SESSION.update(registro);
			SESSION.getTransaction().commit();
			System.out.println("\n >> REGISTRO ACTUALIZADO");
			return true;
		}
		System.out.println("\n !ERROR AL ACTUALIZAR; CLASE => FAVORITOSDAO");
		return false;
	}
	
}