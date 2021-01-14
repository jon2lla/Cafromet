package com.cafromet.cliente;

import java.util.ArrayList;

import com.cafromet.modelo.Municipio;
import com.cafromet.modelodto.MunicipioDTO;
import com.cafromet.server.Datos;
import com.cafromet.server.Peticion;

public class ControladorVentanMunicipio {

	private Datos datos;

	private ArrayList<MunicipioDTO> municipios;

	public VentanaMunicipio ventanaMunicipio = new VentanaMunicipio();

	public ControladorVentanMunicipio(VentanaMunicipio pVentanaMunicipio) {

		ventanaMunicipio = pVentanaMunicipio;
		try {
			enviarPeticion("prueba", Peticion.p103);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void enviarPeticion(String contenido, Peticion peticion) throws InterruptedException {
		datos = new Datos();
		datos.setContenido(contenido);
		datos.setPeticion(peticion);
		IOListenerClt IOListenerClt = new IOListenerClt(datos);
		Thread hiloSender = new Thread(IOListenerClt);
		hiloSender.start();
		hiloSender.join();
		datos = IOListenerClt.getDatos();
		procesarRecepcion();
	}

	@SuppressWarnings("unchecked")
	public void procesarRecepcion() {

		switch (datos.getPeticion().getCodigo()) {

		case 3:
			 
			municipios = (ArrayList<MunicipioDTO>) datos.getObjeto();
			
			String matrizInfo[][] = new String[municipios.size()][2];
			
			for (int i = 0; i < municipios.size(); i++) {
				
				matrizInfo[i][0] = municipios.get(i).getNombre();
				matrizInfo[i][1] = municipios.get(i).getProvincia();
				
				ventanaMunicipio.getDefaultTableModel().addRow(matrizInfo[i]);	
			}
			
			break;
		}
	}

}
