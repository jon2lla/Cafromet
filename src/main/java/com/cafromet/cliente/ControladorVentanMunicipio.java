package com.cafromet.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.cafromet.modelo.Municipio;
import com.cafromet.modelodto.MunicipioDTO;
import com.cafromet.server.Datos;
import com.cafromet.server.Peticiones;

public class ControladorVentanMunicipio implements ActionListener {

	private Datos datos;

	private ArrayList<Municipio> municipios;

	public VentanaMunicipio ventanaMunicipio = new VentanaMunicipio();

	public ControladorVentanMunicipio(VentanaMunicipio pVentanaMunicipio) {

		ventanaMunicipio = pVentanaMunicipio;
		
		enviarPeticion("prueba", Peticiones.p103a);
		iniciarControlador();

	}

	private void iniciarControlador() {

		ventanaMunicipio.getBtnFiltrar().addActionListener(this);
		ventanaMunicipio.getBtnFiltrar().setActionCommand("filtrar");

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		
		case "filtrar":
		
			String provincia = (String) ventanaMunicipio.getComboBoxProvincia().getSelectedItem();

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

			municipios = (ArrayList<Municipio>) datos.getObjeto();

			String matrizInfo[][] = new String[municipios.size()][3];

			for (int i = 0; i < municipios.size(); i++) {

				matrizInfo[i][0] = municipios.get(i).getNombre();
				matrizInfo[i][1] = municipios.get(i).getDescripcion();
				if (municipios.get(i).getProvincia().getIdProvincia() == 1) {
					matrizInfo[i][2] = "Araba/Álava";
				} else if (municipios.get(i).getProvincia().getIdProvincia() == 20) {
					matrizInfo[i][2] = "Gipuzkoa";
				} else if (municipios.get(i).getProvincia().getIdProvincia() == 48) {
					matrizInfo[i][2] = "Bizkaia";
				}
				ventanaMunicipio.getDefaultTableModel().addRow(matrizInfo[i]);
			}
			break;
		}
		return true;
	}

}
