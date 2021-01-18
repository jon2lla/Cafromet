package com.cafromet.server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.cafromet.modelo.Cliente;
import com.cafromet.modelo.Municipio;
import com.cafromet.modelodao.ClienteDAO;
import com.cafromet.modelodao.MunicipioDAO;
import com.cafromet.modelodto.ClienteDTO;
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
			
			textArea.append("\n Conexion => " + idConexion + " || Peticion => " + datos.getPeticion());
			
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
		System.out.println("\n #CONEXION " + idConexion + " -> Desconectado");
		
	}

	public synchronized boolean procesarPeticion() {
		MunicipioDAO.iniciarSesion();
		switch (datos.getPeticion().getCodigo()) {	
		case 1: 
			String[] array = datos.getContenido().split(",");
			
			String usuario = array[0];
			String passwd = array[1];
			Cliente cliente = new Cliente(usuario, passwd);
			Cliente clienteComprobacion = new Cliente();
			boolean existe;
			ClienteDAO.iniciarSesion();
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
			ClienteDAO.cerrarSesion();
			break;

		case 2:		
			ClienteDTO clienteDto = (ClienteDTO) datos.getObjeto();
			Cliente cliente1 = new Cliente();
			cliente1.setUsuario(clienteDto.getUsuario());
			cliente1.setPasswd(clienteDto.getPasswd());
			System.out.println("\n RECEPCION SERVER => Cliente: " + cliente1.getUsuario() + "; Password: " + cliente1.getPasswd());
			ClienteDAO.iniciarSesion();
			datos.setObjeto(ClienteDAO.insertarRegistro(cliente1));
			ClienteDAO.cerrarSesion();
			break;

		case 3:
			List<Municipio> lista =  MunicipioDAO.consultarRegistros();
			switch(datos.getPeticion().getPlataforma()) {
			case 1:
				datos.setObjeto(lista);
				break;
			case 2: 
				List<MunicipioDTO> listaDTO = new ArrayList<MunicipioDTO>();

				for(Municipio muni : lista) {
					MunicipioDTO muniDTO = new MunicipioDTO();
					muniDTO.setNombre(muni.getNombre());
					muniDTO.setIdMunicipio(muni.getIdMunicipio());
					muniDTO.setProvincia(muni.getProvincia().getNombre());
					listaDTO.add(muniDTO);
				}
				datos.setObjeto(listaDTO);
				break;
			}
			break;
	}
		MunicipioDAO.cerrarSesion();
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
		if(oentrada != null)
			oentrada.close();
		if(osalida != null)
			osalida.close();
		if(cliente != null)
			cliente.close();
	}

	public void enviarMensaje(String mensaje) throws IOException {
		Datos datos = new Datos();
		datos.setContenido(mensaje);
		osalida.writeObject(datos);
	}

}
