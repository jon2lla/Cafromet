package com.cafromet.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.cafromet.modelo.EspacioNatural;
import com.cafromet.server.Datos;
import com.cafromet.server.Peticiones;

public class ControladorVentanaEspacioNatural implements ActionListener {
	
	private Datos datos;

	private ArrayList<EspacioNatural> espacioNatural;

	VentanaEspacioNatural ventanaEspacioNatural = new VentanaEspacioNatural();

	public ControladorVentanaEspacioNatural(VentanaEspacioNatural ventanaEspacioNatural) {
		
		this.ventanaEspacioNatural = ventanaEspacioNatural;
		enviarPeticion("prueba", Peticiones.p104a);
		iniciarControlador();

	}

	private void iniciarControlador() {

		ventanaEspacioNatural.getBtnFiltrar().addActionListener(this);
		ventanaEspacioNatural.getBtnFiltrar().setActionCommand("filtrar");

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		
		case "filtrar":
		
			String provincia = (String) ventanaEspacioNatural.getComboBoxProvincia().getSelectedItem();

			break;

		}

	}
	public boolean enviarPeticion(String contenido, Peticiones peticion) {
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
		} catch (Exception e) {
			e.getMessage();
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	public boolean procesarRecepcion() {

		switch (datos.getPeticion().getCodigo()) {

		case 3:

			espacioNatural = (ArrayList<EspacioNatural>) datos.getObjeto();

			String matrizInfo[][] = new String[espacioNatural.size()][4];

			for (int i = 0; i < espacioNatural.size(); i++) {

				matrizInfo[i][0] = espacioNatural.get(i).getNombre();
				matrizInfo[i][1] = espacioNatural.get(i).getDescripcion();
				matrizInfo[i][2] = espacioNatural.get(i).getTipo();
				matrizInfo[i][3] = espacioNatural.get(i).getCategoria();
				ventanaEspacioNatural.getDefaultTableModel().addRow(matrizInfo[i]);
			}
			break;
		}
		return true;
	}


}
