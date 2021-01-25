package com.cafromet.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.cafromet.modelo.CentroMeteorologico;
import com.cafromet.modelo.Cliente;
import com.cafromet.modelo.EspacioNatural;
import com.cafromet.modelo.Favoritos;
import com.cafromet.modelo.Medicion;
import com.cafromet.modelo.Municipio;
import com.cafromet.modelodao.CentroMeteorologicoDAO;
import com.cafromet.modelodao.ClienteDAO;
import com.cafromet.modelodao.EspacioNaturalDAO;
import com.cafromet.modelodao.FavoritosDAO;
import com.cafromet.modelodao.MedicionDAO;
import com.cafromet.modelodao.MunicipioDAO;
import com.cafromet.modelodto.CentroMeteorologicoDTO;
import com.cafromet.modelodto.ClienteDTO;
import com.cafromet.modelodto.EspacioNaturalDTO;
import com.cafromet.modelodto.MunicipioDTO;

public class IOListenerSrv extends Thread {
	protected static int ID_CLIENTE = 0;
	protected static int NUM_CONSULTAS = 0;
	private final String idConexion;
	private Socket cliente;
	private JTextArea textArea = null;
	private JTextField textoF = null;
	private ObjectOutputStream osalida = null;
	private ObjectInputStream oentrada = null;
	private Datos datos;

	public IOListenerSrv(Socket cliente, JTextArea textArea, JTextField texto) throws IOException {
		ID_CLIENTE++;
		NUM_CONSULTAS++;
		this.idConexion = String.valueOf(ID_CLIENTE);
		this.cliente = cliente;
		this.textArea = textArea;
		this.textoF = texto;
		this.oentrada = new ObjectInputStream(cliente.getInputStream());
		this.osalida = new ObjectOutputStream(cliente.getOutputStream());
	}

	public void run() {
		datos = new Datos();
		datos.setContenido("");

		try {
			datos = (Datos) oentrada.readObject();

			textArea.append(" Conexion => " + idConexion + " || Peticion => " + datos.getPeticion() + "\n");

			procesarPeticion();

			osalida.writeObject(datos);
			osalida.flush();

			cerrarConexion();
		} catch (ClassNotFoundException e) {
			System.out.println(" !ERROR: Input Listener Servidor -> ClassNotFoundException");
			GestorConexiones.getInstance().cerrarConexion(idConexion);
		} catch (IOException e) {
			System.out.println(" !ERROR: Input Listener Servidor -> IOException");
			GestorConexiones.getInstance().cerrarConexion(idConexion);
		}

		textoF.setText("Numero de consultas realizadas (sesion actual): " + NUM_CONSULTAS);
		System.out.println(" #CONEXION " + idConexion + " -> Desconectado\n");

	}

	public synchronized boolean procesarPeticion() {
		switch (datos.getPeticion().getCodigo()) {
		case 1:
			ClienteDAO.iniciarSesion();
			switch (datos.getPeticion().getPlataforma()) {

			case 1:
				String[] array = datos.getContenido().split(",");

				String usuario = array[0];
				String passwd = array[1];
				Cliente cliente = new Cliente(usuario, passwd, null, null);
				Cliente clienteComprobacion = new Cliente();
				boolean existe;
				clienteComprobacion = ClienteDAO.consultarRegistro(cliente.getUsuario());

				if (clienteComprobacion != null) {
					if (cliente.getPasswd().equals(clienteComprobacion.getPasswd())) {
						existe = true;
						datos.setObjeto(existe);
						datos.setContenido("Este es el usuario");
					}
				} else {
					System.out.println("\n EL USUARIO NO EXISTE");
					existe = false;
					datos.setObjeto(existe);
				}
				break;

			case 2:
				boolean existe1;
				Cliente clienteComprobacion2 = new Cliente();
				ClienteDTO clienteDTO = (ClienteDTO) datos.getObjeto();
				Cliente cliente1 = new Cliente();
				cliente1.setUsuario(clienteDTO.getUsuario());
				cliente1.setPasswd(clienteDTO.getPasswd());
				
				clienteComprobacion2 = ClienteDAO.consultarRegistro(cliente1.getUsuario());

				if (clienteComprobacion2 != null) {
					if (cliente1.getPasswd().equals(clienteComprobacion2.getPasswd())) {
						existe = true;
						datos.setObjeto(existe);
						datos.setContenido(String.valueOf(clienteComprobacion2.getIdCliente()));
					}
				} else {
					System.out.println("\n EL USUARIO NO EXISTE");
					existe = false;
					datos.setObjeto(existe);
				}
				
				ClienteDAO.cerrarSesion();
				break;
			}
			break;
		case 2:
			ClienteDAO.iniciarSesion();
			switch (datos.getPeticion().getPlataforma()) {
			case 1:
				Cliente cliente2 = (Cliente) datos.getObjeto();
				System.out.println("\n RECEPCION SERVER => Cliente: " + cliente2.getUsuario() + "; Password: "
						+ cliente2.getPasswd());
				datos.setObjeto(ClienteDAO.insertarRegistro(cliente2));
				break;
			case 2:
				ClienteDTO clienteDto = (ClienteDTO) datos.getObjeto();
				Cliente cliente1 = new Cliente();
				cliente1.setUsuario(clienteDto.getUsuario());
				cliente1.setPasswd(clienteDto.getPasswd());
				System.out.println("\n RECEPCION SERVER => Cliente: " + cliente1.getUsuario() + "; Password: "
						+ cliente1.getPasswd());
				datos.setObjeto(ClienteDAO.insertarRegistro(cliente1));
				break;
			}
			ClienteDAO.cerrarSesion();
			break;
		case 3:
			MunicipioDAO.iniciarSesion();
			List<Municipio> lista = MunicipioDAO.consultarRegistros();
			switch (datos.getPeticion().getPlataforma()) {
			case 1:
				datos.setObjeto(lista);
				break;
			case 2:
				List<MunicipioDTO> listaDTO = new ArrayList<MunicipioDTO>();
				for (Municipio muni : lista) {
					MunicipioDTO muniDTO = new MunicipioDTO();
					muniDTO.setNombre(muni.getNombre());
					muniDTO.setIdMunicipio(muni.getIdMunicipio());
					muniDTO.setProvincia(muni.getProvincia().getNombre());
					listaDTO.add(muniDTO);
				}
				datos.setObjeto(listaDTO);
				break;
			case 3:
				ArrayList<Municipio> listaMunicipio = new ArrayList<Municipio>();
				for (Municipio muni : lista) {
					if (!muni.getCentroMeteorologicos().isEmpty()) {
						listaMunicipio.add(muni);
					}
				}
				datos.setObjeto(listaMunicipio);
				break;
			}
			MunicipioDAO.cerrarSesion();
			break;
		case 4:
			EspacioNaturalDAO.iniciarSesion();
			List<EspacioNatural> listaEspNat = EspacioNaturalDAO.consultarRegistros();
			switch (datos.getPeticion().getPlataforma()) {
			case 1:
				datos.setObjeto(listaEspNat);
				break;
			case 2:
				List<EspacioNaturalDTO> listaespaNat = new ArrayList<EspacioNaturalDTO>();
				for (EspacioNatural espacioNatural : listaEspNat) {
					EspacioNaturalDTO espaNatDTO = new EspacioNaturalDTO();
					espaNatDTO.setIdEspacio(espacioNatural.getIdEspacio());
					espaNatDTO.setNombre(espacioNatural.getNombre());
					espaNatDTO.setCategoria(espacioNatural.getCategoria());
					espaNatDTO.setDescripcion(espacioNatural.getDescripcion());
					espaNatDTO.setTipo(espacioNatural.getTipo());
					listaespaNat.add(espaNatDTO);
				}
				datos.setObjeto(listaespaNat);
				break;
			}
			EspacioNaturalDAO.cerrarSesion();
			break;
		case 5:
			CentroMeteorologicoDAO.iniciarSesion();
			List<CentroMeteorologico> listaCentro = CentroMeteorologicoDAO.consultarRegistros();
			switch (datos.getPeticion().getPlataforma()) {
			case 1:
				datos.setObjeto(listaCentro);
				break;
			case 2:
				List<CentroMeteorologicoDTO> listaCentroDTO = new ArrayList<CentroMeteorologicoDTO>();
				for (CentroMeteorologico centroMet : listaCentro) {
					CentroMeteorologicoDTO centroDTO = new CentroMeteorologicoDTO();
					centroDTO.setIdCentroMet(centroMet.getIdCentroMet());
					centroDTO.setNombre(centroMet.getNombre());
					listaCentroDTO.add(centroDTO);
				}
				datos.setObjeto(listaCentroDTO);
				break;
			case 3:
				ArrayList<CentroMeteorologico> listaCentros = new ArrayList<CentroMeteorologico>();
				for (CentroMeteorologico centro : listaCentro) {
					if (centro.getMunicipio() != null) {
						listaCentros.add(centro);
					}
				}
				datos.setObjeto(listaCentros);
				break;
			}
			CentroMeteorologicoDAO.cerrarSesion();
			break;
		case 6:
			MunicipioDAO.iniciarSesion();
			CentroMeteorologicoDAO.iniciarSesion();
			MedicionDAO.iniciarSesion();
			List<Municipio> listaMunicipioParaMedicion = MunicipioDAO.consultarRegistros();
			List<CentroMeteorologico> listaCentroParaMedicion = CentroMeteorologicoDAO.consultarRegistros();
			List<Medicion> listaMediciones = MedicionDAO.consultarRegistros();
			switch (datos.getPeticion().getPlataforma()) {

			case 1:
				ArrayList<Municipio> listaMunicipio = new ArrayList<Municipio>();
				for (Municipio muni : listaMunicipioParaMedicion) {
					if (!muni.getCentroMeteorologicos().isEmpty()) {
						listaMunicipio.add(muni);
					}
				}
				ArrayList<CentroMeteorologico> listaCentros = new ArrayList<CentroMeteorologico>();
				for (CentroMeteorologico centro : listaCentroParaMedicion) {
					if (centro.getMunicipio() != null) {
						listaCentros.add(centro);
					}
				}
				ArrayList<Medicion> listaMedicionesFiltro = new ArrayList<Medicion>();
				for (CentroMeteorologico centro : listaCentroParaMedicion) {
					for (Medicion medicion : listaMediciones) {
						if (centro.getIdCentroMet() == medicion.getId().getIdCentroMet()) {
							listaMedicionesFiltro.add(medicion);
						}
					}
				}
				datos.setObjeto(listaMedicionesFiltro);
				break;
			case 2:
				break;
			}
			MunicipioDAO.cerrarSesion();
			CentroMeteorologicoDAO.cerrarSesion();
			MedicionDAO.cerrarSesion();
			break;
		case 7:
			MunicipioDAO.iniciarSesion();
			switch (datos.getPeticion().getPlataforma()) {
			case 2:
				Municipio muni = new Municipio();
				String idmuni = datos.getContenido();
				muni = MunicipioDAO.consultarMuni(Integer.valueOf(idmuni));
				MunicipioDTO muniDTO = new MunicipioDTO(muni);				
				datos.setObjeto(muniDTO);
				break;
			}
			MunicipioDAO.cerrarSesion();
			break;
		case 8:
			EspacioNaturalDAO.iniciarSesion();
			switch (datos.getPeticion().getPlataforma()) {
			case 2:
				EspacioNatural espacioNatural = new EspacioNatural();
				String idEspacioNatural = datos.getContenido();
				espacioNatural = EspacioNaturalDAO.consultarRegistroPorId(Integer.valueOf(idEspacioNatural));
				EspacioNaturalDTO espacioDTO = new EspacioNaturalDTO(espacioNatural);
				datos.setContenido("Estoy enviandolo");
				datos.setObjeto(espacioDTO);
			
				break;	
				
			}
			EspacioNaturalDAO.cerrarSesion();
			break;
		case 9:
			FavoritosDAO.iniciarSesion();
			Favoritos favorito = new Favoritos();
			EspacioNatural espacio = new EspacioNatural();
			Cliente cliente = new Cliente();
			String[] array = datos.getContenido().split(";");
			espacio.setIdEspacio(Integer.parseInt(array[0]));
			cliente.setIdCliente(Integer.parseInt(array[1]));
	
			favorito.setEspacioNatural(espacio);
			favorito.setCliente(cliente);
			favorito.setFavorito((Boolean) datos.getObjeto()); 
		
			if(!FavoritosDAO.insertarRegistro(favorito)) {
				FavoritosDAO.actualizarRegistro(favorito);
			}	
			FavoritosDAO.cerrarSesion();
			break;
		case 10:
			FavoritosDAO.iniciarSesion();
			Favoritos favorito2 = new Favoritos();
			EspacioNatural espacio2 = new EspacioNatural();
			Cliente cliente2 = new Cliente();
			String[] array2 = datos.getContenido().split(";");
			espacio2.setIdEspacio(Integer.parseInt(array2[0]));
			cliente2.setIdCliente(Integer.parseInt(array2[1]));
	
			favorito2.setEspacioNatural(espacio2);
			favorito2.setCliente(cliente2);
			Favoritos registro = FavoritosDAO.consultarRegistro(favorito2);
			
			if(registro == null) {
				datos.setObjeto(false);
			}else {
				if(registro.getFavorito()) {
					datos.setObjeto(true);
				}else {
					datos.setObjeto(false);
				}
			}
			FavoritosDAO.cerrarSesion();
			
			break;
		}
		return true;
	}

	public String getIdConexion() {
		return idConexion;
	}

	public int getIdCliente() {
		return ID_CLIENTE;
	}

	public Socket getSocket() {
		return cliente;
	}

	public void cerrarConexion() throws IOException {
		GestorConexiones.getInstance().cerrarConexion(idConexion);
		if (oentrada != null)
			oentrada.close();
		if (osalida != null)
			osalida.close();
		if (cliente != null)
			cliente.close();
	}

	public void enviarMensaje(String mensaje) throws IOException {
		Datos datos = new Datos();
		datos.setContenido(mensaje);
		osalida.writeObject(datos);
	}

}
