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

			while (continuar) {
				Thread.sleep(200);
				if (GestorConexiones.getInstance().getNumUsuarios() < Servidor.MAX_CONEXIONES)
					textArea.append(" Esperando conexiones... \n");
				else
					textArea.append(" Servidor lleno\n");
				socket = servidor.accept();
				SocketAddress socketAddress = socket.getRemoteSocketAddress();

				if (socketAddress instanceof InetSocketAddress) {
				    InetAddress inetAddress = ((InetSocketAddress)socketAddress).getAddress();
				    if (inetAddress instanceof Inet4Address)
				        System.out.println("IPv4: " + inetAddress);
				    else if (inetAddress instanceof Inet6Address)
				        System.out.println("IPv6: " + inetAddress);
				    else
				        System.err.println("Not an IP address.");
				} else {
				    System.err.println("Not an internet protocol socket.");
				}
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

	public void desconectar() {
		continuar = false;
		try {
			GestorConexiones.getInstance().mensajeDeDifusion("*");			
			servidor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
