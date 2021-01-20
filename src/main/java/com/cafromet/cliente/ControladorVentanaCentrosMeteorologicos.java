package com.cafromet.cliente;

import java.util.ArrayList;
import com.cafromet.modelo.CentroMeteorologico;
import com.cafromet.server.Datos;
import com.cafromet.server.Peticiones;

public class ControladorVentanaCentrosMeteorologicos {

	private Datos datos;

	private ArrayList<CentroMeteorologico> centrosMeteorologicos;
	
	VentanaCentrosMeteorologicos ventanaCentrosMeteorologicos = new VentanaCentrosMeteorologicos();

	public ControladorVentanaCentrosMeteorologicos(VentanaCentrosMeteorologicos ventanaCentrosMeteorologicos) {
	
		this.ventanaCentrosMeteorologicos = ventanaCentrosMeteorologicos;
		enviarPeticion("prueba", Peticiones.p105a);
	}
	
	public boolean enviarPeticion(String contenido, Peticiones peticion){
		try {
			datos = new Datos();
			datos.setContenido(contenido);
			datos.setPeticion(peticion);
			IOListenerClt IOListenerClt = new IOListenerClt(datos);
			Thread hiloSender = new Thread(IOListenerClt);
			hiloSender.start();
			hiloSender.join();
			datos = IOListenerClt.getDatos();
			procesarRecepcion();		
		}catch(Exception e) {
			e.getMessage();
		}

		return true;
	}
	
	@SuppressWarnings("unchecked")
	public boolean procesarRecepcion() {

		switch (datos.getPeticion().getCodigo()) {

		case 5:
			 
			centrosMeteorologicos = (ArrayList<CentroMeteorologico>) datos.getObjeto();
			
			String matrizInfo[][] = new String[centrosMeteorologicos.size()][2];
			
			for (int i = 0; i < centrosMeteorologicos.size(); i++) {
				
				matrizInfo[i][0] = centrosMeteorologicos.get(i).getNombre();
				matrizInfo[i][1] = centrosMeteorologicos.get(i).getDireccion();
				ventanaCentrosMeteorologicos.getDefaultTableModel().addRow(matrizInfo[i]);			
			}
			break;
		}
		return true;
	}
	
}
