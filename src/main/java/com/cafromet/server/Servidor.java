package com.cafromet.server;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.cafromet.util.HibernateUtil;

public class Servidor extends Thread {

	private int PUERTO = 44444;
	protected static int MAX_CONEXIONES = 3;
	private JTextArea textArea = null;
	private JTextField texto = null;
	private boolean continuar = true;
	private ServerSocket servidor = null;

	public Servidor(JTextArea textArea, JTextField texto) {
		this.texto = texto;
		this.textArea = textArea;
		texto.setText("0");
	}

	public void run() {

		try {
			
			servidor = new ServerSocket(PUERTO);

			System.out.println("\n **SERVIDOR INICIADO**\n");
			Socket socket = new Socket();
			
			texto.setText("Numero de consultas realizadas (sesion actual): " + IOListenerSrv.NUM_CONSULTAS);
			iniciarSesionHibernate();
			while (continuar) {
				Thread.sleep(200);
				if (GestorConexiones.getInstance().getNumUsuarios() < Servidor.MAX_CONEXIONES)
					textArea.append(" Esperando conexiones... \n");
				else
					textArea.append(" Servidor lleno\n");
				socket = servidor.accept();

				if (GestorConexiones.getInstance().getNumUsuarios() < MAX_CONEXIONES) {
					IOListenerSrv hilo = new IOListenerSrv(socket, textArea, texto);
					hilo.start();
					GestorConexiones.getInstance().registrarConexion(hilo);
					System.out.println(" #CONEXION " + hilo.getIdConexion() + " -> Conectado\n");
					texto.setText("Numero de consultas realizadas (sesion actual): " + IOListenerSrv.NUM_CONSULTAS);
				}
				else
					socket.close();
			}
			socket.close();			
			System.out.println(" **SERVIDOR TERMINADO**");
		} catch (IOException e) {
			System.out.println(" **SERVIDOR CERRADO**");
			System.exit(0);
		} catch (InterruptedException e) {
			System.out.println(" !ERROR: Servidor -> InterruptedException\n");
		}
	}
	
	public static void iniciarSesionHibernate() {
		System.out.println("\n\n ** CONECTADO A LA BBDD **\n"
		 		 + " -------------------------\n"); 
		HibernateUtil.getSessionFactory().openSession();
 
	}
	
	public void cerrarSesionHibernate() {
		System.out.println("\n\n ** DESCONECTADO DE LA BBDD **\n"
		 		 + " -------------------------\n"); 
		HibernateUtil.getSessionFactory().close();		
	}
	
	public void desconectar() {
		continuar = false;
		try {
			cerrarSesionHibernate();
			GestorConexiones.getInstance().mensajeDeDifusion("*");			
			servidor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
