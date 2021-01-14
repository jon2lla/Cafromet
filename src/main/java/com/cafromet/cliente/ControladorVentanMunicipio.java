package com.cafromet.cliente;

import java.util.ArrayList;

import com.cafromet.modelo.Municipio;
import com.cafromet.server.Datos;
import com.cafromet.server.Peticion;

public class ControladorVentanMunicipio {

	private Datos datos;

	private ArrayList<Municipio> municipio;

	public VentanaMunicipio ventanaMunicipio = new VentanaMunicipio();

	public ControladorVentanMunicipio(VentanaMunicipio pVentanaMunicipio) throws InterruptedException {

		ventanaMunicipio = pVentanaMunicipio;
		enviarPeticion("prueba", Peticion.p103);
	}

	public boolean enviarPeticion(String contenido, Peticion peticion){
		datos = new Datos();
		datos.setContenido(contenido);
		datos.setPeticion(peticion);
		IOListenerClt IOListenerClt = new IOListenerClt(datos);
		Thread hiloSender = new Thread(IOListenerClt);
		hiloSender.start();
		try {
			hiloSender.join();

		}catch(InterruptedException e) {
			e.getMessage();
		}
		datos = IOListenerClt.getDatos();
		procesarRecepcion();
		return true;
	}

	@SuppressWarnings("unchecked")
	public void procesarRecepcion() {

		switch (datos.getPeticion().getCodigo()) {

		case 3:
			 
			municipio = (ArrayList<Municipio>) datos.getObjeto();
			
			String matrizInfo[][] = new String[municipio.size()][2];
			
			for (int i = 0; i < municipio.size(); i++) {
				
				matrizInfo[i][0] = municipio.get(i).getNombre();
				matrizInfo[i][1] = municipio.get(i).getDescripcion();
				
				ventanaMunicipio.getDefaultTableModel().addRow(matrizInfo[i]);
				
			}
			break;
		}
	}

}
